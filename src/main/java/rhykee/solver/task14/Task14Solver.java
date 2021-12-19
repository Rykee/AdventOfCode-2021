package rhykee.solver.task14;

import rhykee.solver.Challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task14Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        long delta = getDelta(lines, 10);
        System.out.println("Day 14 1/2: " + delta);
    }

    @Override
    public void part2(List<String> lines) {
        long delta = getDelta(lines, 40);
        System.out.println("Day 14 2/2 " + delta);
    }

    private long getDelta(List<String> lines, int iteration) {
        String input = lines.get(0);
        Map<Character, Long> counts = new HashMap<>();
        Map<String, Long> pairs = new HashMap<>();
        for (int i = 0; i < input.length() - 1; i++) {
            pairs.computeIfPresent(input.substring(i, i + 2), (s, aLong) -> aLong + 1L);
            pairs.computeIfAbsent(input.substring(i, i + 2), s -> 1L);
        }
        for (int i = 0; i < input.length(); i++) {
            counts.computeIfPresent(input.charAt(i), (character, currentCount) -> currentCount + 1);
            counts.computeIfAbsent(input.charAt(i), character -> 1L);
        }
        Map<String, String> conversions = lines.subList(2, lines.size()).stream()
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
        for (int i = 0; i < iteration; i++) {
            Map<String, Long> finishedCounts = new HashMap<>();
            pairs.forEach((key, value) -> {
                String newChar = conversions.get(key);
                finishedCounts.computeIfPresent(key.charAt(0) + newChar, (s, integer) -> integer + value);
                finishedCounts.computeIfAbsent(key.charAt(0) + newChar, s -> value);
                finishedCounts.computeIfPresent(newChar + key.charAt(1), (s, integer) -> integer + value);
                finishedCounts.computeIfAbsent(newChar + key.charAt(1), s -> value);
                counts.computeIfPresent(newChar.charAt(0), (character, aLong) -> aLong + value);
                counts.computeIfAbsent(newChar.charAt(0), character -> value);
            });
            pairs = finishedCounts;
        }
        Long min = counts.values().stream().min(Long::compareTo).get();
        Long max = counts.values().stream().max(Long::compareTo).get();
        return max - min;
    }

}
