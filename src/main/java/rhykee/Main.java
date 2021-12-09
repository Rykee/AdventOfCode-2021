package rhykee;

import rhykee.solver.Challenge;
import rhykee.util.ReflectionUtils;

import java.util.List;

import static rhykee.util.HttpUtils.getInput;

public class Main {

    public static void main(String[] args) {
        String cookie = args[0];
        for (int i = 9; i <= 9; i++) {
            Challenge solverByDay = ReflectionUtils.getSolverByDay(i);
            List<String> input = getInput(i, cookie);
            solverByDay.part1(input);
            solverByDay.part2(input);
        }
    }

}
