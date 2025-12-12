package aoc2025.dec04;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Grid<Boolean> loadGrid() {
        List<String> lines = loadLines();
        Grid<Boolean> grid = new Grid<>(Boolean.class, lines.size(), lines.getFirst().length());

        for (int i = 0; i < grid.rows(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < grid.cols(); j++) {
                if (line.charAt(j) == '@') {
                    grid.set(true, i, j);
                } else {
                    grid.set(false, i, j);
                }
            }
        }

        return grid;
    }
}
