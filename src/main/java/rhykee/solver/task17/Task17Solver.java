package rhykee.solver.task17;

import rhykee.solver.Challenge;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task17Solver implements Challenge {

    private static final Pattern COORDINATE_PATTERN = Pattern.compile("(?<=target area: x=)(?<xLow>-?[0-9]*)..(?<xHigh>-?[0-9]*), y=(?<yLow>-?[0-9]*)..(?<yHigh>-?[0-9]*)");

    @Override
    public void part1(List<String> lines) {
        Matcher matcher = COORDINATE_PATTERN.matcher(lines.get(0));
        matcher.find();
        int yMin = Integer.parseInt(matcher.group("yLow"));

        System.out.println("Day 17 1/2: " + Math.abs(yMin - 1) * Math.abs(yMin) / 2);

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
