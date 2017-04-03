package Threading;

import org.junit.Assert;
import org.junit.Test;

public class ScheduledExecutorsTest {
    @Test
    public void scheduleExecutorRunAfterDelayTest() throws Exception {
        ScheduledExecutors executorExample = new ScheduledExecutors();
        executorExample.scheduleExecutorRunAfterDelay();

        Assert.assertTrue(true);
    }

    @Test
    public void scheduleExecutorAtFixedRateTest() throws Exception {
        ScheduledExecutors executorExample = new ScheduledExecutors();
        executorExample.scheduleExecutorAtFixedRate();

        Assert.assertTrue(true);
    }

    @Test
    public void scheduleExecutorWithFixedDelayTest() throws Exception {
        ScheduledExecutors executorExample = new ScheduledExecutors();
        executorExample.scheduleExecutorWithFixedDelay();

        Assert.assertTrue(true);
    }
}
