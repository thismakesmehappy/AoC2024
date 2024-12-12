import helpers.ProcessDataHelpers;
import helpers.ReadFile;

import java.util.List;

import static constants.Constants.BASE_INPUT_DIRECTORY;

public class December02 {
    final static int MIN_INCREASE = 1;
    final static int MAX_INCREASE = 3;

    public static void main(String[] args) {
        December02 december02 = new December02();
        int resultA = december02.runA("December02.txt");
        System.out.println(resultA);

        int resultB = december02.runB("December02.txt");
        System.out.println(resultB);
    }

    int runA(String input) {
        return run(input, false);
    }

    int runB(String input) {
         return run(input, true);
    }

    int run(String input, boolean dampener) {
        List<String> reports = ReadFile.readLines(BASE_INPUT_DIRECTORY, input);
        List<Boolean> safeReports = calculateSafeReports(reports, dampener);
        return (int) safeReports.stream().filter(item -> item).count();
    }

    private List<Boolean> calculateSafeReports(List<String> reports, boolean dampener) {
        return reports.stream().map(report -> isReportSafe(report, dampener)).toList();
    }

    private Boolean isReportSafe(String report, boolean dampener) {
        List<Integer> levels = ProcessDataHelpers.splitLineIntoListOfNumbers(report);
        int growthSign = getGrowthSign(levels);
        return isParsedReportSafe(levels, dampener, growthSign);
    }

    private boolean isParsedReportSafe(List<Integer> levels, boolean dampener, int growthSign) {
        int numberOfLevels = levels.size();
        for (int i = 1; i < numberOfLevels; i++) {
            if(!isGrowthWithinRange(levels.get(i - 1), levels.get(i), growthSign)) {
                // If the dampener is on, test removing the error and turn off the dampener
                if (dampener && isParsedReportSafeRemovingError(levels, growthSign, i)) {
                    dampener = false;
                    i ++;
                    continue;
                }
                // If the dampener is off, fail
                return false;
            }
        }
        return true;
    }
    private boolean isParsedReportSafeRemovingError(List<Integer> levels, int growthSign, int errorIndex) {
        // If the problem is in the last pair, this would have been the first encounter of an error
        if (errorIndex == levels.size() - 1) {
            return true;
        }
        // If the problem is anywhere else on the list, test removing the first item or the second item
        boolean testRemovingFirstItem = isGrowthWithinRange(levels.get(errorIndex), levels.get(errorIndex + 1), growthSign);
        boolean verifyRemovingFirstItemDoesNotBreakStreak = errorIndex ==  1 || isGrowthWithinRange(levels.get(errorIndex - 2), levels.get(errorIndex), growthSign);
        boolean testRemovingSecondItem = isGrowthWithinRange(levels.get(errorIndex - 1), levels.get(errorIndex + 1), growthSign);
        return (testRemovingFirstItem && verifyRemovingFirstItemDoesNotBreakStreak) || testRemovingSecondItem;
    }

    private static boolean isGrowthWithinRange(int first, int second, int growthSign) {
        boolean isGrowthSignCorrect = (second - first) * growthSign > 0;
        int  growth = Math.abs(second - first);
        boolean isGrowthWithinRange = growth >= MIN_INCREASE && growth <= MAX_INCREASE;
        return isGrowthSignCorrect && isGrowthWithinRange;
    }

    private static int getGrowthSign(List<Integer> listOfSpaces) {
        int positive = 0;
        int negative = 0;
        for (int i = 1; i < listOfSpaces.size(); i++) {
            int growth = listOfSpaces.get(i) - listOfSpaces.get(i - 1);
            if (growth > 0) {
                positive++;
            } else if (growth < 0) {
                negative++;
            }
        }
        return (positive > negative ? 1 : -1);
    }
}


