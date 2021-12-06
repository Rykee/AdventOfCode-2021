package rhykee.solver.task06;

import rhykee.solver.Challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Task06Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        List<LanternFish> lanternFish = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .map(LanternFish::new)
                .collect(Collectors.toList());
        for (int i = 0; i < 80; i++) {
            long count = lanternFish.stream()
                    .map(LanternFish::age)
                    .filter(aBoolean -> aBoolean)
                    .count();
            for (int j = 0; j < count; j++) {
                lanternFish.add(new LanternFish());
            }
        }
        System.out.println("Day 6 1/2: " + lanternFish.size());
    }

    @Override
    public void part2(List<String> lines) {
        Map<Integer, Long> fishAges;
        fishAges = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        for (int i = 0; i <= 8; i++) {
            fishAges.putIfAbsent(i, 0L);
        }
        for (int i = 0; i < 256; i++) {
            Map<Integer, Long> newAges = getEmptyMap();
            for (int j = 8; j >= 0; j--) {
                if (j == 0) {
                    newAges.put(8, fishAges.get(0));
                    newAges.put(6, fishAges.get(0) + newAges.get(6));
                } else {
                    newAges.put(j - 1, fishAges.get(j));
                }
            }
            fishAges = newAges;
        }
        long fullCount = fishAges.values().stream().mapToLong(value -> value).sum();
        System.out.println("Day 6 2/2: " + fullCount);
    }

    private Map<Integer, Long> getEmptyMap() {
        Map<Integer, Long> newAges = new HashMap<>();
        for (int j = 0; j <= 8; j++) {
            newAges.putIfAbsent(j, 0L);
        }
        return newAges;
    }

}
