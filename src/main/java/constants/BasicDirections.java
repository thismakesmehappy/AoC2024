package constants;

import helpers.Coordinate;

import java.util.List;

public class BasicDirections {
    public static final List<Coordinate> DIRECTIONS = List.of(
            new Coordinate(0, 1), // DOWN
            new Coordinate(-1, 0), // LEFT
            new Coordinate(0, -1), // UP
            new Coordinate(1, 0) // RIGHT
    );

    public static final List<String> BASIC_DIRECTIONS_LABELS = List.of(
            "Down",
            "Left",
            "Up",
            "Right"
    );

    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int RIGHT = 3;
}
