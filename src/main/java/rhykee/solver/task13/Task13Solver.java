package rhykee.solver.task13;

import rhykee.solver.Challenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task13Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        int i = 0;
        Set<Coordinate> coordinates = new HashSet<>();
        while (!lines.get(i).isEmpty()) {
            String[] parts = lines.get(i).split(",");
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            i++;
        }
        i++;
        List<Folding> foldings = new ArrayList<>();
        while (i < lines.size()) {
            String[] parts = lines.get(i).substring(11).split("=");
            foldings.add(new Folding(Axis.valueOf(parts[0].toUpperCase()), Integer.parseInt(parts[1])));
            i++;
        }
        Paper paper = new Paper(coordinates, foldings);
        System.out.println("Day 13 1/2: " + paper.foldOnceCount());

    }

    @Override
    public void part2(List<String> lines) {
        int i = 0;
        Set<Coordinate> coordinates = new HashSet<>();
        while (!lines.get(i).isEmpty()) {
            String[] parts = lines.get(i).split(",");
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            i++;
        }
        i++;
        List<Folding> foldings = new ArrayList<>();
        while (i < lines.size()) {
            String[] parts = lines.get(i).substring(11).split("=");
            foldings.add(new Folding(Axis.valueOf(parts[0].toUpperCase()), Integer.parseInt(parts[1])));
            i++;
        }
        Paper paper = new Paper(coordinates, foldings);
        paper.applyAllFolding();
        Set<Coordinate> newCoords = paper.getCoordinates();
        List<Coordinate> xCoords = newCoords.stream()
                .sorted(Comparator.comparingInt(Coordinate::getX))
                .toList();
        List<Coordinate> yCoords = newCoords.stream()
                .sorted(Comparator.comparingInt(Coordinate::getY))
                .toList();
        System.out.println("Day 13 2/2: ");
        for (int y = yCoords.get(0).getY(); y <= yCoords.get(yCoords.size() - 1).getY(); y++) {
            for (int x = xCoords.get(0).getX(); x <= xCoords.get(xCoords.size() - 1).getX(); x++) {
                if (newCoords.contains(new Coordinate(x, y))) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
