import errors.GuardIsStuckException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December06Test {
    @Test
    public void testRunA() throws GuardIsStuckException {
        December06 december06 = new December06();
        int result = december06.runA("December06Ex.txt");
        assertEquals(41, result);
    }
}
