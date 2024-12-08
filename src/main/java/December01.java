import helpers.Pair;
import helpers.ReadFile;

import java.util.ArrayList;
import java.util.List;

import static helpers.Constants.BASE_INPUT_DIRECTORY;

public class December01 {
    public static void main(String[] args) {
        int resultA = new December01().runA("December01.txt");
        System.out.println(resultA);

        int resultB = new December01().runB("December01.txt");
        System.out.println(resultB);
    }

    int runA(String input) {
        List<String> line = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        Pair<List<Integer>, List<Integer>> lists = splitLinesIntoTwoLists(line);
        List<Integer> spaces = getListOfOfSpaces(lists);
        return spaces.stream().reduce(0, Integer::sum);
    }

    int runB(String input) {
        List<String> line = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        Pair<List<Integer>, List<Integer>> lists = splitLinesIntoTwoLists(line);
        List<Integer> similarityScores = getListOfOfSimilarityScores(lists);
        return similarityScores.stream().reduce(0, Integer::sum);
    }

    private List<Integer> getListOfOfSimilarityScores(Pair<List<Integer>, List<Integer>> lists) {
        List<Integer> left = lists.getFirst();
        List<Integer> right = lists.getSecond();
        List<Integer> similarityScores =  new ArrayList<>();
        for(int i = 0; i< left.size(); i++){
            Integer value = left.get(i);
            int countRepetitions = (int) right.stream().filter(x -> x.equals(value)).count();
            similarityScores.add(value * countRepetitions);
        }
        return similarityScores;
    }

    private static List<Integer> getListOfOfSpaces(Pair<List<Integer>, List<Integer>> lists) {
        List<Integer> left = lists.getFirst().stream().sorted().toList();
        List<Integer> right = lists.getSecond().stream().sorted().toList();
        List<Integer> spaces =  new ArrayList<>();
        for(int i = 0; i< left.size(); i++){
            Integer space = Math.abs(left.get(i) - right.get(i));
            spaces.add(space);
        }
        return spaces;
    }

    private Pair<Integer, Integer> splitLineIntoTwoNumbers(String line) {
        String[] split = line.split("[ \\t\\n\\r\\f]+", 2);
        Integer first = Integer.parseInt(split[0]);
        Integer second = Integer.parseInt(split[1]);
        return new Pair<>(first, second);
    }

    private Pair<List<Integer>, List<Integer>> splitLinesIntoTwoLists(List<String> lines) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String line : lines) {
            Pair<Integer, Integer> split = splitLineIntoTwoNumbers(line);
            left.add(split.getFirst());
            right.add(split.getSecond());
        }
        return new Pair<>(left, right);
    }
}


