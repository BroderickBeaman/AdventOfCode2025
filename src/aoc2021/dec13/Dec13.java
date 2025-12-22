package aoc2021.dec13;

import framework.AOCParent;
import framework.utils.Grid;

import java.util.List;

public class Dec13 extends AOCParent {

    private Grid<Boolean> grid;
    private List<Fold> folds;

    @Override
    public void loadInput() {
        grid = InputLoader.loadGrid();
        folds = InputLoader.loadFolds();
    }

    @Override
    public void part1() {
        Grid<Boolean> folded = fold(folds.getFirst(), new Grid<>(Boolean.class, grid));
        printSolution(folded.findValues(true).size());
    }

    @Override
    public void part2() {
        Grid<Boolean> folded = new Grid<>(Boolean.class, grid);
        for (Fold fold : folds) {
            folded = fold(fold, folded);
        }
        for (int row = 0; row < folded.rows(); row ++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < folded.cols(); col++) {
                if (folded.get(row, col)) {
                    line.append('#');
                } else {
                    line.append(' ');
                }
            }
            System.out.println(line);
        }
        printSolution("No-op to get completion time");
    }

    private Grid<Boolean> fold(Fold fold, Grid<Boolean> grid) {
        Grid<Boolean> clone = new Grid<>(Boolean.class, grid);
        return fold.horizontal() ? horizontalFold(fold, clone) : verticalFold(fold, clone);
    }

    private Grid<Boolean> horizontalFold(Fold fold, Grid<Boolean> grid) {
        for (int i = 1; fold.value() + i < grid.rows() && fold.value() - i >= 0; i++) {
            for (int col = 0; col < grid.cols(); col++) {
                boolean result = grid.get(fold.value() + i, col) || grid.get(fold.value() - i, col);
                grid.set(result, fold.value() - i, col);
            }
        }
        Grid<Boolean> folded = new Grid<>(Boolean.class, fold.value(), grid.cols());
        copyGrid(folded, grid);
        return folded;
    }

    private Grid<Boolean> verticalFold(Fold fold, Grid<Boolean> grid) {
        for (int i = 1; fold.value() + i < grid.cols() && fold.value() - i >= 0; i++) {
            for (int row = 0; row < grid.rows(); row++) {
                boolean result = grid.get(row, fold.value() + i) || grid.get(row, fold.value() - i);
                grid.set(result, row, fold.value() - i);
            }
        }
        Grid<Boolean> folded = new Grid<>(Boolean.class, grid.rows(), fold.value());
        copyGrid(folded, grid);
        return folded;
    }

    private void copyGrid(Grid<Boolean> to, Grid<Boolean> from) {
        for (int row = 0; row < to.rows(); row++) {
            for (int col = 0; col < to.cols(); col++) {
                to.set(from.get(row, col), row, col);
            }
        }
    }
}
