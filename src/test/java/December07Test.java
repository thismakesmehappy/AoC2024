import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December07Test {
    @Test
    void testRunA() {
        December07 december07 = new December07();
        long resultA = december07.runA("December07Ex.txt");
        assertEquals(3749L, resultA);
    }

    @Test
    void testRunABig() {
        December07 december07 = new December07();
        long resultA = december07.runA("December07.txt");
        assertEquals(3312271365652L, resultA);
    }

    @Test
    void testRunB() {
        December07 december07 = new December07();
        long resultA = december07.runB("December07Ex.txt");
        assertEquals(11387L, resultA);
    }

    @Test
    void testRunBBig() {
        December07 december07 = new December07();
        long resultA = december07.runB("December07.txt");
        assertEquals(509463489296712L, resultA);
    }
}
