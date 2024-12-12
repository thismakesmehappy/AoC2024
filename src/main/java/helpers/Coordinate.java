package helpers;


import lombok.Data;

@Data
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate plus(Coordinate other) {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }

    public boolean equals(Coordinate other) {
        return this.x == other.x && this.y == other.y;
    }
}
