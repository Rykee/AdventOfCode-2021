package rhykee.solver.task04;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class BingoBoard {

    private final Map<Integer, BoardPosition> positionsByValue = new HashMap<>();
    private final BoardPosition[][] positions;

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
    }

    public boolean markNumberIsBingo(int number) {
        if (positionsByValue.containsKey(number)) {
            BoardPosition boardPosition = positionsByValue.get(number);
            boardPosition.setMarked(true);
            return isItBingo(boardPosition);
        }
        return false;
    }

    public long getResultOfBingo(int lastCalledNumber){
        return Arrays.stream(positions)
                .flatMap(Arrays::stream)
                .filter(pos -> !pos.isMarked())
                .mapToLong(BoardPosition::getValue)
                .sum() * lastCalledNumber;
    }

    private boolean isItBingo(BoardPosition boardPosition) {
        return Arrays.stream(positions[boardPosition.row])
                .allMatch(BoardPosition::isMarked)
                || Arrays.stream(getColumn(boardPosition.column))
                .allMatch(BoardPosition::isMarked);
    }

    private BoardPosition[] getColumn(int column) {
        BoardPosition[] columnPositions = new BoardPosition[positions.length];
        for (int i = 0; i < positions.length; i++) {
            columnPositions[i] = positions[i][column];
        }
        return columnPositions;
    }

    @Data
    private static class BoardPosition {
        private final int value;
        private boolean isMarked;
        private int row;
        private int column;
    }

}
