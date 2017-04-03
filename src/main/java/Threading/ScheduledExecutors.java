package Threading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutors {

    /**
     * Scheduling a task produces a specialized future of type ScheduledFuture which
     * - in addition to Future - provides the method getDelay() to retrieve the remaining delay.
     * After this delay has elapsed the task will be executed concurrently.
     * @throws InterruptedException
     */
    public void scheduleExecutorRunAfterDelay() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);
    }

    /**
     * In order to schedule tasks to be executed periodically,
     * executors provide the two methods scheduleAtFixedRate() and scheduleWithFixedDelay().
     * The first method is capable of executing tasks with a fixed time rate,
     * e.g. once every second as demonstrated in this example:
     *
     * Please keep in mind that scheduleAtFixedRate() doesn't take into account the actual duration
     * of the task.
     * So if you specify a period of one second but the task needs 2 seconds to be executed
     * then the thread pool will working to capacity very soon.
     */
    public void scheduleExecutorAtFixedRate() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 1;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        //Sleep the code so that we can see a second task get scheduled
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * This method works just like the counterpart described above.
     * The difference is that the wait time period applies between the end of a task
     * and the start of the next task.
     */
    public void scheduleExecutorWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Scheduling: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);

        //Sleep the code so that we can see a second task get scheduled
        TimeUnit.SECONDS.sleep(5);
    }



}

