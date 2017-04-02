import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExpressions {

    public String filterExample(){
        String[] input = {"tiger", "cat", "TIGER", "Tiger", "leopard"};

        List<String> cats = Arrays.asList(input);
        String search = "tiger";
        String tigers = cats.stream()
                .filter(c -> c.equalsIgnoreCase(search))
                .collect(Collectors.joining(", "));

        return tigers;
    }

    public List<Integer> mapExample(){
        String[] input = {"tiger", "cat", "TIGER", "Tiger", "leopard"};

        List<String> cats = Arrays.asList(input);
        List<Integer> namesLength = cats.stream()
                .map(c -> c.length())
                .collect(Collectors.toList());

        return namesLength;
    }


    /**
     *  Reduce implements the reduce idiom, which is really a family of similar and related aggregation (or fold) operations.
     *  Reduce takes two arguments, the initial value (called the identity) and a function to apply
     *  step-by-step (the aggregator). The aggregator function itself takes in two arguments of the same type
     *  and returns another value of that type. This second argument to reduce() (the aggregator) is a lambda function.
     *
     *  An easy way to think about the second argument to reduce() is that it creates a "running total" as it runs over
     *  a stream. It starts by combining the identity with the first element of the stream, then combines that result
     *  with the second element of the stream, and so on.
     *
     * @return
     */
    public double reduceExample(){
        double sumPrimes = ((double)Stream.of(2, 3, 5, 7, 11, 13, 17, 19, 23)
                .reduce (0, (x, y) -> {return x + y;}));
        return sumPrimes;
    }

}
