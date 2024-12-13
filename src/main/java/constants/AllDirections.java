package constants;

import helpers.Coordinate;

import java.util.List;
import java.util.Map;

public class AllDirections implements Directions {
    public static final List<Coordinate> COORDINATES = List.of(
            new Coordinate(NO_MOVE, MOVE_UP), // UP
            new Coordinate(MOVE_RIGHT, MOVE_UP),
            new Coordinate(MOVE_RIGHT, NO_MOVE), // RIGHT
            new Coordinate(MOVE_RIGHT, MOVE_DOWN),
            new Coordinate(NO_MOVE, MOVE_DOWN), // DOWN
            new Coordinate(MOVE_LEFT, MOVE_DOWN),
            new Coordinate(MOVE_LEFT, NO_MOVE), // LEFT
            new Coordinate(MOVE_LEFT, MOVE_UP)
    );
    public static final List<String> LABELS = List.of(
            "Up",
            "Top Right",
            "Right",
            "Bottom Right",
            "Down",
            "Bottom Left",
            "Left",
            "Top Left"
    );
    private static final Map<String, Integer> DIRECTIONS = Map.of(
            "UP", 0,
            "TOP_RIGHT", 1,
            "RIGHT", 2,
            "BOTTOM_RIGHT", 3,
            "DOWN", 4,
            "BOTTOM_LEFT", 5,
            "LEFT", 6,
            "TOP_LEFT", 7
    );
    public final static String UP = "UP";
    public static final String TOP_RIGHT = "TOP_RIGHT";
    public final static String RIGHT = "RIGHT";
    public static final String BOTTOM_RIGHT = "BOTTOM_RIGHT";
    public final static String DOWN = "DOWN";
    public static final String BOTTOM_LEFT = "BOTTOM_LEFT";
    public final static String LEFT = "LEFT";
    public static final String TOP_LEFT = "TOP_LEFT";

    public List<Coordinate> COORDINATES() {
        return COORDINATES;
    }
    public Coordinate COORDINATE(int coordinate) {
        return COORDINATES.get(coordinate);
    }
    public Coordinate COORDINATE(String direction) {
        return COORDINATES.get(DIRECTIONS.get(direction));
    }
    public List<String> LABELS() {
        return LABELS;
    }
    public String LABEL(int label) {
        return LABELS.get(label);
    }
    public int SIZE() {
        return COORDINATES.size();
    }
    public Map<String, Integer> DIRECTIONS(String direction) {
        return DIRECTIONS;
    }
    public int DIRECTION(String direction) {
        return DIRECTIONS.get(direction);
    }
}
