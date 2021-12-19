package rhykee.solver.task09;

import lombok.Data;
import rhykee.solver.task11.Coordinate;

import java.util.HashSet;
import java.util.Set;

@Data
public class GridHelper {
    private final Set<Coordinate> viableCoordinates = new HashSet<>();
    private final int[][] grid;

    public GridHelper(int[][] grid) {
        this.grid = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                viableCoordinates.add(new Coordinate(i, j));
            }
        }
    }

    public boolean isViable(int i, int j, int currentNumber) {
        return viableCoordinates.contains(new Coordinate(i, j))
                && grid[i][j] != 9 && grid[i][j] > currentNumber;
    }

}
