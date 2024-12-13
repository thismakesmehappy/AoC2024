package helpers;

import errors.GuardIsStuckException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class GuardGallivant {
    private static final List<Character> GUARD_SYMBOL = List.of('⌄', '<', '^', '>');
    @Getter
    private static final char COLUMN_SYMBOL = '#';
    @Getter
    private static final char EMPTY_SYMBOL = '.';
    private static final char EXIT_SYMBOL = 'X';
    @Getter
    @Setter
    private Grid grid;
    @Getter
    private Guard guard;
    @Getter
    private int steps;
    @Getter
    private int uniqueSteps;
    @Getter
    private HashSet<Coordinate> visited;
    @Getter
    private List<String> sequence;
    @Getter
    List<String> visitedWithFacing = new ArrayList<>();
    private Coordinate startingPosition;
    @Setter
    private int maxStepsBeforeExit;

    public GuardGallivant(String input) {
        this.grid = new Grid(input);
        commonInit();
    }

    public GuardGallivant(List<String> input) {
        this.grid = new Grid(input);
        commonInit();
    }

    public GuardGallivant(Grid grid) {
        this.grid = grid;
        System.out.println(this.grid.hashCode());
        commonInit();
    }

    private void commonInit() {
        this.startingPosition = findStartingPosition();
        assert startingPosition != null;
        this.visited = new HashSet<>();
        this.sequence = new ArrayList<>(List.of(grid.toString()));
        this.guard = new Guard(startingPosition.getX(), startingPosition.getY());
        this.maxStepsBeforeExit = this.grid.getHeight() * this.grid.getWidth();
    }

    public String toString() {
        return this.grid.toString();
    }

    public void takeStep() throws GuardIsStuckException {
        turnAsNeeded(); // TODO: Return whether guard turned
        moveForward();
        this.sequence.add(this.grid.toString());
    }

    public void escape() throws GuardIsStuckException {
        resetGuard();
        while (!isGuardOutOfBounds()) {
            takeStep();
        }
        this.uniqueSteps = visited.size();
    }

    private void turnAsNeeded() throws GuardIsStuckException {
        int turns = 0;
        while (shouldTurnRight() && turns < 4) {
            this.guard.turnRight();
            turns++;
        }

        if (turns == 4) {
            throw new GuardIsStuckException("Guard is trapped");
        }
    }

    private void moveForward() throws GuardIsStuckException {
        while (canMoveForward()) {
            String guardState = this.guard.toString();
            boolean contains = this.visitedWithFacing.contains(guardState);
            if (contains || steps >= this.maxStepsBeforeExit) {
                throw new GuardIsStuckException("Guard is stuck in a loop");
            }
            this.visited.add(getGuardCurrentPosition());
            this.visitedWithFacing.add(this.guard.toString());
            swapSymbolsForStep(); // TODO: Change current grid to show path and turns
            this.guard.step();
            this.steps++;
        }
    }

    private void swapSymbolsForStep() {
        Coordinate current = this.guard.getPosition();
        Coordinate ahead = this.guard.getAhead();
        boolean currentOutOfBounds = isGuardOutOfBounds();
        boolean aheadOutOfBounds = grid.isPositionOutOfBounds(ahead);
        if (!currentOutOfBounds) {
            this.grid.set(current.getX(), current.getY(), EMPTY_SYMBOL);
        }
        if (!aheadOutOfBounds) {
            Character guardSymbol = GUARD_SYMBOL.get(guard.getFacing());
            this.grid.set(ahead.getX(), ahead.getY(), guardSymbol);
        }
        if (!grid.isPositionOutOfBounds(current) && grid.isPositionOutOfBounds(ahead)) {
            this.grid.set(current.getX(), current.getY(), EXIT_SYMBOL);
        }
    }

    public boolean canMoveForward() {
        Coordinate ahead = this.guard.getAhead();
        Coordinate current = this.guard.getPosition();
        char whatIsAhead = this.grid.get(ahead.getX(), ahead.getY());
        return !grid.isPositionOutOfBounds(current)
                && whatIsAhead != COLUMN_SYMBOL;
    }

    public boolean shouldTurnRight() {
        Coordinate ahead = this.guard.getAhead();
        char whatIsAhead = 'X';
        if (!grid.isPositionOutOfBounds(ahead)) {
            whatIsAhead = this.grid.get(ahead.getX(), ahead.getY());
        }
        return whatIsAhead == COLUMN_SYMBOL;
    }

    private Coordinate findStartingPosition() {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (GUARD_SYMBOL.contains(grid.get(x, y))) {
                    return new Coordinate(x, y);
                }
            }
        }
        return null;
    }

    public boolean isGuardOutOfBounds() {
        return grid.isPositionOutOfBounds(guard.getPosition());
    }

    public Coordinate getGuardCurrentPosition() {
        return guard.getCurrentPosition();
    }

    public void resetGuard() {
        this.guard.setX(this.startingPosition.getX());
        this.guard.setY(this.startingPosition.getY());
        this.guard.setFacing();
        this.steps = 0;
        this.visited.clear();
        this.visitedWithFacing.clear();
        this.sequence.clear();
        this.sequence.add(this.grid.toString());
    }
}
