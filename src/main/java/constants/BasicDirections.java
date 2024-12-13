package constants;

import helpers.Coordinate;

import java.util.List;
import java.util.Map;

public class BasicDirections implements Directions {
    private static final List<Coordinate> COORDINATES = List.of(
            new Coordinate(NO_MOVE, MOVE_DOWN), // DOWN
            new Coordinate(MOVE_LEFT, NO_MOVE), // LEFT
            new Coordinate(NO_MOVE, MOVE_UP), // UP
            new Coordinate(MOVE_RIGHT, NO_MOVE) // RIGHT
    );
    private static final List<String> LABELS = List.of(
            "Down",
            "Left",
            "Up",
            "Right"
    );
    private static final Map<String, Integer> DIRECTIONS  = Map.of(
            "DOWN", 0,
            "LEFT", 1,
            "UP", 2,
            "RIGHT", 3
    );
    public final static String UP = "UP";
    public final static String DOWN = "DOWN";
    public final static String LEFT = "LEFT";
    public final static String RIGHT = "RIGHT";

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
