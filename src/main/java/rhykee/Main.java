package rhykee;

import rhykee.solver.task01.Task01Solver;
import rhykee.solver.task02.Task02Solver;
import rhykee.solver.task03.Task03Solver;
import rhykee.solver.task04.Task04Solver;

import java.util.List;

import static rhykee.util.HttpUtils.getInputForDay;

public class Main {

    public static void main(String[] args) {
        String cookie = args[0];
        //day01(getInputForDay(1, cookie));
        //day02(getInputForDay(2, cookie));
        //day03(getInputForDay(3, cookie));
        day04(getInputForDay(4, cookie));
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
        long startTime = System.currentTimeMillis();
        Task04Solver task04Solver = new Task04Solver();
            task04Solver.part1(inputForDay);
            task04Solver.part2(inputForDay);
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
