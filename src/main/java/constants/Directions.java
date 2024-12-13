package constants;

import helpers.Coordinate;

import java.util.List;
import java.util.Map;

public interface Directions {
    int MOVE_UP = -1;
    int MOVE_RIGHT = 1;
    int MOVE_DOWN = 1;
    int MOVE_LEFT = -1;
    int NO_MOVE = 0;

    List<Coordinate> COORDINATES = List.of();// UP)
    List<String> LABELS = List.of();
    Map<String, Integer> DIRECTIONS = Map.of();

    default List<Coordinate> COORDINATES() {
        return COORDINATES;
    }
    default Coordinate COORDINATE(int coordinate) {
        return COORDINATES.get(coordinate);
    }
    default Coordinate COORDINATE(String direction) {
        return COORDINATES.get(DIRECTIONS.get(direction));
    }
    default List<String> LABELS() {
        return LABELS;
    }
    default String LABEL(int label) {
        return LABELS.get(label);
    }
    default int SIZE() {
        return COORDINATES.size();
    }
    default Map<String, Integer> DIRECTIONS(String direction) {
        return DIRECTIONS;
    }
    default int DIRECTION(String direction) {
        return DIRECTIONS.get(direction);
    }
}
