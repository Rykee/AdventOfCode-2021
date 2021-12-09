package rhykee.solver.task09;

import rhykee.solver.Challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Task09Solver implements Challenge {

    private GridHelper gridHelper;

    @Override
    public void part1(List<String> lines) {
        int[][] heightData = mapToIntGrid(lines);
        gridHelper = new GridHelper(heightData);
        long sum = 0;
        for (int i = 0; i < heightData.length; i++) {
            for (int j = 0; j < heightData[i].length; j++) {
                if (dip(heightData, i, j)) {
                    sum += heightData[i][j] + 1;
                }
            }
        }
        System.out.println("Day 9 1/2: " + sum);
    }

    @Override
    public void part2(List<String> lines) {
        int[][] heightData = mapToIntGrid(lines);
        List<Integer> basinSizes = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, heightData.length)
                .forEach(i -> IntStream.range(0, heightData[i].length)
                        .filter(j -> dip(heightData, i, j))
                        .forEach(j -> mapBasin(heightData, basinSizes, i, j)));
        basinSizes.sort(Collections.reverseOrder());
        long result = 1;
        for (int i = 0; i < 3; i++) {
            result *= basinSizes.get(i);
        }
        System.out.println("Day 9 2/2:" + result);
    }

    private void mapBasin(int[][] heightData, List<Integer> basinSizes, int i, int j) {
        int[][] visitedNodes = new int[heightData.length][heightData[0].length];
        visitedNodes[i][j]++;
        basinSizes.add(findBasinSize(heightData, i, j, new AtomicInteger(1), visitedNodes));
    }

    private int findBasinSize(int[][] heightData, int i, int j, AtomicInteger currentSize, int[][] visitedNodes) {
        int currentNumber = heightData[i][j];
        visitedNodes[i][j]++;
        if (gridHelper.isViable(i - 1, j, currentNumber) && visitedNodes[i - 1][j] == 0) {
            currentSize.incrementAndGet();
            findBasinSize(heightData, i - 1, j, currentSize, visitedNodes);
        }
        if (gridHelper.isViable(i, j - 1, currentNumber) && visitedNodes[i][j - 1] == 0) {
            currentSize.incrementAndGet();
            findBasinSize(heightData, i, j - 1, currentSize, visitedNodes);
        }
        if (gridHelper.isViable(i + 1, j, currentNumber) && visitedNodes[i + 1][j] == 0) {
            currentSize.incrementAndGet();
            findBasinSize(heightData, i + 1, j, currentSize, visitedNodes);
        }
        if (gridHelper.isViable(i, j + 1, currentNumber) && visitedNodes[i][j + 1] == 0) {
            currentSize.incrementAndGet();
            findBasinSize(heightData, i, j + 1, currentSize, visitedNodes);
        }
        return currentSize.get();
    }

    private boolean dip(int[][] heightData, int i, int j) {
        int currentNumber = heightData[i][j];
        if (i != 0 && heightData[i - 1][j] <= currentNumber) {
            return false;
        }
        if (j != 0 && heightData[i][j - 1] <= currentNumber) {
            return false;
        }
        if (i != heightData.length - 1 && heightData[i + 1][j] <= currentNumber) {
            return false;
        }
        return j == heightData[i].length - 1 || heightData[i][j + 1] > currentNumber;
    }

    private int[][] mapToIntGrid(List<String> lines) {
        return lines.stream()
                .map(line -> IntStream.range(0, line.length())
                        .map(j -> Integer.parseInt(String.valueOf(line.charAt(j))))
                        .toArray())
                .toArray(size -> new int[size][1]);
    }

}
