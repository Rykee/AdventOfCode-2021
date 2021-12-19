package rhykee.solver.task15;

import rhykee.solver.Challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task15Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        Integer[][] input = lines.stream()
                .map(s -> Arrays.stream(s.split(""))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
        Graph graph = new Graph();
        Map<Integer, Node> nodes = new HashMap<>();
        Node finalNode = new Node(0, 0, 0);
        Node sourceNode = new Node(0, input.length - 1, input.length - 1);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Node node = new Node(input[i][j], i, j);
                if (i == input.length - 1 && j == input[i].length - 1) {
                    finalNode = node;
                }
                if (i == 0 && j == 0) {
                    sourceNode = node;
                }
                nodes.put(i * 1000 + j, node);
            }
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Node node = nodes.get(i * 1000 + j);
                if (i != 0) {
                    Node leftNode = nodes.get((i - 1) * 1000 + j);
                    node.addDestination(leftNode, leftNode.getCost());
                }
                if (i != input.length - 1) {
                    Node rightNode = nodes.get((i + 1) * 1000 + j);
                    node.addDestination(rightNode, rightNode.getCost());
                }
                if (j != 0) {
                    Node upperNode = nodes.get(i * 1000 + j - 1);
                    node.addDestination(upperNode, upperNode.getCost());
                }
                if (j != input[i].length - 1) {
                    Node lowerNode = nodes.get(i * 1000 + j + 1);
                    node.addDestination(lowerNode, lowerNode.getCost());
                }
            }
        }
        graph.getNodes().addAll(nodes.values());
        calculateShortestPathFromSource(graph, sourceNode);
        System.out.println("Day 15 1/2: " + finalNode.getDistance());
    }

    private Integer[][] parseInput(List<String> lines) {
        Integer[][] input = lines.stream()
                .map(s -> Arrays.stream(s.split(""))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
        Integer[][] scaledInput = new Integer[5 * input.length][5 * input[0].length];
        for (int i = 0; i < scaledInput.length; i++) {
            for (int j = 0; j < scaledInput[i].length; j++) {
                int rowScale = i / input.length;
                int columnScale = j / input[0].length;
                scaledInput[i][j] = scaleBy(input[i % input.length][j % input[0].length], rowScale + columnScale);
            }
        }
        return scaledInput;
    }

    private int scaleBy(int value, int scale) {
        int newValue = value + scale;
        return newValue > 9 ? newValue - 9 : newValue;
    }

    @Override
    public void part2(List<String> lines) {
        Integer[][] input = parseInput(lines);
        Graph graph = new Graph();
        Map<Integer, Node> nodes = new HashMap<>();
        Node finalNode = new Node(0, 0, 0);
        Node sourceNode = new Node(0, input.length - 1, input.length - 1);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Node node = new Node(input[i][j], i, j);
                if (i == input.length - 1 && j == input[i].length - 1) {
                    finalNode = node;
                }
                if (i == 0 && j == 0) {
                    sourceNode = node;
                }
                nodes.put(i * 1000 + j, node);
            }
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Node node = nodes.get(i * 1000 + j);
                if (i != 0) {
                    Node leftNode = nodes.get((i - 1) * 1000 + j);
                    node.addDestination(leftNode, leftNode.getCost());
                }
                if (i != input.length - 1) {
                    Node rightNode = nodes.get((i + 1) * 1000 + j);
                    node.addDestination(rightNode, rightNode.getCost());
                }
                if (j != 0) {
                    Node upperNode = nodes.get(i * 1000 + j - 1);
                    node.addDestination(upperNode, upperNode.getCost());
                }
                if (j != input[i].length - 1) {
                    Node lowerNode = nodes.get(i * 1000 + j + 1);
                    node.addDestination(lowerNode, lowerNode.getCost());
                }
            }
        }
        graph.getNodes().addAll(nodes.values());
        calculateShortestPathFromSource(graph, sourceNode);
        System.out.println("Day 15 2/2: " + finalNode.getDistance());
    }

    private void printArray(Integer[][] input) {
        for (Integer[] integers : input) {
            for (int j = 0; j < integers.length; j++) {
                if (j % 10 == 0) {
                    System.out.print(" | ");
                }
                System.out.print(integers[j] + " ");
            }
            System.out.println();
        }
    }

    private Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
        }
    }

}
