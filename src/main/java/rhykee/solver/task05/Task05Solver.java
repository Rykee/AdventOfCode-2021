package rhykee.solver.task05;

import org.apache.commons.lang3.tuple.ImmutablePair;
import rhykee.solver.Challenge;
import rhykee.solver.task05.model.DiagonalLine;
import rhykee.solver.task05.model.Point;
import rhykee.solver.task05.model.SimpleLine;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Task05Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        long count = lines.stream()
                .map(s -> s.split("\s+->\s*"))
                .map(strings -> {
                    Point point1 = new Point(strings[0]);
                    Point point2 = new Point(strings[1]);
                    return new ImmutablePair<>(point1, point2);
                })
                .filter(pair -> pair.getLeft().getX() == pair.getRight().getX() || pair.getLeft().getY() == pair.getRight().getY())
                .map(pair -> new SimpleLine(pair.getLeft(), pair.getRight()))
                .map(SimpleLine::getAllPoints)
                .flatMap(Collection::stream)
                .collect(groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .count();
        System.out.println("Day 5 1/2: " + count);
    }

    @Override
    public void part2(List<String> lines) {
        long count = lines.stream()
                .map(s -> s.split("\s+->\s*"))
                .map(strings -> {
                    Point point1 = new Point(strings[0]);
                    Point point2 = new Point(strings[1]);
                    return new ImmutablePair<>(point1, point2);
                })
                .map(pair -> new DiagonalLine(pair.getLeft(), pair.getRight()))
                .map(DiagonalLine::getAllPoints)
                .flatMap(Collection::stream)
                .collect(groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .count();
        System.out.println("Day 5 2/2: " + count);
    }

}
