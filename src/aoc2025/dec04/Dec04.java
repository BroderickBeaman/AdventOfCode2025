package aoc2025.dec04;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Grid;

import java.util.Set;

public class Dec04 extends AOCParent {

    Grid<Boolean> grid;

    @Override
    public void loadInput() {
        grid = InputLoader.loadGrid();
    }

    @Override
    public void part1() {
        long sum = 0;
        for (int i = 0; i < grid.rows(); i++) {
            for (int j = 0; j < grid.cols(); j++) {
                Coordinate currentPos = new Coordinate(i, j);
                if (!grid.get(currentPos)) {
                    continue;
                }
                Set<Coordinate> adjacentSet = currentPos.adjacent();
                long currentTally = 0;
                for (Coordinate adjacent : adjacentSet) {
                    if (grid.isInBounds(adjacent) && grid.get(adjacent)) {
                        currentTally++;
                    }
                }
                if (currentTally < 4) {
                    sum++;
                }
            }
        }
        printSolution(sum);
    }

    @Override
    public void part2() {
        long sum = 0;
        long currentIterationSum = 0;
        do {
            currentIterationSum = 0;
            for (int i = 0; i < grid.rows(); i++) {
                for (int j = 0; j < grid.cols(); j++) {
                    Coordinate currentPos = new Coordinate(i, j);
                    if (!grid.get(currentPos)) {
                        continue;
                    }
                    Set<Coordinate> adjacentSet = currentPos.adjacent();
                    long currentTally = 0;
                    for (Coordinate adjacent : adjacentSet) {
                        if (grid.isInBounds(adjacent) && grid.get(adjacent)) {
                            currentTally++;
                        }
                    }
                    if (currentTally < 4) {
                        sum++;
                        currentIterationSum++;
                        grid.set(false, currentPos);
                    }
                }
            }
        } while (currentIterationSum > 0);
        printSolution(sum);
    }
}
