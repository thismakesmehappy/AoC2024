package helpers;


import constants.BasicDirections;
import constants.Directions;

public class DirectionsCross extends Direction {
    private Directions directions = new BasicDirections();
    private Direction direction = new Direction(directions, 0);

    public DirectionsCross(Directions directions, int facing) {
        super(directions, facing);
    }
}
