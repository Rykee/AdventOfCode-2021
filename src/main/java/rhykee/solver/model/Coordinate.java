package rhykee.solver.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public final class Coordinate {

    private static final List<Coordinate> NEIGHBORS = createNeighbors();
    private final int x;
    private final int y;

    private static List<Coordinate> createNeighbors() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                coordinates.add(new Coordinate(i, j));
            }
        }
        return coordinates;
    }

    public List<Coordinate> getNeighboringCoordinates() {
        return applyMultipleVectors(NEIGHBORS);
    }

    public Coordinate applyVector(Coordinate vector) {
        return new Coordinate(x + vector.x, y + vector.y);
    }

    public List<Coordinate> applyMultipleVectors(List<Coordinate> vectors) {
        return vectors.stream()
                .map(this::applyVector)
                .collect(Collectors.toList());
    }

}
