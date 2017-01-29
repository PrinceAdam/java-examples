import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

}
