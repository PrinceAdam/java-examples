package Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * These examples are taken from the tutorial at:
 * http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 */
public class ThreadsAndExecutors {

    /**
     *  Here we specify the code to be executed by new thread,
     *  this code is often called the 'task'.
     *  This is done by implementing Runnable - a functional interface defining
     *  a single void no-args method run(), as demonstrated.
     */
    public void runABasicTask(){
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        // run from main thread
        task.run();

        // run from new thread
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }

    /**
     * Causes one second delay between the first and the second print statement.
     * Because we're running on a separate thread, the main thread is not affected
     */
    public void runABasicTaskWithSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1); //you can achieve the same by calling Thread.sleep(1000).
                System.out.println("Bar " + name);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        // Wait for the thread to finish before jumping out of the code example.
        // "Bar" was not getting output before the caller had finished running.
        thread.join();
    }

    /**
     * The Concurrency API introduces the concept of an ExecutorService
     * as a higher level replacement for working with threads directly.
     * Executors are capable of running asynchronous tasks and typically
     * manage a pool of threads.
     * The class Executors provides convenient factory methods for creating
     * different kinds of executor services.
     * In this sample we use an executor with a thread pool of size one.
     */
    public void executorIntro(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        // => Hello pool-1-thread-1

        System.out.println("executor done");
    }

    /**
     * when running the code in an executor you'll notice an important difference: the java process never stops!
     * Executors have to be stopped explicitly - otherwise they keep listening for new tasks.
     * An ExecutorService provides two methods for that purpose:
     *  shutdown() waits for currently running tasks to finish
     *  shutdownNow() interrupts all running tasks and shut the executor down immediately.
     *  (I actually don't see this happening as my unit test (caller) completes.
     *  The executor doesn't hold anything up in this scenario)
     */

    /**
     * This is the preferred way how I (the tutorial dude) typically shutdown executors:
     */
    public void executorWithCleanShutdown(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}


