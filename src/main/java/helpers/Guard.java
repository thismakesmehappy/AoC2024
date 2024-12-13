package helpers;

import constants.BasicDirections;
import constants.Directions;
import lombok.Getter;

public class Guard {
    @Getter
    private final Coordinate position;
    private final Direction facing;

    public Guard(int x, int y) {
        this.position = new Coordinate(x, y);
        this.facing = new Direction(new BasicDirections(), 2);
    }

    public Guard(int x, int y, int facing) {
        this.position = new Coordinate(x, y);
        this.facing = new Direction(new BasicDirections(), facing);
    }

    public Guard(int x, int y, int facing, Directions directions) {
        this.position = new Coordinate(x, y);
        this.facing = new Direction(directions, facing);
    }

    public Guard(Guard guard) {
        this.position = new Coordinate(guard.position);
        this.facing = new Direction(guard.getDirections(), guard.getFacing());
    }

    public void setX(int x) {
        this.position.setX(x);
    }

    public void setY(int y) {
        this.position.setY(y);
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public int getFacing() {
        return this.facing.getFacing();
    }

    public void setFacing(int facing) {
        this.facing.setFacing(facing);
    }

    public void setFacing() {
        this.facing.setFacing(2);
    }

    public Coordinate getCurrentPosition() {
        return new Coordinate(position);
    }

    public String getFacingLabel() {
        return this.facing.getLabel();
    }

    public void step() {
        Coordinate ahead = this.getAhead();
        this.setX(ahead.getX());
        this.setY(ahead.getY());
    }

    public void turnRight() {
        this.facing.turnRight();
    }

    public int getTurnRight(int facing) {
        return this.facing.getTurnRight(facing);
    }

    public int getTurnBack(int facing) {
        return this.facing.getTurnBack(facing);
    }

    public int getTurnRight() {
        return this.facing.getTurnRight(this.facing.getFacing());
    }

    public int getTurnBack() {
        return this.facing.getTurnBack(this.facing.getFacing());
    }

    public void turnLeft() {
        facing.turnLeft();
    }

    public Coordinate getAhead() {
        Coordinate direction = this.facing.getDirection();
        return this.position.plus(direction);
    }

    public boolean equals(Guard other) {
        return this.getX() == other.getX()
                && this.getY() == other.getY()
                && this.facing.getFacing() == other.facing.getFacing();
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) %s", this.getX(), this.getY(), this.facing.getLabel());
    }

    public boolean equals(Object other) {
        if (other instanceof Guard) {
            return this.position.equals(((Guard) other).position)
                    && this.facing.getFacing() == ((Guard) other).facing.getFacing();
        }
        return false;
    }

    public Directions getDirections() {
        return this.facing.getDirections();
    }
}
