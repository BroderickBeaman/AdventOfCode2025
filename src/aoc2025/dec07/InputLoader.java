package aoc2025.dec07;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Grid<Manifold> loadManifold() {
        List<String> lines = loadLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();

        Grid<Manifold> manifold = new Grid<>(Manifold.class, rows, cols);

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                manifold.set(Manifold.fromChar(line.charAt(col)), row, col);
            }
        }

        return manifold;
    }
}
