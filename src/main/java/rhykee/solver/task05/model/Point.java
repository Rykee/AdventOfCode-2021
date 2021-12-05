package rhykee.solver.task05.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Point {

    @EqualsAndHashCode.Include
    private final int x;
    @EqualsAndHashCode.Include
    private final int y;

    public Point(String input) {
        String[] parts = input.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);
    }

}
