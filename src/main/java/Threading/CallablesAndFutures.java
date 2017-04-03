package Threading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallablesAndFutures {

    /**
     * In addition to Runnable, executors support another kind of task named Callable.
     * Callables are functional interfaces just like runnables but
     * instead of being void they return a value.
     * This lambda expression defines a callable returning an integer
     * after sleeping for one second
     *
     * Callables can be submitted to executor services just like runnables.
     * But what about the callables result?
     * Since submit() doesn't wait until the task completes, the executor service
     * cannot return the result of the callable directly.
     * Instead the executor returns a special result of type Future
     * which can be used to retrieve the actual result at a later point in time.
     */
    public void runABasicTaskUsingCallableAndFuture() throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        // Block to wait for the future to complete
        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
    }

    public void runABasicTaskUsingCallableAndFutureWithTimeout() throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        });

        // Block to wait for the future to complete
        future.get(1, TimeUnit.SECONDS);
    }

    /**
     * Executors support batch submitting of multiple callables at once via invokeAll().
     * This method accepts a collection of callables and returns a list of futures.
     *
     * In this example we utilize Java 8 functional streams in order to process all futures
     * returned by the invocation of invokeAll.
     * We first map each future to its return value and then print each value to the console. If you're not yet familiar with streams read my Java 8 Stream Tutorial.
     *
     * @throws InterruptedException
     */
    public void invokeAllExample() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");


        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }

    /**
     * We use this method to create a bunch of callables with different durations
     * from one to three seconds.
     * Submitting those callables to an executor via invokeAny()
     * returns the string result of the fastest callable - in that case task2
     * This example uses yet another type of executor created via newWorkStealingPool().
     * This factory method is part of Java 8 and returns an executor of type ForkJoinPool
     * which works slightly different than normal executors.
     * Instead of using a fixed size thread-pool, ForkJoinPools are created for
     * a given parallelism size which per default is the number of
     * available cores of the hosts CPU.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void invokeAnyExample() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                testCallableWithSleep("task1", 2),
                testCallableWithSleep("task2", 1),
                testCallableWithSleep("task3", 3));

        String result = executor.invokeAny(callables);
        System.out.println(result);

        // => task2
    }
    Callable<String> testCallableWithSleep(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
