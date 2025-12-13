package aoc2021.dec05;

import framework.AOCParent;
import framework.utils.Coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dec05 extends AOCParent {

    private List<Line> lines;

    @Override
    public void loadInput() {
        lines = InputLoader.loadRanges();
    }

    @Override
    public void part1() {
        List<Line> orthogonal = lines.stream().filter(Line::isOrthogonal).toList();
        Set<Coordinate> overlaps = new HashSet<>();
        for (int i = 0; i < orthogonal.size() - 1; i++) {
            for (int j = i + 1; j < orthogonal.size(); j++) {
                overlaps.addAll(orthogonal.get(i).overlapOrthogonal(orthogonal.get(j)));
            }
        }

        printSolution(overlaps.size());
    }

    @Override
    public void part2() {

    }
}
