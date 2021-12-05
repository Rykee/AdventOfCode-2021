package rhykee.solver.task05.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DiagonalLine {

    private final Point start;
    private final Point end;
    private final int slope;

    public DiagonalLine(Point point1, Point point2) {
        if ((point1.getX() == point2.getX() && point1.getY() > point2.getY()) ||
                point1.getY() == point2.getY() && point1.getX() > point2.getX()) {
            start = point2;
            end = point1;
        } else if (point1.getX() - point2.getX() > 0) {
            start = point2;
            end = point1;
        } else {
            start = point1;
            end = point2;
        }
        if (end.getY() > start.getY()) {
            slope = 1;
        } else {
            slope = -1;
        }
    }

    public Set<Point> getAllPoints() {
        Set<Point> points = new HashSet<>();
        if (start.getX() == end.getX()) {
            int x = start.getX();
            for (int y = start.getY(); y <= end.getY(); y++) {
                points.add(new Point(x, y));
            }
        } else if (start.getY() == end.getY()) {
            int y = start.getY();
            for (int x = start.getX(); x <= end.getX(); x++) {
                points.add(new Point(x, y));
            }
        } else {
            for (int i = 0; i <= end.getX() - start.getX(); i++) {
                points.add(new Point(start.getX() + i, start.getY() + slope * i));
            }
        }
        return points;
    }

}
