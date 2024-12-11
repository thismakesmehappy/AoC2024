package helpers;

import lombok.Data;

import java.util.*;
import java.util.Collections;


@Data
public class SafetyManualUpdates {
    private final List<List<Integer>> correctUpdates = new ArrayList<>();
    private final List<List<Integer>> incorrectUpdates = new ArrayList<>();
    private final List<List<Integer>> correctedUpdates = new ArrayList<>();
    private final List<List<Integer>> allUpdates = new ArrayList<>();
    private HashMap<Integer, Set<Integer>> pageOrdering = new HashMap<>();
    private boolean hasCorrected;
    public final static boolean KEEP_INCORRECT = false;
    public final static boolean POPULATE_CORRECTED = true;

    public SafetyManualUpdates(List<List<Integer>> correctUpdates, List<List<Integer>> incorrectUpdates, List<List<Integer>> correctedUpdates, boolean isCorrected) {
        this.correctUpdates.addAll(correctUpdates);
        this.incorrectUpdates.addAll(incorrectUpdates);
        this.correctedUpdates.addAll(correctedUpdates);
        this.hasCorrected = isCorrected;
    }

    public SafetyManualUpdates(
            List<String>updates, HashMap<Integer, Set<Integer>> pageOrdering) {
        buildFromInput(updates, pageOrdering, true);
    }

    public SafetyManualUpdates(
            List<String>updates, HashMap<Integer, Set<Integer>> pageOrdering, boolean shouldCorrectIncorrectUpdates) {
        buildFromInput(updates, pageOrdering, shouldCorrectIncorrectUpdates);
    }

    private void buildFromInput(List<String>updates, HashMap<Integer, Set<Integer>> pageOrdering, boolean shouldCorrectIncorrectUpdates) {
        SafetyManualUpdates safetyManualUpdates = separateCorrectUpdatesAndIncorrectUpdates(pageOrdering, updates, shouldCorrectIncorrectUpdates);
        this.pageOrdering = (HashMap<Integer, Set<Integer>>) pageOrdering.clone();
        this.correctUpdates.addAll(safetyManualUpdates.getCorrectUpdates());
        this.incorrectUpdates.addAll(safetyManualUpdates.getIncorrectUpdates());
        this.correctedUpdates.addAll(safetyManualUpdates.getCorrectedUpdates());
        this.hasCorrected = safetyManualUpdates.isHasCorrected();
    }

    private SafetyManualUpdates separateCorrectUpdatesAndIncorrectUpdates(
            HashMap<Integer, Set<Integer>> pageOrdering, List<String> updates, boolean shouldCorrectIncorrectUpdates) {
        List<List<Integer>> correctUpdates = new ArrayList<>();
        List<List<Integer>> incorrectUpdates = new ArrayList<>();
        List<List<Integer>> correctedUpdates = new ArrayList<>();
        for (String update:updates) {
            List<Integer> updateList = Arrays.stream(update.split(",")).map(Integer::parseInt).toList();
            int incorrectPage = incorrectPageIndex(updateList, pageOrdering);
            if (incorrectPage == -1) {
                correctUpdates.add(updateList);
            } else {
                incorrectUpdates.add(updateList);
                if (shouldCorrectIncorrectUpdates){
                    List<Integer> corrected = corrected(updateList, incorrectPage, pageOrdering);
                    correctedUpdates.add(corrected);
                }
            }
        }
        return new SafetyManualUpdates(correctUpdates, incorrectUpdates, correctedUpdates, shouldCorrectIncorrectUpdates);
    }

    private int incorrectPageIndex(List<Integer> updateList, HashMap<Integer, Set<Integer>> pageOrdering) {
        List<Integer> seen = new ArrayList<>();
        int updateListSize = updateList.size();
        for (int i = 0; i < updateListSize; i++) {
            int page = updateList.get(i);
            Set<Integer> shouldFollow = pageOrdering.getOrDefault(page, new HashSet<>());
            boolean seenContains = seenContainsAnyInSet(seen, shouldFollow);
            if (seenContains) {
                return i;
            }
            seen.add(page);
        }
        return -1;
    }

    private boolean seenContainsAnyInSet(List<Integer> seen, Set<Integer> shouldFollow) {
        for (Integer page:shouldFollow) {
            if (seen.contains(page)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> corrected(List<Integer> updateList, int incorrectPageIndex, HashMap<Integer, Set<Integer>> pageOrdering) {
        List<Integer> updatedList = new ArrayList<>(updateList);
        while (incorrectPageIndex != -1) {
            updatedList = correctOnce(updatedList, incorrectPageIndex);
            incorrectPageIndex = incorrectPageIndex(updatedList, pageOrdering);
        }
        return updatedList;
    }

    private static List<Integer> correctOnce(List<Integer> updateList, int incorrectPageIndex) {
        List<Integer> updatedList = new ArrayList<>(updateList);
        Collections.swap(updatedList, incorrectPageIndex, incorrectPageIndex - 1);
        return updatedList;
    }
}