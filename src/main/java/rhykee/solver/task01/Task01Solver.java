package rhykee.solver.task01;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task01Solver {

    public void countIncrements(List<String> input) {
        AtomicInteger counter = new AtomicInteger(0);
        input.stream()
                .map(Integer::parseInt)
                .reduce((prev, current) -> {
                    if (current > prev) counter.addAndGet(1);
                    return current;
                });
        System.out.println("Day 1 1/2: " + counter.get());
    }

    public void countSlidingIncrements(List<String> input) {
        List<Integer> depths = input.stream().map(Integer::parseInt).toList();
        int counter = 0;
        int currentSum = depths.get(0) + depths.get(1) + depths.get(2);
        for (int i = 3; i < depths.size(); i++) {
            int newSum = currentSum - depths.get(i - 3) + depths.get(i);
            if (newSum > currentSum) counter++;
            currentSum = newSum;
        }
        System.out.println("Day 1 2/2: " + counter);
    }
}
