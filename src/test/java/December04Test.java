import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December04Test {
    @Test
    public void testRunA() {
        December04 december04 = new December04();
        assertEquals(18, december04.runA("December04Ex.txt"));
    }

    @Test
    public void testRunB() {
        December04 december04 = new December04();
        assertEquals(9, december04.runB("December04Ex.txt"));
    }
}
