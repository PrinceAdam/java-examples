import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class LambdaExpressionsTest {
    @Test
    public void whenFilterExampleCalled_ItReturnsCorrectResult() throws Exception {
        LambdaExpressions lambdaExamples = new LambdaExpressions();
        String tigers = lambdaExamples.filterExample();

        Assert.assertEquals("tiger, TIGER, Tiger", tigers);
    }

}