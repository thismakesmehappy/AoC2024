package helpers;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ProcessDataHelpersTest {
    @Test
    public void testSplitLineIntoListOfNumbers() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        String input = "1 2 3 4 5";
        List<Integer> obtained = ProcessDataHelpers.splitLineIntoListOfNumbers(input);
        assert  expected.equals(obtained);
    }
}
