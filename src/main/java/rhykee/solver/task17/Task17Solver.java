package rhykee.solver.task17;

import rhykee.solver.Challenge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task17Solver implements Challenge {

    private static final Pattern COORDINATE_PATTERN = Pattern.compile("(?<=target area: x=)(?<xLow>-?[0-9]*)..(?<xHigh>-?[0-9]*), y=(?<yLow>-?[0-9]*)..(?<yHigh>-?[0-9]*)");

    @Override
    public void part1(List<String> lines) {
        Matcher matcher = COORDINATE_PATTERN.matcher(lines.get(0));
        matcher.find();
        int xMin = Integer.parseInt(matcher.group("xLow"));
        int xMax = Integer.parseInt(matcher.group("xHigh"));
        int yMin = Integer.parseInt(matcher.group("yLow"));
        int yMax = Integer.parseInt(matcher.group("yHigh"));

        Probe probe = new Probe(new Coordinate(6, 9));
        Target target = new Target(new Coordinate(20, -5), new Coordinate(30, -10));
        Set<Coordinate> hits = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            hits.add(probe.move());
        }
        int minY = hits.stream()
                .map(Coordinate::getY)
                .mapToInt(Integer::intValue)
                .min()
                .getAsInt();
        int maxY = hits.stream()
                .map(Coordinate::getY)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        int maxX = hits.stream()
                .map(Coordinate::getX)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        for (int j = maxY; j >= minY; j--) {
            for (int i = 0; i <= maxX + 10; i++) {
                Coordinate o = new Coordinate(i, j);
                if (hits.contains(o)) {
                    System.out.print("#");
                } else if (target.hasCoordinate(o)) {
                    System.out.print("T");
                } else if (i == 0 && j == 0) {
                    System.out.print("S");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

    }

    @Override
    public void part2(List<String> lines) {
        Matcher matcher = COORDINATE_PATTERN.matcher(lines.get(0));
        matcher.find();
        int xMin = Integer.parseInt(matcher.group("xLow"));
        int xMax = Integer.parseInt(matcher.group("xHigh"));
        int yMin = Integer.parseInt(matcher.group("yLow"));
        int yMax = Integer.parseInt(matcher.group("yHigh"));
        Target target = new Target(new Coordinate(xMin, yMax), new Coordinate(xMax, yMin));

        //FELFELÉ
        //(n^2/2 +n - x = 0)
        int minX = getMinX(target);
        int maxY = Math.abs(target.getBottomRight().getY()) - 1;

        int count = 0;

        for (int i = minX; i <= target.getBottomRight().getX(); i++) {
            for (int j = target.getBottomRight().getY(); j <= maxY; j++) {
                Probe probe = new Probe(new Coordinate(i, j));
                Coordinate currentCoordinate = probe.move();
                while (!target.hasCoordinate(currentCoordinate)) {
                    if (currentCoordinate.getX() > target.getBottomRight().getX() || currentCoordinate.getY() < target.getBottomRight().getY()) {
                        break;
                    }
                    currentCoordinate = probe.move();
                }
                if (target.hasCoordinate(currentCoordinate)) {
                    count++;
                }
            }
        }
        System.out.println("Day 17 2/2: " + count);
    }

    private int getMinX(Target target) {
        double c = target.getTopLeft().getX();

        return (int) Math.ceil(-1 + Math.sqrt(1 - 2 * -c));
    }

}
