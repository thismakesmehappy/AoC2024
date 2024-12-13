package helpers;

import constants.BasicDirections;
import errors.GuardIsStuckException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GuardGallivantTest {
    @ParameterizedTest
    @MethodSource("guardGallivantProvider")
    public void testGuardGallivant(
            List<String> gridInput,
            Coordinate expectedPosition,
            int expectedFacing,
            int expectedWidth,
            int expectedHeight,
            char expectedAhead) {
        GuardGallivant guardGallivant = new GuardGallivant(gridInput);
        Coordinate initialPosition = guardGallivant.getGuard().getPosition();
        int facing = guardGallivant.getGuard().getFacing();
        int width = guardGallivant.getGrid().getWidth();
        int height = guardGallivant.getGrid().getHeight();
        Coordinate ahead = guardGallivant.getGuard().getAhead();
        char aheadChar = guardGallivant.getGrid().get(ahead.getX(), ahead.getY());

        assertEquals(expectedPosition.getX(), initialPosition.getX());
        assertEquals(expectedPosition.getY(), initialPosition.getY());
        assertEquals(expectedFacing, facing);
        assertEquals(expectedWidth, width);
        assertEquals(expectedHeight, height);
        assertEquals(expectedAhead, aheadChar);
    }

    private static Object[][] guardGallivantProvider() {
        return new Object[][]{
                {List.of(
                        ".#.",
                        ".^.",
                        "..."
                ), new Coordinate(1, 1), new BasicDirections().DIRECTION(BasicDirections.UP), 3, 3, "#"},
                {List.of(
                        ".#.",
                        "...",
                        "..^"
                ), new Coordinate(2, 2), new BasicDirections().DIRECTION(BasicDirections.UP), 3, 3, "."}
        };
    }

    @ParameterizedTest
    @MethodSource("shouldTurnRightProvider")
    public void testShouldTurnRight(
            List<String> gridInput,
            Coordinate expectedPosition,
            boolean expectedShouldTurnRight) {
        GuardGallivant guardGallivant = new GuardGallivant(gridInput);
        Coordinate initialPosition = guardGallivant.getGuard().getPosition();

        assertEquals(expectedPosition.getX(), initialPosition.getX());
        assertEquals(expectedPosition.getY(), initialPosition.getY());
        assertEquals(expectedShouldTurnRight, guardGallivant.shouldTurnRight());
    }

    private static Object[][] shouldTurnRightProvider() {
        return new Object[][]{
                {List.of(
                        ".#.",
                        ".^.",
                        "..."
                ), new Coordinate(1, 1), true},
                {List.of(
                        ".#.",
                        "...",
                        "..^"
                ), new Coordinate(2, 2), false}
        };
    }

    @ParameterizedTest
    @MethodSource("stepProvider")
    public void testStep(
            List<String> gridInput,
            Coordinate expectedInitialPosition,
            Coordinate expectedEndPosition,
            int triedSteps,
            int expectedSteps,
            int expectedFacing,
            boolean expectedIsOutOfBounds) throws GuardIsStuckException {
        GuardGallivant guardGallivant = new GuardGallivant(gridInput);
        Coordinate initialPosition = guardGallivant.getGuard().getCurrentPosition();

        for (int i = 0; i < triedSteps; i++) {
            guardGallivant.takeStep();
        }

        Coordinate endPosition = guardGallivant.getGuard().getCurrentPosition();
        int facing = guardGallivant.getGuard().getFacing();
        int steps = guardGallivant.getSteps();
        boolean isOutOfBounds = guardGallivant.getGrid().isPositionOutOfBounds(endPosition);

        assertEquals(expectedInitialPosition.getX(), initialPosition.getX());
        assertEquals(expectedInitialPosition.getY(), initialPosition.getY());
        assertEquals(expectedEndPosition.getX(), endPosition.getX());
        assertEquals(expectedEndPosition.getY(), endPosition.getY());
        assertEquals(expectedFacing, facing);
        assertEquals(expectedSteps, steps);
        ;
        assertEquals(expectedIsOutOfBounds, isOutOfBounds);
    }

    private static Object[][] stepProvider() {
        return new Object[][]{
                {List.of( // input
                        ".#.",
                        ".^.",
                        "..."
                ),
                        new Coordinate(1, 1), // initial position
                        new Coordinate(3, 1), // end position
                        3, // steps to take
                        2, // expected steps
                        new BasicDirections().DIRECTION(BasicDirections.RIGHT), // facing
                        true}, // out of bounds
                {List.of(
                        "#..",
                        "..#",
                        "^.."
                ),
                        new Coordinate(0, 2),
                        new Coordinate(1, 3),
                        3,
                        4,
                        new BasicDirections().DIRECTION(BasicDirections.DOWN),
                        true}
        };
    }

    @ParameterizedTest
    @MethodSource("escapeProvider")
    public void testEscape(
            List<String> gridInput,
            Coordinate expectedInitialPosition,
            Coordinate expectedEndPosition,
            int expectedSteps,
            int expectedFacing,
            boolean expectedIsOutOfBounds) throws GuardIsStuckException {
        GuardGallivant guardGallivant = new GuardGallivant(gridInput);
        Coordinate initialPosition = guardGallivant.getGuard().getCurrentPosition();

        guardGallivant.escape();

        Coordinate endPosition = guardGallivant.getGuardCurrentPosition();
        int facing = guardGallivant.getGuard().getFacing();
        int steps = guardGallivant.getSteps();
        boolean isOutOfBounds = guardGallivant.getGrid().isPositionOutOfBounds(endPosition);

        assertEquals(expectedInitialPosition.getX(), initialPosition.getX());
        assertEquals(expectedInitialPosition.getY(), initialPosition.getY());
        assertEquals(expectedEndPosition.getX(), endPosition.getX());
        assertEquals(expectedEndPosition.getY(), endPosition.getY());
        assertEquals(expectedFacing, facing);
        assertEquals(expectedSteps, steps);
        ;
        assertEquals(expectedIsOutOfBounds, isOutOfBounds);
    }

    private static Object[][] escapeProvider() {
        return new Object[][]{
                {List.of( // input
                        ".#.",
                        ".^.",
                        "..."
                ),
                        new Coordinate(1, 1), // initial position
                        new Coordinate(3, 1), // end position
                        2, // expected steps
                        new BasicDirections().DIRECTION(BasicDirections.RIGHT), // facing
                        true}, // out of bounds
                {List.of(
                        "#..",
                        "..#",
                        "^.."
                ),
                        new Coordinate(0, 2),
                        new Coordinate(1, 3),
                        4,
                        new BasicDirections().DIRECTION(BasicDirections.DOWN),
                        true}
        };
    }

    @Test
    public void testGuardIsStuck() {
        List<String> gridInput = List.of(
                ".#.",
                "#^#",
                ".#."
        );
        int triedSteps = 5;
        GuardGallivant guardGallivant = new GuardGallivant(gridInput);
        assertThrows(GuardIsStuckException.class, () -> {
                    for (int i = 0; i < triedSteps; i++) {
                        guardGallivant.takeStep();
                    }
                }
        );
    }

    private List<String> provideLargeExample() {
        return List.of(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#..."
        );
    }
}
