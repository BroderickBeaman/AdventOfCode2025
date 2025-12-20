package aoc2021.dec10;

import framework.AOCParent;
import framework.InputLoaderParent;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Dec10 extends AOCParent {

    private final Set<Character> CLOSING = Set.of(')', ']', '}', '>');

    List<String> lines;

    @Override
    public void loadInput() {
        lines = InputLoaderParent.loadLines();
    }

    @Override
    public void part1() {
        long sum = 0;
        for (String line : lines) {
            Stack<Character> stack = new Stack<>();
            for (Character character : line.toCharArray()) {
                if (CLOSING.contains(character)) {
                    if (stack.isEmpty() || !matches(stack.pop(), character)) {
                        sum += mapCharToScorePt1(character);
                        break;
                    }
                } else {
                    stack.push(character);
                }
            }
        }
        printSolution(sum);
    }

    @Override
    public void part2() {
        List<Long> scores = lines.stream().map(line -> {
            Stack<Character> stack = new Stack<>();
            for (Character character : line.toCharArray()) {
                if (CLOSING.contains(character)) {
                    if (stack.isEmpty() || !matches(stack.pop(), character)) {
                        return new Stack<>();
                    }
                } else {
                    stack.push(character);
                }
            }
            return stack;
        }).filter(stack -> !stack.isEmpty()).map(stack -> {
            long score = 0;
            while (!stack.isEmpty()) {
                score = (score * 5) + mapCharToScorePt2((Character) stack.pop());
            }
            return score;
        }).sorted(Long::compareTo).toList();

        printSolution(scores.get(scores.size()/2));
    }

    private long mapCharToScorePt1(char charIn) {
        return switch (charIn) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new IllegalArgumentException("Unsupported character: " + charIn);
        };
    }

    private long mapCharToScorePt2(char charIn) {
        return switch (charIn) {
            case '(' -> 1;
            case '[' -> 2;
            case '{' -> 3;
            case '<' -> 4;
            default -> throw new IllegalArgumentException("Unsupported character: " + charIn);
        };
    }

    private boolean matches(char opening, char closing) {
        return switch (closing) {
            case ')' -> opening == '(';
            case ']' -> opening == '[';
            case '}' -> opening == '{';
            case '>' -> opening == '<';
            default -> throw new IllegalArgumentException("Unsupported character: " + closing);
        };
    }
}
