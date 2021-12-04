package rhykee.solver.task04;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class BingoBoard {

    private final Map<Integer, BoardPosition> positionsByValue = new HashMap<>();
    private final BoardPosition[][] positions;
    private final Map<Integer, Integer> rowCounts = new HashMap<>();
    private final Map<Integer, Integer> columnCounts = new HashMap<>();

    public BingoBoard(String boardInput) {
        positions = Arrays.stream(boardInput.split("\n"))
                .map(line -> Arrays.stream(line.trim().split("\s+"))
                        .map(boardNumber -> new BoardPosition(Integer.parseInt(boardNumber)))
                        .toArray(BoardPosition[]::new))
                .toArray(BoardPosition[][]::new);
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                BoardPosition currentPos = positions[i][j];
                currentPos.setRow(i);
                currentPos.setColumn(j);
                positionsByValue.put(currentPos.value, currentPos);
            }
        }
        for (int i = 0; i < 5; i++) {
            rowCounts.put(i, 0);
            columnCounts.put(i, 0);
        }
    }

    public boolean markNumberIsBingo(int number) {
        if (positionsByValue.containsKey(number)) {
            BoardPosition boardPosition = positionsByValue.get(number);
            boardPosition.setMarked(true);
            int currentRow = boardPosition.row;
            int currentColumn = boardPosition.column;
            rowCounts.computeIfPresent(currentRow, (key, currentCount) -> currentCount + 1);
            columnCounts.computeIfPresent(currentColumn, (key, currentCount) -> currentCount + 1);
            return rowCounts.get(currentRow) == 5 || columnCounts.get(currentColumn) == 5;
        }
        return false;
    }

    public long getResultOfBingo(int lastCalledNumber) {
        return Arrays.stream(positions)
                .flatMap(Arrays::stream)
                .filter(pos -> !pos.isMarked())
                .mapToLong(BoardPosition::getValue)
                .sum() * lastCalledNumber;
    }

    @Data
    private static class BoardPosition {
        private final int value;
        private boolean isMarked;
        private int row;
        private int column;
    }

}
