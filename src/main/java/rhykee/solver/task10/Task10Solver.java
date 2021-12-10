package rhykee.solver.task10;

import rhykee.solver.Challenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

public class Task10Solver implements Challenge {

    private static final String OPENING_BRACKETS = "([{<";
    private static final String CLOSING_BRACKETS = ")]}>";
    private static final Map<Character, Integer> ERROR_SCORE = createErrorScoreMap();
    private static final Map<Character, Integer> AUTOCOMPLETE_SCORE = createAutoCompleteScoreMap();

    private static Map<Character, Integer> createErrorScoreMap() {
        Map<Character, Integer> scoreMap = new HashMap<>();
        scoreMap.put(')', 3);
        scoreMap.put(']', 57);
        scoreMap.put('}', 1197);
        scoreMap.put('>', 25137);
        return scoreMap;
    }

    private static Map<Character, Integer> createAutoCompleteScoreMap() {
        Map<Character, Integer> scoreMap = new HashMap<>();
        scoreMap.put(')', 1);
        scoreMap.put(']', 2);
        scoreMap.put('}', 3);
        scoreMap.put('>', 4);
        return scoreMap;
    }

    @Override
    public void part1(List<String> lines) {
        Map<Character, Integer> invalidCount = initCountMap();
        lines.forEach(chunk -> countInvalidChars(invalidCount, chunk));
        AtomicLong sum = new AtomicLong(0);
        invalidCount.forEach((character, count) -> sum.addAndGet((long) ERROR_SCORE.get(character) * count));
        System.out.println("Day 10 1/2: " + sum);
    }

    @Override
    public void part2(List<String> lines) {
        List<Long> scores = lines.stream()
                .map(this::mapToDeques)
                .filter(Objects::nonNull)
                .map(this::calculateScore).sorted(Long::compareTo)
                .toList();
        Long aLong = scores.get(scores.size() / 2);
        System.out.println("Day 10 2/2: " + aLong);
    }

    private void countInvalidChars(Map<Character, Integer> invalidCount, String chunk) {
        Stack<Bracket> brackets = new Stack<>();
        for (int i = 0; i < chunk.length(); i++) {
            char character = chunk.charAt(i);
            Bracket currentBracket = Bracket.fromChar(character);
            if (isOpeningBracket(character)) {
                brackets.push(currentBracket);
            } else {
                Bracket lastOpeningBracket = brackets.pop();
                if (lastOpeningBracket != currentBracket) {
                    invalidCount.put(character, invalidCount.get(character) + 1);
                }
            }
        }
    }

    private Deque<Bracket> mapToDeques(String chunk) {
        Deque<Bracket> brackets = new ArrayDeque<>();
        for (int i = 0; i < chunk.length(); i++) {
            char character = chunk.charAt(i);
            Bracket currentBracket = Bracket.fromChar(character);
            if (isOpeningBracket(character)) {
                brackets.push(currentBracket);
            } else {
                Bracket lastOpeningBracket = brackets.pop();
                if (lastOpeningBracket != currentBracket) {
                    return null;
                }
            }
        }
        return brackets;
    }

    private long calculateScore(Deque<Bracket> brackets) {
        AtomicLong score = new AtomicLong(0);
        brackets.forEach(bracket -> score.set(score.get() * 5 + (long) AUTOCOMPLETE_SCORE.get(bracket.getClosingBracket())));
        return score.get();
    }

    private Map<Character, Integer> initCountMap() {
        Map<Character, Integer> counts = new HashMap<>();
        CLOSING_BRACKETS.chars().mapToObj(value -> (char) value)
                .forEach(character -> counts.put(character, 0));
        return counts;
    }

    private boolean isOpeningBracket(char character) {
        return OPENING_BRACKETS.contains(String.valueOf(character));
    }
}
