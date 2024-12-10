import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December02Test {
    @Test
    public void testRunA() {
        December02 december02 = new December02();
        int result = december02.runA("December02Ex.txt");
        assertEquals(2, result);
    }

    @Test
    public void testRunB() {
        December02 december02 = new December02();
        int result = december02.runB("December02Ex.txt");
        assertEquals(4, result);
    }

    @Test
    public void testRunBBig() {
        December02 december02 = new December02();
        int result = december02.runB("December02.txt");
        assertEquals(400, result);
    }

    @Test
    public void testRunBEdge() {
        December02 december02 = new December02();
        int result = december02.runB("December02ExEdge.txt");
        assertEquals(26, result);
    }
}
