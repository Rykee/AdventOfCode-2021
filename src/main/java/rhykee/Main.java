package rhykee;

import rhykee.solver.task01.Task01Solver;
import rhykee.solver.task02.Task02Solver;

import java.util.List;

import static rhykee.util.HttpUtils.getInputForDay;

public class Main {

    public static void main(String[] args) {
        String cookie = args[0];
        day01(getInputForDay(1, cookie));
        day02(getInputForDay(2, cookie));
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

}
