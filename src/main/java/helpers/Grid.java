package helpers;

import lombok.Getter;

import java.util.List;

import static constants.Constants.BASE_INPUT_DIRECTORY;

@Getter
public class Grid {
    private final List<String> grid;
    private final int width;
    private final int height;

    public Grid(String input) {
        this.grid = importGrid(input);
        this.width = grid.size();
        this.height = grid.get(0).length();
    }

    public String toString() {
        return String.join("\n", this.grid);
    }

    private List<String> importGrid(String input)  {
        return ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
    }

    public char get(int x, int y) {
        return grid.get(y).charAt(x);
    }
}
