package december04;

import helpers.Pair;
import helpers.ReadFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static helpers.Constants.BASE_INPUT_DIRECTORY;
import static helpers.CrosswordConstants.DIAGONALS;
import static helpers.CrosswordConstants.DIRECTONS;

public class Crossword {
    private final List<String> grid;
    private final int width;
    private final int height;

    public Crossword(String input) {
        this.grid = importGrid(input);
        this.width = grid.size();
        this.height = grid.get(0).length();
    }

    public String toString() {
        return String.join("\n", this.grid);
    }

    public int findCountsOfWordInPuzzle(String wordToFind) {
        int totalCount = 0;
        for (int y = 0; y < this.width; y ++) {
            for (int x = 0; x < this.height; x ++) {
                totalCount += getCountsOfWordFromPosition(wordToFind, x, y);
            }
        }
        return totalCount;
    }

    public int findCountsOfXmasInPuzzle() {
        List<Pair<Integer, Integer>> xmasCenters = findCentersForAllInstancesOfMas();
        Set<String> xMas = findAllOverlappingMas(xmasCenters);
        return xMas.size();
    }

    private static Set<String> findAllOverlappingMas(List<Pair<Integer, Integer>> xmasCenters) {
        Set<String> seen = new HashSet<>();
        Set<String> xMas = new HashSet<>();
        for (Pair<Integer, Integer> xmasCenter : xmasCenters) {
            if (seen.contains(xmasCenter.toString())) {
                xMas.add(xmasCenter.toString());
            } else {
                seen.add(xmasCenter.toString());
            }
        }
        return xMas;
    }

    private List<Pair<Integer, Integer>> findCentersForAllInstancesOfMas() {
        List<Pair<Integer, Integer>> xmasCenters = new ArrayList<>();
        for (int y = 0; y < this.width; y ++) {
            for (int x = 0; x < this.height; x ++) {
                xmasCenters.addAll(getCentersOfXmasForPosition(x, y));
            }
        }
        return xmasCenters;
    }

    private List<Pair<Integer, Integer>> getCentersOfXmasForPosition(int x, int y) {
        String word = "MAS";
        List<Pair<Integer, Integer>> centers = new ArrayList<>();
        for (Pair<Integer, Integer> direction : DIAGONALS) {
            boolean found = 1 == findWord(word, x, y, direction.first(), direction.second());
            if (found) {
                int centerX = x + direction.first();
                int centerY = y + direction.second();
                centers.add(new Pair<>(centerX, centerY));
            }
        }
        return centers;
    }

    private int getCountsOfWordFromPosition(String wordToFind, int x, int y) {
        int count = 0;
        for (Pair<Integer, Integer> direction : DIRECTONS) {
            count += findWord(wordToFind, x, y, direction.first(), direction.second());
            if (count > 0) {
            }
        }
        return count;
    }

    int findWord(String wordToFind, int x, int y, int xDir, int yDir) {
        int wordSize = wordToFind.length();
        int currentX = x;
        int currentY = y;

        for (int i = 0; i < wordSize; i++) {
            if (isPositionOutOfBounds(currentX, currentY)
                    || isWrongLetter(currentY, currentX, wordToFind.charAt(i))) {
                return 0;
            }
            currentX += xDir;
            currentY += yDir;
        }
        return 1;
    }

    private boolean isWrongLetter(int currentY, int currentX, char characterToFind) {
        return grid.get(currentY).charAt(currentX) != characterToFind;
    }

    private List<String> importGrid(String input)  {
        return ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
    }

    private boolean isPositionOutOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }
}