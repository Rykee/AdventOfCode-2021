package rhykee;

import rhykee.solver.task01.Task01Solver;
import rhykee.solver.task02.Task02Solver;
import rhykee.solver.task03.Task03Solver;
import rhykee.solver.task04.Task04Solver;
import rhykee.solver.task05.Task05Solver;
import rhykee.solver.task06.Task06Solver;
import rhykee.solver.task07.Task07Solver;

import java.util.List;

import static rhykee.util.HttpUtils.getInput;

public class Main {

    public static void main(String[] args) {
        String cookie = args[0];
        day01(getInput(1, cookie));
        day02(getInput(2, cookie));
        day03(getInput(3, cookie));
        day04(getInput(4, cookie));
        day05(getInput(5, cookie));
        day06(getInput(6, cookie));
        day07(getInput(7, cookie));
    }

    private static void day01(List<String> input) {
        Task01Solver task01Solver = new Task01Solver();
        task01Solver.countIncrements(input);
        task01Solver.countSlidingIncrements(input);
    }

    private static void day02(List<String> input) {
        Task02Solver solver = new Task02Solver();
        solver.applyInstructions(input);
        solver.applyInstructionsAiming(input);
    }

    private static void day03(List<String> input) {
        Task03Solver task03Solver = new Task03Solver();
        task03Solver.first(input);
        task03Solver.second(input);
    }

    private static void day04(List<String> inputForDay) {
        Task04Solver task04Solver = new Task04Solver();
        task04Solver.part1(inputForDay);
        task04Solver.part2(inputForDay);
    }

    private static void day05(List<String> input) {
        Task05Solver task05Solver = new Task05Solver();
        task05Solver.part1(input);
        task05Solver.part2(input);
    }

    private static void day06(List<String> input) {
        Task06Solver task06Solver = new Task06Solver();
        task06Solver.part1(input);
        task06Solver.part2(input);
    }

    private static void day07(List<String> input) {
        Task07Solver task07Solver = new Task07Solver();
        task07Solver.part1(input);
        task07Solver.part2(input);
    }

}
