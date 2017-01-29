import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class LambdaExpressionsTest {

    @Test
    public void whenFilterExampleCalled_ItReturnsCorrectResult() throws Exception {
        LambdaExpressions lambdaExamples = new LambdaExpressions();
        String tigers = lambdaExamples.filterExample();

        Assert.assertEquals("tiger, TIGER, Tiger", tigers);
    }


    @Test
    public void whenMapExampleCalled_ItReturnsCorrectResult() throws Exception {
        LambdaExpressions lambdaExamples = new LambdaExpressions();
        List<Integer> namesLength = lambdaExamples.mapExample();

        Assert.assertEquals(5, namesLength.get(0).intValue());
        Assert.assertEquals(3, namesLength.get(1).intValue());
        Assert.assertEquals(5, namesLength.get(2).intValue());
        Assert.assertEquals(5, namesLength.get(3).intValue());
        Assert.assertEquals(7, namesLength.get(4).intValue());
    }

}