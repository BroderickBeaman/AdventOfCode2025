package aoc2021.dec13;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;
import java.util.stream.Collectors;

public class InputLoader extends InputLoaderParent {

    public static Grid<Boolean> loadGrid() {
        List<String> lines = loadLines("coordinates.txt");
        int maxRow = 0, maxCol = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            int row = Integer.parseInt(parts[1]);
            int col = Integer.parseInt(parts[0]);
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }
        Grid<Boolean> grid = new Grid<>(Boolean.class, maxRow + 1, maxCol + 1);
        grid.initWithDefault(false);
        for (String line : lines) {
            String[] parts = line.split(",");
            int row = Integer.parseInt(parts[1]);
            int col = Integer.parseInt(parts[0]);
            grid.set(true, row, col);
        }
        return grid;
    }

    public static List<Fold> loadFolds() {
        return loadLines("folds.txt").stream().map(line -> {
            String[] parts = line.split("=");
            boolean horizontal = parts[0].charAt(parts[0].length() - 1) == 'y';
            int value = Integer.parseInt(parts[1]);
            return new Fold(value, horizontal);
        }).collect(Collectors.toList());
    }
}
