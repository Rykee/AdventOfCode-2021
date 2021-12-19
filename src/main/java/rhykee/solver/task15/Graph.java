package rhykee.solver.task15;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Graph {

    private final Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

}
