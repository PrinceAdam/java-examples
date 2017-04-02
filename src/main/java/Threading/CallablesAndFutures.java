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

//    public void invokeAnyExample() {
//        Callable<String> callable(String result, long sleepSeconds) {
//            return () -> {
//                TimeUnit.SECONDS.sleep(sleepSeconds);
//                return result;
//            };
//        }
//
//        ExecutorService executor = Executors.newWorkStealingPool();
//
//        List<Callable<String>> callables = Arrays.asList(
//                callable("task1", 2),
//                callable("task2", 1),
//                callable("task3", 3));
//
//        String result = executor.invokeAny(callables);
//        System.out.println(result);
//
//// => task2
//    }
}
