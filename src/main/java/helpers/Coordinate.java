package helpers;


import lombok.Data;

import java.io.Serializable;

@Data
public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public Coordinate plus(Coordinate other) {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }
    public Coordinate minus(Coordinate other) {
        return new Coordinate(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (other instanceof Coordinate) {
            return this.x == ((Coordinate) other).x && this.y == ((Coordinate) other).y;
        }
        return false;
    }

    public int hashCode() {
        return this.x * 100000 + this.y;
    }

    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

    @Override
    public int compareTo(Coordinate other) {
        // First compare x
        int xCompare = Integer.compare(this.x, other.x);
        if (xCompare != 0) {
            return xCompare;
        }
        // If x is equal, compare y
        return Integer.compare(this.y, other.y);
    }
}
