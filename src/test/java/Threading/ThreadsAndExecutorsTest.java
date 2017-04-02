package Threading;

import Threading.ThreadsAndExecutors;
import org.junit.Assert;
import org.junit.Test;

public class ThreadsAndExecutorsTest {

    @Test
    public void basicTaskExampleTest() throws Exception {
        ThreadsAndExecutors threadTaskExample = new ThreadsAndExecutors();
        threadTaskExample.runABasicTask();

        Assert.assertTrue(true);
    }

    @Test
    public void runABasicTaskWithSleepTest() throws Exception {
        ThreadsAndExecutors threadTaskExample = new ThreadsAndExecutors();
        threadTaskExample.runABasicTaskWithSleep();

        Assert.assertTrue(true);
    }

    @Test
    public void executorIntroTest() throws Exception {
        ThreadsAndExecutors executorExample = new ThreadsAndExecutors();
        executorExample.executorIntro();

        Assert.assertTrue(true);
    }

    @Test
    public void executorWithCleanShutdownTest() throws Exception {
        ThreadsAndExecutors executorExample = new ThreadsAndExecutors();
        executorExample.executorWithCleanShutdown();

        Assert.assertTrue(true);
    }






}
