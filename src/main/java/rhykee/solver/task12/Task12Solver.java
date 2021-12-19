package rhykee.solver.task12;

import rhykee.solver.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Task12Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        Map<String, Node> nodes = new HashMap<>();
        lines.stream()
                .map(s -> s.split("-"))
                .forEach(parts -> {
                    Node node1 = nodes.containsKey(parts[0]) ? nodes.get(parts[0]) : new Node(parts[0]);
                    Node node2 = nodes.containsKey(parts[1]) ? nodes.get(parts[1]) : new Node(parts[1]);
                    node1.getNeighbors().add(node2);
                    node2.getNeighbors().add(node1);
                    nodes.putIfAbsent(node1.getName(), node1);
                    nodes.putIfAbsent(node2.getName(), node2);
                });
        Node start = nodes.get("start");
        ArrayList<List<Node>> allFoundPaths = new ArrayList<>();
        findPaths(start, allFoundPaths, new ArrayList<>());
        System.out.println("Day 12 1/2: " + allFoundPaths.size());
    }

    private void findPaths(Node currentNode, List<List<Node>> allFoundPaths, List<Node> currentPath) {
        currentPath.add(currentNode);
        if (currentNode.getName().equals("end")) {
            allFoundPaths.add(new ArrayList<>(currentPath));
        } else {
            for (Node node : currentNode.getNeighbors()) {
                if (node.isBigCave() || (!node.isBigCave() && !currentPath.contains(node))) {
                    findPaths(node, allFoundPaths, new ArrayList<>(currentPath));
                }
            }
        }
    }

    @Override
    public void part2(List<String> lines) {
        Map<String, Node> nodes = new HashMap<>();
        lines.stream()
                .map(s -> s.split("-"))
                .forEach(parts -> {
                    Node node1 = nodes.containsKey(parts[0]) ? nodes.get(parts[0]) : new Node(parts[0]);
                    Node node2 = nodes.containsKey(parts[1]) ? nodes.get(parts[1]) : new Node(parts[1]);
                    node1.getNeighbors().add(node2);
                    node2.getNeighbors().add(node1);
                    nodes.putIfAbsent(node1.getName(), node1);
                    nodes.putIfAbsent(node2.getName(), node2);
                });
        Node start = nodes.get("start");
        ArrayList<List<Node>> allFoundPaths = new ArrayList<>();
        findPaths2(start, allFoundPaths, new ArrayList<>());
        System.out.println("Day 12 2/2: " + allFoundPaths.size());
    }

    private void findPaths2(Node currentNode, List<List<Node>> allFoundPaths, List<Node> currentPath) {
        currentPath.add(currentNode);
        if (currentNode.getName().equals("end")) {
            allFoundPaths.add(new ArrayList<>(currentPath));
        } else {
            for (Node node : currentNode.getNeighbors()) {
                if (!node.getName().equals("start") && (node.isBigCave() || (!node.isBigCave() && !currentPath.contains(node)) || !isAnySmallCaveVisitedTwice(currentPath))) {
                    findPaths2(node, allFoundPaths, new ArrayList<>(currentPath));
                }
            }
        }
    }

    public boolean isAnySmallCaveVisitedTwice(List<Node> path) {
        return path.stream()
                .filter(node -> !node.isBigCave())
                .collect(groupingBy(Node::getName, Collectors.counting()))
                .entrySet()
                .stream().anyMatch(entry -> entry.getValue() >= 2L);
    }

}
