package rhykee.solver.task11;

import lombok.Data;
import rhykee.solver.model.Coordinate;

import java.util.List;
import java.util.Set;

@Data
public class Dumbo {

    private final List<Coordinate> neighbors;
    private final Coordinate coordinate;
    private int energy;

    public Dumbo(int x, int y, int energy) {
        coordinate = new Coordinate(x, y);
        neighbors = coordinate.getNeighboringCoordinates();
        this.energy = energy;
    }

    public void addEnergy() {
        energy++;
    }

    public void removeUnreachableCoordinates(Set<Coordinate> viableCoordinates) {
        neighbors.retainAll(viableCoordinates);
    }
}
