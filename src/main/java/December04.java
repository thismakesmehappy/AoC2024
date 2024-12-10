import december04.Crossword;

public class December04 {
    private static final String WORD_TO_FIND = "XMAS";
    public static void main(String[] args) {
        December04 december04 = new December04();
        int resultA = december04.runA("December04.txt");
        System.out.println(resultA);

        int resultB = december04.runB("December04.txt");
        System.out.println(resultB);
    }

    int runA(String input) {
        Crossword crossword = new Crossword(input);
        return crossword.findCountsOfWordInPuzzle(WORD_TO_FIND);
    }

    int runB(String input) {
        Crossword crossword = new Crossword(input);
        return crossword.findCountsOfXmasInPuzzle();
    }
}
