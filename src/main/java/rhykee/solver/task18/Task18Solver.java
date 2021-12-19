package rhykee.solver.task18;

import rhykee.solver.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task18Solver implements Challenge {

    private static final Pattern MAGNITUDE_MATCHER = Pattern.compile("\\[(?<left>[0-9]+),(?<right>[0-9]+)]");

    @Override
    public void part1(List<String> lines) {
        int magnitude = lines.stream()
                .reduce((s, s2) -> reduce(add(s, s2)))
                .map(this::getMagnitude)
                .get();
        System.out.println("Day 18 1/2: " + magnitude);
    }

    @Override
    public void part2(List<String> lines) {
        List<String> additions = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (i == j) {
                    continue;
                }
                additions.add(add(lines.get(i), lines.get(j)));
            }
        }
        int maxMagnitude = additions.stream()
                .map(this::reduce)
                .map(this::getMagnitude)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        System.out.println("Day 18 2/2: " + maxMagnitude);
    }

    private String reduce(String sum) {
        do {
            while (!explode(sum).equals(sum)) {
                sum = explode(sum);
            }
            sum = split(sum);
        } while (!explode(sum).equals(sum) || !split(sum).equals(sum));
        return sum;
    }

    private String split(String input) {
        int splitPosition = getSplitPosition(input);
        if (splitPosition == -1) {
            return input;
        }
        int splitee = Integer.parseInt(input.substring(splitPosition, splitPosition + 2));
        int left = splitee / 2;
        int right = (int) Math.ceil(splitee / 2.0);
        return input.substring(0, splitPosition) + "[" + left + "," + right + "]" + input.substring(splitPosition + 2);
    }

    private int getSplitPosition(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.substring(i, i + 2).matches("[0-9]{2}")) {
                return i;
            }
        }
        return -1;
    }

    private String explode(String input) {
        int startIndex = getExplodePosition(input);
        if (startIndex == -1) {
            return input;
        }
        int closingBracketIndex = input.indexOf(']', startIndex);
        String explodingPair = input.substring(startIndex, closingBracketIndex + 1);
        int comma = explodingPair.indexOf(',');
        int leftNumber = Integer.parseInt(explodingPair.substring(1, comma));
        int rightNumber = Integer.parseInt(explodingPair.substring(comma + 1, explodingPair.length() - 1));
        for (int i = startIndex - 1; i >= 0; i--) {
            if (String.valueOf(input.charAt(i)).matches("[0-9]")) {
                if (String.valueOf(input.charAt(i - 1)).matches("[0-9]")) {
                    int newLeftNumber = Integer.parseInt(input.substring(i - 1, i + 1)) + leftNumber;
                    input = input.substring(0, i - 1) + newLeftNumber + input.substring(i + 1);
                } else {
                    int newLeftNumber = Integer.parseInt(input.substring(i, i + 1)) + leftNumber;
                    input = input.substring(0, i) + newLeftNumber + input.substring(i + 1);
                }
                break;
            }
        }
        for (int i = closingBracketIndex + 1; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[0-9]")) {
                if (String.valueOf(input.charAt(i + 1)).matches("[0-9]")) {
                    int newRightNumber = Integer.parseInt(input.substring(i, i + 2)) + rightNumber;
                    input = input.substring(0, i) + newRightNumber + input.substring(i + 2);
                } else {
                    int newRightNumber = Integer.parseInt(input.substring(i, i + 1)) + rightNumber;
                    input = input.substring(0, i) + newRightNumber + input.substring(i + 1);
                }
                break;
            }
        }
        startIndex = getExplodePosition(input);
        closingBracketIndex = input.indexOf(']', startIndex);
        input = input.substring(0, startIndex) + 0 + input.substring(closingBracketIndex + 1);
        return input;
    }

    private String add(String left, String right) {
        return "[" + left + "," + right + "]";
    }

    private int getExplodePosition(String input) {
        int currentOpeningCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '[') {
                currentOpeningCount++;
            } else if (currentChar == ']') {
                currentOpeningCount--;
            }
            if (currentOpeningCount == 5) {
                return i;
            }
        }
        return -1;
    }

    private int getMagnitude(String input) {
        int magnitude = 0;
        while (input.matches(".*\\[[0-9]+,[0-9]+].*")) {
            Matcher matcher = MAGNITUDE_MATCHER.matcher(input);
            matcher.find();
            int left = Integer.parseInt(matcher.group("left"));
            int right = Integer.parseInt(matcher.group("right"));
            magnitude = left * 3 + right * 2;
            input = input.replaceFirst(MAGNITUDE_MATCHER.pattern(), String.valueOf(magnitude));
        }
        return magnitude;
    }

}
