package helpers;

import constants.BasicDirections;
import constants.Directions;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Direction {
    private int facing;
    @Getter
    private Directions directions = new BasicDirections();

    public Direction(Directions directions, int facing) {
        this.directions = directions;
        this.facing = facing;
    }

    public void turnClockwise() {
        this.facing = getTurnClockwise(this.facing);
    }

    public int getTurnClockwise(int facing) {
        return (facing + 1) % directions.SIZE();
    }

    public int getTurnRight(int facing) {
        return (facing + 1) % directions.SIZE();
    }

    public int getTurnBack(int facing) {
        return (facing + (directions.SIZE() / 2)) % directions.SIZE();
    }

    public void turnRight() {
        turnClockwise();
    }

    public void turnCounterClockwise() {
        facing = ((facing - 1) + directions.SIZE()) % directions.SIZE();
    }

    public void turnLeft() {
        turnCounterClockwise();
    }

    public Coordinate getDirection() {
        return directions.COORDINATE(facing);
    }

    public String getLabel() {
        return directions.LABEL(facing);
    }

    public void setFacing(int facing) {this.facing = facing;}
}
