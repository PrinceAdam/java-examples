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

}
