package aoc2021.dec03;

import framework.AOCParent;
import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Dec03 extends AOCParent {

    private List<String> lines;

    @Override
    public void loadInput() {
        lines = InputLoaderParent.loadLines();
    }

    @Override
    public void part1() {
        String toggleString = "1".repeat(lines.getFirst().length());

        StringBuilder binaryString = new StringBuilder();

        for (int i = 0; i < lines.getFirst().length(); i++) {
            int oneTally = 0;
            int zeroTally = 0;
            for (String line : lines) {
                if (line.charAt(i) == '1') {
                    oneTally++;
                } else {
                    zeroTally++;
                }
            }
            if (oneTally > zeroTally) {
                binaryString.append("1");
            } else {
                binaryString.append("0");
            }
        }

        int gamma = Integer.parseInt(binaryString.toString(), 2);
        int epsilon = gamma ^ Integer.parseInt(toggleString, 2);
        printSolution(gamma * epsilon);
    }

    @Override
    public void part2() {
        List<String> tempLines = new ArrayList<>(lines);
        for (int i = 0; i < tempLines.getFirst().length(); i++) {
            int oneTally = 0;
            int zeroTally = 0;
            for (String line : tempLines) {
                if (line.charAt(i) == '1') {
                    oneTally++;
                } else {
                    zeroTally++;
                }
            }
            int index = i;
            Predicate<String> filterCriteria = oneTally >= zeroTally ?
                    num -> num.charAt(index) == '1' : num -> num.charAt(index) == '0';
            tempLines = tempLines.stream().filter(filterCriteria).collect(Collectors.toList());
            if (tempLines.size() == 1) {
                break;
            }
        }

        if (tempLines.size() != 1) {
            throw new RuntimeException("We should have filtered down to 1 line. Actual number of lines: " + tempLines.size());
        }

        final int oxygen = Integer.parseInt(tempLines.getFirst(), 2);

        tempLines = new ArrayList<>(lines);
        for (int i = 0; i < tempLines.getFirst().length(); i++) {
            int oneTally = 0;
            int zeroTally = 0;
            for (String line : tempLines) {
                if (line.charAt(i) == '1') {
                    oneTally++;
                } else {
                    zeroTally++;
                }
            }
            int index = i;
            Predicate<String> filterCriteria = oneTally >= zeroTally ?
                    num -> num.charAt(index) == '0' : num -> num.charAt(index) == '1';
            tempLines = tempLines.stream().filter(filterCriteria).collect(Collectors.toList());
            if (tempLines.size() == 1) {
                break;
            }
        }

        if (tempLines.size() != 1) {
            throw new RuntimeException("We should have filtered down to 1 line. Actual number of lines: " + tempLines.size());
        }

        final int co2 = Integer.parseInt(tempLines.getFirst(), 2);

        printSolution(oxygen * co2);
    }
}
