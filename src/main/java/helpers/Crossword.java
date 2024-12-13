package helpers;

import constants.AllDirections;
import constants.DiagonalDirections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crossword {
    private final Grid grid;
    private final List<Coordinate> DIAGONALS = new DiagonalDirections().COORDINATES();
    private final List<Coordinate> ALL_DIRECTIONS = new AllDirections().COORDINATES();

    public Crossword(String input) {
        this.grid = new Grid(input);
    }

    public String toString() {
        return this.grid.toString();
    }

    public int findCountsOfWordInPuzzle(String wordToFind) {
        int totalCount = 0;
        for (int y = 0; y < grid.getWidth(); y ++) {
            for (int x = 0; x < grid.getHeight(); x ++) {
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
        for (int y = 0; y < grid.getWidth(); y ++) {
            for (int x = 0; x < grid.getHeight(); x ++) {
                xmasCenters.addAll(getCentersOfXmasForPosition(x, y));
            }
        }
        return xmasCenters;
    }

    private List<Pair<Integer, Integer>> getCentersOfXmasForPosition(int x, int y) {
        String word = "MAS";
        List<Pair<Integer, Integer>> centers = new ArrayList<>();
        for (Coordinate direction : DIAGONALS) {
            boolean found = 1 == findWord(word, x, y, direction.getX(), direction.getY());
            if (found) {
                int centerX = x + direction.getX();
                int centerY = y + direction.getY();
                centers.add(new Pair<>(centerX, centerY));
            }
        }
        return centers;
    }

    private int getCountsOfWordFromPosition(String wordToFind, int x, int y) {
        int count = 0;
        for (Coordinate direction : ALL_DIRECTIONS) {
            count += findWord(wordToFind, x, y, direction.getX(), direction.getY());
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
            if (grid.isPositionOutOfBounds(currentX, currentY)
                    || isWrongLetter(currentY, currentX, wordToFind.charAt(i))) {
                return 0;
            }
            currentX += xDir;
            currentY += yDir;
        }
        return 1;
    }

    private boolean isWrongLetter(int currentY, int currentX, char characterToFind) {
        return grid.get(currentX, currentY) != characterToFind;
    }
}