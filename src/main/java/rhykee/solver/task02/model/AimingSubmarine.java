package rhykee.solver.task02.model;

import java.util.List;

public class AimingSubmarine extends Submarine {

    private long aim;

    @Override
    public void applyInstructions(List<Instruction> instructions) {
        instructions.forEach(instruction -> {
            switch (instruction.getDirection()) {
                case FORWARD -> {
                    horizontalPosition += instruction.getValue();
                    verticalPosition += aim * instruction.getValue();
                }
                case DOWN -> aim+=instruction.getValue();
                case UP -> aim-=instruction.getValue();
            }
        });
    }
}
