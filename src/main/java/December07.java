import helpers.BridgeOperators;
import helpers.ReadFile;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static constants.Constants.BASE_INPUT_DIRECTORY;

public class December07 {
    public static void main(String[] args) {
        December07 december07 = new December07();

        long resultA = december07.runA("December07.txt");
        System.out.println(resultA);

        long resultB = december07.runB("December07.txt");
        System.out.println(resultB);
    }

    long runA(String input) {
        List<String> lines = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        AtomicLong total = new AtomicLong();
        lines.forEach(line -> {
            BridgeOperators bridgeOperators = new BridgeOperators(line);
            if (bridgeOperators.canOperate()) {
                total.addAndGet(bridgeOperators.getTotal());
            }
        });
        return total.get();
    }

    long runB(String input) {
        List<String> lines = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        AtomicLong total = new AtomicLong();
        lines.forEach(line -> {
            BridgeOperators bridgeOperators = new BridgeOperators(line);
            if (bridgeOperators.canConcatenate()) {
                total.addAndGet(bridgeOperators.getTotal());
            }
        });
        return total.get();
    }
}
