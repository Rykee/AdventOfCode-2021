package rhykee.solver.task13;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Paper {

    private final Set<Coordinate> coordinates;
    private final List<Folding> foldings;

    public int foldOnceCount() {
        Folding folding = foldings.get(0);
        applyFolding(folding);
        return coordinates.size();
    }

    public void applyAllFolding() {
        foldings.forEach(this::applyFolding);
    }

    private void applyFolding(Folding folding) {
        Set<Coordinate> coordinatesToApplyFolding = switch (folding.getAxis()) {
            case X -> coordinates.stream().filter(coordinate -> coordinate.getX() > folding.getValue()).collect(Collectors.toSet());
            case Y -> coordinates.stream().filter(coordinate -> coordinate.getY() > folding.getValue()).collect(Collectors.toSet());
        };
        Set<Coordinate> newCoords = coordinatesToApplyFolding.stream()
                .map(coordinate -> coordinate.transpose(folding))
                .collect(Collectors.toSet());
        applyChanges(coordinatesToApplyFolding, newCoords);
    }

    private void applyChanges(Set<Coordinate> coordinatesToApplyFolding, Set<Coordinate> newCoords) {
        coordinates.addAll(newCoords);
        coordinates.removeAll(coordinatesToApplyFolding);
    }
}
