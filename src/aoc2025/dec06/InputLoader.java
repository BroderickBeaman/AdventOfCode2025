package aoc2025.dec06;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Grid<String> loadHomework() {
        List<String> lines = loadLines();
        int rows = lines.size();
        int cols = lines.getFirst().split("\\s+").length;
        Grid<String> homework = new Grid<>(String.class, rows, cols);
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s+");
            for (int j = 0; j < parts.length; j++) {
                homework.set(parts[j], i, j);
            }
        }
        return homework;
    }

    public static Grid<Character> loadHomeworkPt2() {
        List<String> lines = loadLines();
        final int rows = lines.size();
        final int cols = lines.getFirst().length();
        Grid<Character> homework = new Grid<>(Character.class, rows, cols);
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                homework.set(line.charAt(col), row, col);
            }
        }
        return homework;
    }
}
