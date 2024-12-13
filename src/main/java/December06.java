import errors.GuardIsStuckException;
import helpers.Coordinate;
import helpers.Grid;
import helpers.GuardGallivant;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class December06 {
    public static void main(String[] args) throws GuardIsStuckException {
        December06 december06 = new December06();

        int resultA = december06.runA("December06.txt");
        System.out.println(resultA);

        int resultB = december06.runB("December06.txt");
        System.out.println(resultB);
    }

    int runA(String input) throws GuardIsStuckException {
        GuardGallivant gallivant = new GuardGallivant(input);
        gallivant.escape();
        return gallivant.getUniqueSteps();

    }

    int runB(String input) throws GuardIsStuckException {
        Grid grid = new Grid(input);
        GuardGallivant gallivant = new GuardGallivant(grid);
        gallivant.escape();
        Set<Coordinate> path = new HashSet<>(gallivant.getVisited());

        int maxStepsBeforeExit = 4 * path.size();
        gallivant.setMaxStepsBeforeExit(maxStepsBeforeExit);
        int loopCount = 0;
        for (Coordinate coordinate : path) {
            log.debug("{}", coordinate);
            int x = coordinate.getX();
            int y = coordinate.getY();
            grid.set(x, y, GuardGallivant.getCOLUMN_SYMBOL());
            try {

                gallivant.escape();
            } catch (GuardIsStuckException e) {
                log.debug("LOOP: {}", coordinate);
                loopCount++;
            }
            grid.set(x, y, GuardGallivant.getEMPTY_SYMBOL());
        }
        return loopCount;
    }
}
