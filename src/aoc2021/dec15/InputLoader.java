package aoc2021.dec15;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Grid<Integer> loadRiskMapP1() {
        List<String> lines = loadLines();
        final int rows = lines.size();
        final int cols = lines.getFirst().length();
        Grid<Integer> grid = new Grid(Integer.class, rows, cols);
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                grid.set(Integer.parseInt(line.charAt(col) + ""), row, col);
            }
        }
        return grid;
    }

    public static Grid<Integer> loadRiskMapP2() {
        List<String> lines = loadLines();
        final int rows = lines.size();
        final int cols = lines.getFirst().length();
        Grid<Integer> grid = new Grid(Integer.class, rows * 5, cols * 5);
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                grid.set(Integer.parseInt(line.charAt(col) + ""), row, col);
            }
        }

        for (int row = rows; row < rows * 5; row++) {
            for (int col = 0; col < cols; col++) {
                int value = grid.get(row - rows, col) + 1;
                if (value == 10) {
                    value = 1;
                }
                grid.set(value, row, col);
            }
        }

        for (int col = cols; col < cols * 5; col++) {
            for (int row = 0; row < rows * 5; row++) {
                int value = grid.get(row, col - cols) + 1;
                if (value == 10) {
                    value = 1;
                }
                grid.set(value, row, col);
            }
        }
        return grid;
    }
}
