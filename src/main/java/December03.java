import helpers.Pair;
import helpers.ReadFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helpers.Constants.BASE_INPUT_DIRECTORY;

public class December03 {
    private static final Pattern PATTERN_MUL = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    public static void main(String[] args) {
        December03 december03 = new December03();
        int resultA = december03.runA("December03.txt");
        System.out.println(resultA);

        int resultB = december03.runB("December03.txt");
        System.out.println(resultB);
    }

    int runA(String input) {
        return run(input, false);
    }

    int runB(String input) {
        return run(input, true);
    }

    int run(String input, boolean shouldCleanUp) {
        List<String> memoryLines = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        String memory = cleanUpMemory(memoryLines, shouldCleanUp);
        List<Pair<Integer, Integer>> multipliers = findPairOfFactors(memory);
        return sumOfMultiplications(multipliers);
    }

    private static Integer sumOfMultiplications(List<Pair<Integer, Integer>> multipliers) {
        return multipliers
                .stream()
                .map(multiples -> multiples.getFirst() * multiples.getSecond())
                .reduce(0, Integer::sum);
    }

    private String cleanUpMemory(List<String> memoryLines, boolean shouldCleaUp) {
        String memory = String.join("", memoryLines);
        if (shouldCleaUp) {
            memory = cleanup(memory);
        }
        return memory;
    }

    private String cleanup(String memory) {
        do {
            Pair<Integer, Integer> dontDoIndices = findLocationsToSnip(memory);
            int indexDont = dontDoIndices.getFirst();
            int indexDo = dontDoIndices.getSecond();
            if (indexDont == -1) {
                break;
            }
            memory = memory.substring(0, indexDont) + memory.substring(indexDo);
        } while (memory.contains("don't()"));
        return memory;
    }

    private Pair<Integer, Integer> findLocationsToSnip(String memory) {
        String beginOfSnip = "don't()";
        String endOfSnip = "do()";
        int indexDont = memory.indexOf(beginOfSnip);
        int indexDo = memory.indexOf(endOfSnip, indexDont);
        indexDo = indexDo == -1 ? memory.length() - 1 : indexDo + 4;
        return new Pair<>(indexDont, indexDo);
    }

    public static List<Pair<Integer, Integer>> findPairOfFactors(String input) {
        List<Pair<Integer, Integer>> results = new ArrayList<>();
        Matcher matcher = PATTERN_MUL.matcher(input);
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            results.add(new Pair<>(x, y));
        }
        return results;
    }
}
