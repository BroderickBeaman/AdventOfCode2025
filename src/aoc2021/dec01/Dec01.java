package aoc2021.dec01;

import framework.AOCParent;

import java.util.List;

public class Dec01 extends AOCParent {

    private List<Long> depths;

    @Override
    public void loadInput() {
        depths = InputLoader.loadDepths();
    }

    @Override
    public void part1() {
        long tally = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) {
                tally++;
            }
        }
        printSolution(tally);
    }

    @Override
    public void part2() {
        long tally = 0;
        for (int i = 3; i < depths.size(); i++) {
            if (windowSum(i) > windowSum(i - 1)) {
                tally++;
            }
        }
        printSolution(tally);
    }

    private long windowSum(int index) {
        return depths.get(index) + depths.get(index - 1) + depths.get(index - 2);
    }
}
