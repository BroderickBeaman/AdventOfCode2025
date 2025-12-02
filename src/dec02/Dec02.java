package dec02;

import framework.AOCParent;

import java.util.List;

public class Dec02 extends AOCParent {

    public static List<Range> ranges = InputLoader.loadRanges();

    @Override
    public void part1() {
        long sum = ranges.stream().mapToLong(Range::computeInvalidIdsSumPart1).sum();
        printSolution(sum);
    }

    @Override
    public void part2() {
        long sum = ranges.stream().mapToLong(Range::computeInvalidIdsSumPart2).sum();
        printSolution(sum);
    }
}
