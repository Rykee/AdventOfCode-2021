package rhykee.solver.task10;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Bracket {
    ROUND('(', ')'),
    SQUARE('[', ']'),
    CURLY('{', '}'),
    DIAMOND('<', '>');

    private final char openingBracket;
    private final char closingBracket;

    Bracket(char openingBracket, char closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    public static Bracket fromChar(char input) {
        return Arrays.stream(values())
                .filter(bracket -> bracket.getOpeningBracket() == input
                        || bracket.getClosingBracket() == input)
                .findAny()
                .orElseThrow(() -> new RuntimeException("No enum for " + input));
    }


}
