package rhykee.solver.task11;


import rhykee.solver.model.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class DumboGrid {

    private final Map<Coordinate, Dumbo> dumbos = new HashMap<>();
    private final int width;
    private final int height;

    public DumboGrid(List<String> octopi) {
        Set<Coordinate> viableCoordinates = new HashSet<>();
        height = octopi.size();
        width = octopi.get(0).length();
        parseInput(octopi, viableCoordinates);
        dumbos.values().forEach(dumbo -> dumbo.removeUnreachableCoordinates(viableCoordinates));
    }

    private void parseInput(List<String> octopi, Set<Coordinate> viableCoordinates) {
        for (int i = 0; i < octopi.size(); i++) {
            for (int j = 0; j < octopi.get(i).length(); j++) {
                Coordinate position = new Coordinate(i, j);
                Dumbo dumbo = new Dumbo(i, j, Integer.parseInt(String.valueOf(octopi.get(i).charAt(j))));
                dumbos.put(position, dumbo);
                viableCoordinates.add(position);
            }
        }
    }

    public int getPopulation() {
        return dumbos.size();
    }

    public int flash() {
        Set<Coordinate> hasFlashed = new HashSet<>();
        doInitialFlashes(hasFlashed);
        if (!hasFlashed.isEmpty()) {
            doAdditionalFlashes(hasFlashed);
        }
        hasFlashed.forEach(coordinate -> dumbos.get(coordinate).setEnergy(0));
        return hasFlashed.size();
    }

    private void doInitialFlashes(Set<Coordinate> hasFlashed) {
        dumbos.values().forEach(dumbo -> {
            dumbo.addEnergy();
            if (dumbo.getEnergy() > 9) {
                dumbo.getNeighbors().forEach(coordinate -> dumbos.get(coordinate).addEnergy());
                hasFlashed.add(dumbo.getCoordinate());
            }
        });
    }

    private void doAdditionalFlashes(Set<Coordinate> hasFlashed) {
        AtomicInteger newFlashes = new AtomicInteger(0);
        do {
            newFlashes.set(0);
            dumbos.values().forEach(dumbo -> {
                if (dumbo.getEnergy() > 9 && !hasFlashed.contains(dumbo.getCoordinate())) {
                    hasFlashed.add(dumbo.getCoordinate());
                    dumbo.getNeighbors().forEach(coordinate -> dumbos.get(coordinate).addEnergy());
                    newFlashes.addAndGet(1);
                }
            });
        } while (newFlashes.get() != 0);
    }

    public void print() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int energy = dumbos.get(new Coordinate(x, y)).getEnergy();
                System.out.print((energy < 10 ? "0" + energy : energy) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
