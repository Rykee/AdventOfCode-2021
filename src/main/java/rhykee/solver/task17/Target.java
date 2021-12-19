package rhykee.solver.task17;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Target {

    private Coordinate topLeft;
    private Coordinate bottomRight;


    public boolean hasCoordinate(Coordinate coordinate) {
        return coordinate.getX() >= topLeft.getX() && coordinate.getX() <= bottomRight.getX()
                && coordinate.getY() <= topLeft.getY() && coordinate.getY() >= bottomRight.getY();
    }

}
