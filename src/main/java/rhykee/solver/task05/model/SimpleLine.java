package rhykee.solver.task05.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SimpleLine {

    private final Point start;
    private final Point end;

    public SimpleLine(Point point1, Point point2) {
        if(point1.getX()<point2.getX() || point1.getY()<point2.getY()){
            start = point1;
            end = point2;
        } else {
            start = point2;
            end = point1;
        }
    }

    public Set<Point> getAllPoints() {
        Set<Point> points = new HashSet<>();
        if (start.getX() == end.getX()) {
            int x = start.getX();
            for (int y = start.getY(); y <= end.getY(); y++) {
                points.add(new Point(x, y));
            }
        } else {
            int y = start.getY();
            for (int x = start.getX(); x <= end.getX(); x++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }

}
