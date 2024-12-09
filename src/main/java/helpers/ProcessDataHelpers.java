package helpers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessDataHelpers {
    public static List<Integer> splitLineIntoListOfNumbers(String line) {
        String[] split = line.split("[ \\t\\n\\r\\f]+", 0);
        return Stream.of(split).map(Integer::parseInt).collect(Collectors.toList());
    }
}
