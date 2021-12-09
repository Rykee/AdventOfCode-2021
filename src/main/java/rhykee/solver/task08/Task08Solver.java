package rhykee.solver.task08;

import rhykee.solver.Challenge;

import java.util.List;

public class Task08Solver implements Challenge {
    @Override
    public void part1(List<String> lines) {
        int sum = lines.stream()
                .map(Display::new)
                .map(Display::getNumberOf1478)
                .mapToInt(value -> value)
                .sum();

        System.out.println("Day 8 1/2: " + sum);
    }

    @Override
    public void part2(List<String> lines) {
        Integer sum = lines.stream()
                .map(Display::new)
                .map(Display::getNumber)
                .reduce(Integer::sum)
                .get();

        //  System.out.println("Day 8 2/2: " + sum);
    }
}
