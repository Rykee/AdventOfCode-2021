package rhykee.solver.task02;

import rhykee.solver.Challenge;
import rhykee.solver.task02.model.AimingSubmarine;
import rhykee.solver.task02.model.Instruction;
import rhykee.solver.task02.model.Instruction.Direction;
import rhykee.solver.task02.model.Submarine;

import java.util.List;
import java.util.stream.Collectors;

public class Task02Solver implements Challenge {

    @Override
    public void part1(List<String> input) {
        Submarine submarine = new Submarine();
        List<Instruction> instructions = input.stream()
                .map(s -> s.split(" "))
                .collect(Collectors.toMap(strings -> strings[0], strings -> Long.parseLong(strings[1]), Long::sum))
                .entrySet().stream()
                .map(entry -> new Instruction(Direction.fromString(entry.getKey()), entry.getValue()))
                .toList();
        submarine.applyInstructions(instructions);
        System.out.println("Day 2 1/2: " + submarine.getProduct());
    }

    @Override
    public void part2(List<String> input) {
        AimingSubmarine submarine = new AimingSubmarine();
        List<Instruction> instructions = input.stream()
                .map(Instruction::new)
                .toList();
        submarine.applyInstructions(instructions);
        System.out.println("Day 2 2/2: " + submarine.getProduct());
    }

}
