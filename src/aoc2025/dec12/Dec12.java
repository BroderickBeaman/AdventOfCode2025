package aoc2025.dec12;

import framework.AOCParent;

import java.util.List;

public class Dec12 extends AOCParent {

    private List<PresentSlot> presentSlots;

    @Override
    public void loadInput() {
        presentSlots = InputLoader.loadPresents();
    }

    @Override
    public void part1() {
        long numFits = presentSlots.stream().filter(PresentSlot::fits).count();
        printSolution(numFits);
    }

    @Override
    public void part2() {

    }
}
