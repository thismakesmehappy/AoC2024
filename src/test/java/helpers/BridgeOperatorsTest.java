package helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BridgeOperatorsTest {
    @ParameterizedTest
    @MethodSource("provideOperations")
    void testOperations(String input, int expectedTotal, List<Integer> expectedList) {
        BridgeOperators bridgeOperators = new BridgeOperators(input);
        assertEquals(expectedTotal, bridgeOperators.getTotal());
        assertEquals(expectedList, bridgeOperators.getOperands());
    }

    @ParameterizedTest
    @MethodSource("provideCanOperateOrConcatenate")
    void testCanOperate(String input, boolean expectedCanOperate) {
        BridgeOperators bridgeOperators = new BridgeOperators(input);
        boolean canOperate = bridgeOperators.canOperate();
        assertEquals(expectedCanOperate, canOperate);
    }

    private static Object[][] provideOperations() {
        return new Object[][]{
                {"12 ", 12, EMPTY_LIST},
                {"123: ", 123, EMPTY_LIST},
                {"3: 5", 3, List.of(5)},
                {"10: 1 2 3 4", 10, List.of(1, 2, 3, 4)},
                {"15: 1 3 5 7 9", 15, List.of(1, 3, 5, 7, 9)},
                {"50: 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45", 50, List.of(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45)}

        };
    }

    private static Object[][] provideCanOperateOrConcatenate() {
        return new Object[][]{
                {"190: 10 19", true, true},
                {"3267: 81 40 27", true, true},
                {"83: 17 5", false, false},
                {"156: 15 6", false, true},
                {"7290: 6 8 6 15", false, true},
                {"161011: 16 10 13", false, false},
                {"192: 17 8 14", false, true},
                {"21037: 9 7 18 13", false, false},
                {"292: 11 6 16 20", true, true}
        };
    }
}
