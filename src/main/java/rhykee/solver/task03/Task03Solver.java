package rhykee.solver.task03;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Task03Solver {

    public void first(List<String> input) {
        Map<Integer, Occurrence> occurrences = getOccurrences(input);

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        occurrences.values().forEach(occurrence -> {
            if (occurrence.getOne() > occurrence.getZero()) {
                gammaRate.append("0");
                epsilonRate.append("1");
            } else {
                gammaRate.append("1");
                epsilonRate.append("0");
            }
        });
        long gammaNumber = Long.parseLong(gammaRate.toString(), 2);
        long epsilonNUmber = Long.parseLong(epsilonRate.toString(), 2);
        System.out.println("Day 3 1/2: " + gammaNumber * epsilonNUmber);
    }

    private Map<Integer, Occurrence> getOccurrences(List<String> input) {
        Map<Integer, Occurrence> occurrences = new HashMap<>();
        for (int i = 0; i < input.get(0).length(); i++) {
            occurrences.put(i, new Occurrence());
        }
        input.stream()
                .map(s -> s.split(""))
                .forEach(parts -> IntStream.range(0, parts.length).forEach(value -> occurrences.get(value).parse(parts[value])));
        return occurrences;
    }

    public void second(List<String> input) {
        List<String> oxygenTemp = new ArrayList<>(input);
        int length = oxygenTemp.get(0).length();
        for (int i = 0; i < length && oxygenTemp.size() > 1; i++) {
            int finalI = i;
            Map<Integer, Occurrence> occurrences = getOccurrences(oxygenTemp);
            oxygenTemp.removeIf(s -> !occurrences.get(finalI).getMostCommon().equals(s.split("")[finalI]));
        }
        int oxygen = Integer.parseInt(oxygenTemp.get(0), 2);
        System.out.println(oxygenTemp.get(0) + " " + oxygen);

        List<String> co2Temp = new ArrayList<>(input);
        for (int i = 0; i < length && co2Temp.size() > 1; i++) {
            int finalI = i;
            Map<Integer, Occurrence> occurrences = getOccurrences(co2Temp);
            co2Temp.removeIf(s -> !occurrences.get(finalI).getLeastCommon().equals(s.split("")[finalI]));
        }
        int co2 = Integer.parseInt(co2Temp.get(0), 2);
        System.out.println(co2Temp.get(0) + " " + co2);
        System.out.println("Day 3 2/2: " + co2*oxygen);
    }

    @Data
    private static class Occurrence {
        private long zero;
        private long one;

        public void parse(String binaryPart) {
            if (binaryPart.equals("0")) {
                zero++;
            } else {
                one++;
            }
        }

        public String getMostCommon() {
            if (one >= zero) {
                return "1";
            } else {
                return "0";
            }
        }

        public String getLeastCommon() {
            if (zero <= one) {
                return "0";
            } else {
                return "1";
            }
        }
    }

}
