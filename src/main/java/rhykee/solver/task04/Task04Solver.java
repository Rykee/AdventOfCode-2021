package rhykee.solver.task04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task04Solver {

    public void part1(List<String> lines) {
        List<Integer> bingoNumbers = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        String wholeBoardInput = String.join("\n", lines.subList(2, lines.size()));
        List<BingoBoard> bingoBoards = Arrays.stream(wholeBoardInput.split("\n\n"))
                .map(BingoBoard::new)
                .toList();
        for (Integer bingoNumber : bingoNumbers) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.markNumberIsBingo(bingoNumber)) {
                    System.out.println("Day 4 1/2: " + bingoBoard.getResultOfBingo(bingoNumber));
                    return;
                }
            }
        }
    }

    public void part2(List<String> lines) {
        List<Integer> bingoNumbers = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        String wholeBoardInput = String.join("\n", lines.subList(2, lines.size()));
        List<BingoBoard> bingoBoards = Arrays.stream(wholeBoardInput.split("\n\n"))
                .map(BingoBoard::new)
                .collect(Collectors.toList());
        for (Integer bingoNumber : bingoNumbers) {
            for (BingoBoard bingoBoard : new ArrayList<>(bingoBoards)) {
                if (bingoBoard.markNumberIsBingo(bingoNumber)) {
                    if (bingoBoards.size() == 1) {
                        System.out.println("Day 4 2/2: " + bingoBoard.getResultOfBingo(bingoNumber));
                        return;
                    }
                    bingoBoards.remove(bingoBoard);
                }
            }
        }
    }

}
