package helpers;

import constants.BasicDirections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardTest {
    static final int UP = new BasicDirections().DIRECTION(BasicDirections.UP);
    static final int DOWN = new BasicDirections().DIRECTION(BasicDirections.DOWN);;
    static final int RIGHT = new BasicDirections().DIRECTION(BasicDirections.RIGHT);
    static final int LEFT = new BasicDirections().DIRECTION(BasicDirections.LEFT);
    @Test
    public void testGuardWithDefaultFacing() {
        Guard guard = new Guard(1, 2);
        assertEquals(1, guard.getX());
        assertEquals(2, guard.getY());
        assertEquals(UP, guard.getFacing());
    }

    @Test
    public void testGuardWithCustomFacing() {
        Guard guard = new Guard(1, 2, LEFT);
        assertEquals(1, guard.getX());
        assertEquals(2, guard.getY());
        assertEquals(LEFT, guard.getFacing());
    }

    @ParameterizedTest
    @MethodSource("aheadProvider")
    public void testAhead(Guard start, Coordinate expected) {
        Coordinate ahead = start.getAhead();
        assertTrue(expected.equals(ahead));
    }
    private static Object[][] aheadProvider() {
        return new Object[][] {
                {new Guard(1, 2, UP), new Coordinate(1, 1)},
                {new Guard(1, 2, DOWN), new Coordinate(1, 3)},
                {new Guard(1, 2, LEFT), new Coordinate(0, 2)},
                {new Guard(1, 2, RIGHT), new Coordinate(2, 2)},
        };
    }

    @ParameterizedTest
    @MethodSource("turnRightProvider")
    public void testTurnRight(Guard start, Guard expected) {
        start.turnRight();
        assertTrue(expected.equals(start));
    }

    private static Object[][] turnRightProvider() {
        return new Object[][] {
                {new Guard(1, 2, UP), new Guard(1, 2, RIGHT)},
                {new Guard(1, 2, DOWN), new Guard(1, 2, LEFT)},
                {new Guard(1, 2, LEFT), new Guard(1, 2, UP)},
                {new Guard(1, 2, RIGHT), new Guard(1, 2, DOWN)},
        };
    }

    @ParameterizedTest
    @MethodSource("turnLeftProvider")
    public void testTurnLeft(Guard start, Guard expected) {
        start.turnLeft();
        assertTrue(expected.equals(start));
    }

    private static Object[][] turnLeftProvider() {
        return new Object[][] {
                {new Guard(1, 2, UP), new Guard(1, 2, LEFT)},
                {new Guard(1, 2, DOWN), new Guard(1, 2, RIGHT)},
                {new Guard(1, 2, LEFT), new Guard(1, 2, DOWN)},
                {new Guard(1, 2, RIGHT), new Guard(1, 2, UP)},
        };
    }

    @ParameterizedTest
    @MethodSource("turnRightAndMoveProvider")
    public void testTurnRightAndMove(Guard start, Guard expected) {
        start.turnRight();
        start.step();
        assertTrue(expected.equals(start));
    }

    private static Object[][] turnRightAndMoveProvider() {
        return new Object[][] {
                {new Guard(1, 2, UP), new Guard(2, 2, RIGHT)},
                {new Guard(1, 2, DOWN), new Guard(0, 2, LEFT)},
                {new Guard(1, 2, LEFT), new Guard(1, 1, UP)},
                {new Guard(1, 2, RIGHT), new Guard(1, 3, DOWN)},
        };
    }

    @ParameterizedTest
    @MethodSource("turnLeftAndMoveProvider")
    public void turnLeftAndMoveTest(Guard start, Guard expected) {
        start.turnLeft();
        start.step();
        assertTrue(expected.equals(start));
    }

    private static Object[][] turnLeftAndMoveProvider() {
        return new Object[][] {
                {new Guard(1, 2, UP), new Guard(0, 2, LEFT)},
                {new Guard(1, 2, DOWN), new Guard(2, 2, RIGHT)},
                {new Guard(1, 2, LEFT), new Guard(1, 3, DOWN)},
                {new Guard(1, 2, RIGHT), new Guard(1, 1, UP)},
        };
    }

}
