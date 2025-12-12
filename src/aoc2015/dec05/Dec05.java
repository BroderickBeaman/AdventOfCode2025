package aoc2015.dec05;

import framework.AOCParent;
import framework.InputLoaderParent;

import java.util.List;
import java.util.Set;

public class Dec05 extends AOCParent {

    private final static Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    private final static Set<String> bannedCombos = Set.of("ab", "cd", "pq", "xy");

    @Override
    public void loadInput() {

    }

    @Override
    public void part1() {
        List<String> allLines = InputLoaderParent.loadLines();

        long niceLines = allLines.stream().filter(Dec05::isNicePart1).count();
        System.out.println("Number of nice lines: " + niceLines);

    }

    @Override
    public void part2() {
        List<String> allLines = InputLoaderParent.loadLines();

        long niceLines = allLines.stream().filter(Dec05::isNicePart2).count();
        System.out.println("Number of nice lines: " + niceLines);

    }

    private static boolean isNicePart1(String line) {
        return !containsBannedCombo(line) && containsThreeVowels(line) && containsDoubleCharacter(line);
    }

    private static boolean containsBannedCombo(String line) {
        for (String combo : bannedCombos) {
            if (line.contains(combo)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsThreeVowels(String line) {
        int vowelCount = 0;
        for (Character character : line.toCharArray()) {
            if (vowels.contains(character)) {
                vowelCount++;
                if (vowelCount == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsDoubleCharacter(String line) {
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == line.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNicePart2(String line) {
        return containsTwoPairs(line) && wraps(line);
    }

    private static boolean containsTwoPairs(String line) {
        for (int i = 0; i < line.length() - 1; i++) {
            String pair = line.substring(i, i + 2);
            if (line.lastIndexOf(pair) > i + 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean wraps(String line) {
        for (int i = 2; i < line.length(); i++) {
            if (line.charAt(i) == line.charAt(i - 2)) {
                return true;
            }
        }
        return false;
    }
}
