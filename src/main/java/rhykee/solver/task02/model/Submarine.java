package rhykee.solver.task02.model;

import lombok.Data;

import java.util.List;

@Data
public class Submarine {
    protected long horizontalPosition;
    protected long verticalPosition;

    public long getProduct() {
        return horizontalPosition * verticalPosition;
    }

    public void applyInstructions(List<Instruction> instructions) {
        instructions.forEach(instruction -> {
            switch (instruction.getDirection()) {
                case FORWARD -> horizontalPosition += instruction.getValue();
                case DOWN -> verticalPosition += instruction.getValue();
                case UP -> verticalPosition -= instruction.getValue();
            }
        });
    }
}
