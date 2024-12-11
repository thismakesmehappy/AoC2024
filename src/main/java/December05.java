import helpers.Pair;
import helpers.ReadFile;
import helpers.SafetyManualUpdates;

import java.util.*;
import java.util.HashSet;

import static helpers.Constants.BASE_INPUT_DIRECTORY;

public class December05 {
    public static void main(String[] args) {
        December05 december05 = new December05();
        SafetyManualUpdates safetyManualUpdates = getUpdatesFromInput("December05.txt");

        int resultA = december05.runA(safetyManualUpdates);
        System.out.println(resultA);

        int resultB = december05.runB(safetyManualUpdates);
        System.out.println(resultB);
    }

    int runA(SafetyManualUpdates updates) {
        List<List<Integer>> correctUpdates = updates.getCorrectUpdates();
        return getSumOfCenters(correctUpdates);
    }

    int runB(SafetyManualUpdates updates) {
        List<List<Integer>> correctedUpdates = updates.getCorrectedUpdates();
        return getSumOfCenters(correctedUpdates);
    }

    static SafetyManualUpdates getUpdatesFromInput(String input) {
        List<String> inputLines = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        Pair<List<String>, HashMap<Integer, Set<Integer>>> pageOrderingAndUpdates = pageOrderingAndUpdates(inputLines);
        HashMap<Integer, Set<Integer>> pageOrdering = pageOrderingAndUpdates.second();
        List<String> updates = pageOrderingAndUpdates.first();
        return new SafetyManualUpdates(updates, pageOrdering);
    }

    private int getSumOfCenters(List<List<Integer>> correctUpdates) {
        return correctUpdates.stream()
                .map(update -> {
                    int middle = (update.size() - 1)/2;
                    return update.get(middle);
                })
                .reduce(0, Integer::sum);
    }

    private static Pair<List<String>, HashMap<Integer, Set<Integer>>> pageOrderingAndUpdates(List<String> inputLines) {
        HashMap<Integer, Set<Integer>> pageOrdering = new HashMap<>();
        List<String> updates = new ArrayList<>();
        for (String line:inputLines) {
            if (line.contains(",")) {
                updates.add(line);
            }
            if (line.contains("|")) {
                List<String> twoPages = List.of(line.split("\\|"));
                int first = Integer.parseInt(twoPages.get(0));
                int second = Integer.parseInt(twoPages.get(1));
                pageOrdering.putIfAbsent(first, new HashSet<>());
                pageOrdering.get(first).add(second);
            }
        }
        return new Pair<>(updates, pageOrdering);
    }
}
