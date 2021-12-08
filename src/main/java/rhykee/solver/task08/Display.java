package rhykee.solver.task08;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Display {

    private final List<Set<Character>> patterns;
    private final List<Set<Character>> numbers;
    private final BiMap<Integer, Set<Character>> patternMap = HashBiMap.create();
    /*      0
        *********
        *       *
      1 *       * 2
        *   3   *
        *********
        *       *
      4 *       * 5
        *       *
        *********
            6
     */
    Map<Integer, Set<Character>> segmentWiring = new HashMap<>();

    @SuppressWarnings("ConstantConditions")
    public Display(String line) {
        String[] parts = line.split("\s+\\|\s+");
        patterns = split(parts[0]);
        numbers = split(parts[1]);
        //Set trivial numbers
        patterns.forEach(s -> {
            switch (s.size()) {
                case 2 -> patternMap.put(1, s);
                case 3 -> patternMap.put(7, s);
                case 4 -> patternMap.put(4, s);
                case 7 -> patternMap.put(8, s);
            }
        });
        //Set initial wiring
        //Top position's wiring is the character that appears in 7 but not in 1
        Set<Character> topPosition = new HashSet<>(patternMap.get(7));
        topPosition.removeAll(patternMap.get(1));
        segmentWiring.put(0, topPosition);

        //Position 2 and 5 are the characters of 1
        Set<Character> twoFive = new HashSet<>(patternMap.get(1));
        segmentWiring.put(2, twoFive);
        segmentWiring.put(5, twoFive);

        //Position 1 and 3 are the characters that appear in 4 but not in 1
        Set<Character> oneThree = new HashSet<>(patternMap.get(4));
        oneThree.removeAll(patternMap.get(1));
        segmentWiring.put(1, oneThree);
        segmentWiring.put(3, oneThree);

        //Position 4 and 6 are the characters that not appear in 4 and 7
        Set<Character> fourSix = getAllChars();
        fourSix.removeAll(patternMap.get(4));
        fourSix.removeAll(patternMap.get(7));
        segmentWiring.put(4, fourSix);
        segmentWiring.put(6, fourSix);

        /*
            Positions 2 and 5 can be clarified by considering the number 6, which contains the segments:
            0,(1,3),(4,6),5 (using segment 2 instead of 5 is an impossible number, it won't appear in the input)
         */
        calculateSegments(5, 2, 6, 6);

        /*
            Positions 4 and 6 can be clarified by considering the number 9, which contains the segments:
            0,(1,3),(2,5),6 (using segment 4 instead of 6 is an impossible number, it won't appear in the input)
         */
        calculateSegments(6, 4, 6, 9);

         /*
            Positions 1 and 3 can be clarified by considering the number 0, which contains the segments:
            0,(2,5),(4,6),1 (using segment 3 instead of 1 is an impossible number, it won't appear in the input)
         */
        calculateSegments(1, 3, 6, 0);

        //Add missing numberPatterns: 2,3,5
        addPattern(2, 0, 2, 3, 4, 6);
        addPattern(3, 0, 2, 3, 5, 6);
        addPattern(5, 0, 1, 3, 5, 6);
    }

    public int getNumber() {
        return Integer.parseInt(numbers.stream()
                .map(characters -> patternMap.inverse().get(characters))
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }

    private void calculateSegments(int segmentContained, int segmentNotContained, int size, int targetNumber) {
        Set<Character> numberPart = getAllChars();
        numberPart.removeAll(segmentWiring.get(segmentContained));
        Set<Character> number = patterns.stream()
                .filter(characters -> characters.size() == size)
                .filter(characters -> characters.containsAll(numberPart))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cant find number " + targetNumber));
        patternMap.put(targetNumber, number);
        Set<Character> position = new HashSet<>(segmentWiring.get(segmentContained));
        position.retainAll(number);
        segmentWiring.put(segmentContained, position);
        segmentWiring.get(segmentNotContained).removeAll(segmentWiring.get(segmentContained));
    }

    private void addPattern(int number, int... segments) {
        patternMap.put(number, Arrays.stream(segments)
                .mapToObj(segment -> segmentWiring.get(segment))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }

    private Set<Character> getAllChars() {
        return new HashSet<>(patternMap.get(8));
    }

    private List<Set<Character>> split(String input) {
        return Arrays.stream(input.split(" "))
                .map(String::toCharArray)
                .map(ArrayUtils::toObject)
                .map(array -> Arrays.stream(array).collect(Collectors.toSet()))
                .toList();
    }

    public int getNumberOf1478() {
        AtomicInteger count = new AtomicInteger(0);
        numbers.forEach(s -> {
            switch (s.size()) {
                case 2, 3, 4, 7 -> count.incrementAndGet();
            }
        });
        return count.get();
    }

}
