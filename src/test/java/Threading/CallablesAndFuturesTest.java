package Threading;

import org.junit.Assert;
import org.junit.Test;

public class CallablesAndFuturesTest {

    @Test
    public void runABasicTaskUsingCallableAndFutureTest() throws Exception {
        CallablesAndFutures callableAndFutureExample = new CallablesAndFutures();
        callableAndFutureExample.runABasicTaskUsingCallableAndFuture();

        Assert.assertTrue(true);
    }

    @Test
    public void runABasicTaskUsingCallableAndFutureWithTimeoutTest() throws Exception {
        CallablesAndFutures callableAndFutureExample = new CallablesAndFutures();
        callableAndFutureExample.runABasicTaskUsingCallableAndFutureWithTimeout();

        Assert.assertTrue(true);
    }

}
