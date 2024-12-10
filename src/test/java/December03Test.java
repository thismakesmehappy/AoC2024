import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December03Test {
    @Test
    public void testRunA() {
        December03 december03 = new December03();
        int result = december03.runA("December03ExA.txt");
        assertEquals(161, result);
    }

    @Test
    public void testRunB() {
        December03 december03 = new December03();
        int result = december03.runB("December03ExB.txt");
        assertEquals(48, result);
    }

    @Test
    public void testRunBBig() {
        December03 december03 = new December03();
        int result = december03.runB("December03.txt");
        assertEquals(74838033, result);
    }
}