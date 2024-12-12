package constants;

import helpers.Coordinate;

import java.util.List;

public class AllDirections {
    private static final int TOP = -1;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 1;
    private static final int LEFT = -1;
    private static final int SAME = 0;
    public static final List<Coordinate> ALL_DIRECTIONS = List.of(
            new Coordinate(SAME, TOP), // UP
            new Coordinate(RIGHT, TOP),
            new Coordinate(RIGHT, SAME), // RIGHT
            new Coordinate(RIGHT, BOTTOM),
            new Coordinate(SAME, BOTTOM), // DOWN
            new Coordinate(LEFT, BOTTOM),
            new Coordinate(LEFT, SAME), // LEFT
            new Coordinate(LEFT, TOP)
    );

    public static final List<String> BASIC_DIRECTIONS_LABELS = List.of(
            "Up",
            "Top Right",
            "Right",
            "Bottom Right",
            "Down",
            "Bottom Left",
            "Left",
            "Top Left"
            );
}
