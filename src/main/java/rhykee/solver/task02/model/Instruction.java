package rhykee.solver.task02.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class Instruction {

    private Direction direction;
    private long value;

    public Instruction(String rawInput){
        String[] parts = rawInput.split(" ");
        direction = Direction.fromString(parts[0]);
        value = Long.parseLong(parts[1]);
    }


    public  enum Direction{
        FORWARD,
        DOWN,
        UP;

        public static Direction fromString(String name){
            return Arrays.stream(values())
                    .filter(dir -> dir.name().equalsIgnoreCase(name))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Can't parse Direction: " + name));
        }
    }

}
