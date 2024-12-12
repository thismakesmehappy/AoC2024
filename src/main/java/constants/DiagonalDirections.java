package constants;

import helpers.Coordinate;

import java.util.List;

public class DiagonalDirections {
    public static final List<Coordinate> DIAGONALS = List.of(
            new Coordinate(1, 1),
            new Coordinate(-1, 1),
            new Coordinate(-1, -1),
            new Coordinate(1, -1)
    );

    public static final List<String> BASIC_DIRECTIONS_LABELS = List.of(
            "Top Right",
            "Top Left",
            "Bottom Left",
            "Bottom Right"
    );

    public static final int TOP_RIGHT = 0;
    public static final int TOP_LEFT = 1;
    public static final int BOTTOM_LEFT = 2;
    public static final int BOTTOM_RIGHT = 3;
}
