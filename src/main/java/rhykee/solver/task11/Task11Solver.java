package rhykee.solver.task11;

import rhykee.solver.Challenge;

import java.util.List;

public class Task11Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        DumboGrid dumboGrid = new DumboGrid(lines);
        long flashes = 0;
        for (int i = 0; i < 100; i++) {
            flashes += dumboGrid.flash();
        }
        dumboGrid.print();
        System.out.println("Day 11 1/2: " + flashes);
    }

    @Override
    public void part2(List<String> lines) {
        DumboGrid dumboGrid = new DumboGrid(lines);
        long iteration = 1;
        while (dumboGrid.flash() != dumboGrid.getPopulation()) {
            iteration++;
        }
        dumboGrid.print();
        System.out.println("Day 11 2/2: " + iteration);
    }

}
