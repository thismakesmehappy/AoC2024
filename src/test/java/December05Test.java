import helpers.SafetyManualUpdates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December05Test {
    private static final SafetyManualUpdates safetyManualUpdates = December05.getUpdatesFromInput("December05.txt");
    private static final SafetyManualUpdates safetyManualUpdatesExample =
            December05.getUpdatesFromInput("December05Ex.txt");

    @Test
    public void testGetUpdatesFromInputA() {
        December05 december05 = new December05();
        int resultA = december05.runA(safetyManualUpdatesExample);
        assertEquals(143, resultA);
    }

    @Test
    public void testGetUpdatesFromInputABig() {
        December05 december05 = new December05();
        int resultA = december05.runA(safetyManualUpdates);
        assertEquals(4281, resultA);
    }

    @Test
    public void testGetUpdatesFromInputB() {
        December05 december05 = new December05();
        int resultA = december05.runB(safetyManualUpdatesExample);
        assertEquals(123, resultA);
    }

    @Test
    public void testGetUpdatesFromInputBBig() {
        December05 december05 = new December05();
        int resultA = december05.runB(safetyManualUpdates);
        assertEquals(5466, resultA);
    }
}
