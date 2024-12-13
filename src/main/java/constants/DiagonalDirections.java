package constants;

import helpers.Coordinate;

import java.util.List;
import java.util.Map;

public class DiagonalDirections implements Directions {
    private static final List<Coordinate> COORDINATES = List.of(
            new Coordinate(MOVE_RIGHT, MOVE_DOWN),
            new Coordinate(MOVE_LEFT, MOVE_DOWN),
            new Coordinate(MOVE_LEFT, MOVE_UP),
            new Coordinate(MOVE_RIGHT, MOVE_UP)
    );
    private static final List<String> LABELS = List.of(
            "Top Right",
            "Top Left",
            "Bottom Left",
            "Bottom Right"
    );
    private static final Map<String, Integer> DIRECTIONS  = Map.of(
            "TOP_RIGHT", 0,
            "TOP_LEFT", 1,
            "BOTTOM_LEFT", 2,
            "BOTTOM_RIGHT", 3
    );
    public static final String TOP_RIGHT = "TOP_RIGHT";
    public static final String TOP_LEFT = "TOP_LEFT";
    public static final String BOTTOM_LEFT = "BOTTOM_LEFT";
    public static final String BOTTOM_RIGHT = "BOTTOM_RIGHT";

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
