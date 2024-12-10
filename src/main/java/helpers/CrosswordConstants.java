package helpers;

import java.util.List;

public class CrosswordConstants {
    final static int RIGHT = 1;
    final static int DOWN = 1;
    final static int LEFT = -1;
    final static int UP = -1;
    final static int SAME_ROW = 0;
    final static int SAME_COLUMN = 0;
    public final static List<Pair<Integer, Integer>> DIRECTONS =
            List.of(
                    new Pair<> (SAME_COLUMN, UP),
                    new Pair<> (RIGHT, UP),
                    new Pair<> (RIGHT, SAME_ROW),
                    new Pair<> (RIGHT, DOWN),
                    new Pair<> (SAME_COLUMN, DOWN),
                    new Pair<> (LEFT, DOWN),
                    new Pair<> (LEFT, SAME_ROW),
                    new Pair<> (LEFT, UP)
            );

    public final static List<Pair<Integer, Integer>> DIAGONALS =
            List.of(
                    new Pair<> (RIGHT, UP),
                    new Pair<> (RIGHT, DOWN),
                    new Pair<> (LEFT, DOWN),
                    new Pair<> (LEFT, UP)
            );
}
