package rhykee.solver.task07;

import rhykee.solver.Challenge;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Task07Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        List<Integer> crabPositions = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        Integer minPosition = crabPositions.stream().min(Integer::compareTo).get();
        Integer maxPosition = crabPositions.stream().max(Integer::compareTo).get();
        OptionalInt min = IntStream.range(minPosition, maxPosition + 1)
                .map(currentPos -> crabPositions.stream()
                        .map(position -> Math.abs(currentPos - position))
                        .mapToInt(Integer::intValue)
                        .sum())
                .min();
        System.out.println("Day 7 1/2: " + min.getAsInt());
    }

    @Override
    public void part2(List<String> lines) {
        List<Integer> crabPositions = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        Integer minPosition = crabPositions.stream().min(Integer::compareTo).get();
        Integer maxPosition = crabPositions.stream().max(Integer::compareTo).get();
        OptionalInt min = IntStream.range(minPosition, maxPosition + 1)
                .map(currentPos -> crabPositions.stream()
                        .map(position -> getFuel(Math.abs(currentPos - position)))
                        .mapToInt(Integer::intValue)
                        .sum())
                .min();
        System.out.println("Day 7 2/2: " + min.getAsInt());
    }

    private int getFuel(int distance) {
        int sum = 0;
        for (int i = 0; i < distance; i++) {
            sum += i + 1;
        }
        return sum;
    }
}
