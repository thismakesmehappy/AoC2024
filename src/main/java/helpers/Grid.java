package helpers;

import lombok.Getter;

import java.util.List;

import static constants.Constants.BASE_INPUT_DIRECTORY;

@Getter
public class Grid {
    private final List<char[]> grid;
    private final int width;
    private final int height;

    public Grid(String input) {
        this.grid = importGrid(input);
        this.width = grid.size();
        this.height = grid.get(0).length;
    }

    public Grid(List<String> input) {
        this.grid = input.stream().map(String::toCharArray).toList();
        this.width = grid.size();
        this.height = grid.get(0).length;
    }

    public Grid(Grid grid) {
        this.grid = grid.getGrid().stream().map(char[]::clone).toList();
        this.width = grid.getWidth();
        this.height = grid.getHeight();
    }


    public String toString() {
        return String.join("\n", this.grid.stream().map(String::new).toList());
    }

    private List<char[]> importGrid(String input) {
        return ReadFile.readLines(BASE_INPUT_DIRECTORY, input).stream().map(String::toCharArray).toList();
    }

    public char get(int x, int y) {
        if (isPositionOutOfBounds(x, y)) {
            return "\n".toCharArray()[0];
        }
        return grid.get(y)[x];
    }

    public char get(Coordinate position) {
        if (isPositionOutOfBounds(position.getX(), position.getY())) {
            return "\n".toCharArray()[0];
        }
        return get(position.getX(), position.getY());
    }

    public void set(int x, int y, char character) {
        grid.get(y)[x] = character;
    }

    public boolean isPositionOutOfBounds(int x, int y) {
        return x < 0
                || x >= this.width
                || y < 0
                || y >= this.height;
    }

    public boolean isPositionOutOfBounds(Coordinate coordinate) {
        return isPositionOutOfBounds(coordinate.getX(), coordinate.getY());
    }
}
