package rhykee.solver.task12;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node {

    @EqualsAndHashCode.Include
    private final String name;
    private final List<Node> neighbors = new ArrayList<>();

    public boolean isEnd() {
        return name.equals("end");
    }

    public boolean isBigCave() {
        return name.toUpperCase().equals(name);
    }

    @Override
    public String toString() {
        return "name: " + name + ". Neighbors: " + neighbors.stream().map(Node::getName).collect(Collectors.joining(", "));
    }


}
