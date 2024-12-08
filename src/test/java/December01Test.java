import helpers.Pair;
import org.junit.jupiter.api.Test;

import static helpers.Constants.BASE_INPUT_DIRECTORY;

public class December01Test {
    @Test
    public void testRunA() {
        December01 december01 = new December01();
        int result = december01.runA("December01Ex.txt");
        assert result == 11;
    }

    @Test
    public void testRunB() {
        December01 december01 = new December01();
        int result = december01.runB("December01Ex.txt");
        assert result == 31;
    }
}
