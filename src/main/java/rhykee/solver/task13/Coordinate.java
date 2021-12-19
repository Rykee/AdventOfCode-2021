package rhykee.solver.task13;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate transpose(Folding folding) {
        return switch (folding.getAxis()) {
            case X -> new Coordinate(2 * folding.getValue() - x, y);
            case Y -> new Coordinate(x, folding.getValue() * 2 - y);
        };
    }

}
