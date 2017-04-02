package Threading;

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
}
