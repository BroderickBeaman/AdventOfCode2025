package aoc2025.dec05;

import framework.AOCParent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Dec05 extends AOCParent {

    private List<Range> ranges;
    private List<Long> ingredients;

    @Override
    public void loadInput() {
        ranges = InputLoader.loadRanges();
        ingredients = InputLoader.loadIngredients();
    }

    @Override
    public void part1() {
        long tally = 0;
        for (Long ingredient : ingredients) {
            for (Range range : ranges) {
                if (range.isInRange(ingredient)) {
                    tally++;
                    break;
                }
            }
        }
        printSolution(tally);
    }

    @Override
    public void part2() {
        Collections.sort(ranges);
        List<Range> processedRanges = new ArrayList<>();
        long tally = 0;
        for (Range range : ranges) {
            Optional<Range> current = Optional.of(range);
            for (Range processed : processedRanges) {
                current = current.get().nonOverlap(processed);
                if (current.isEmpty()) {
                    break;
                }
            }
            if (current.isPresent()) {
                tally += current.get().size();
            }
            processedRanges.add(range);
        }

        printSolution(tally);
    }
}
