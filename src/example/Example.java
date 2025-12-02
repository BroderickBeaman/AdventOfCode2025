package example;

import framework.AOCParent;

import java.util.List;

public class Example extends AOCParent {

    private List<String> linesPart1;
    private List<String> linesPart2;

    @Override
    public void loadInput() {
        linesPart1 = InputLoaderExample.loadLines();
        linesPart2 = InputLoaderExample.loadLines("example.txt");
    }

    @Override
    public void part1() {
        for (String line : linesPart1) {
            printSolution(line);
        }
    }

    @Override
    public void part2() {
        for (String line : linesPart2) {
            printSolution(line);
        }
    }
}
