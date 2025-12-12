package aoc2025.dec09;

import framework.InputLoaderParent;

import java.awt.*;
import java.util.List;

public class InputLoader extends InputLoaderParent {
    public static List<Point> loadPoints() {
        return loadLines().stream().map(line -> {
            String[] parts = line.split(",");
            return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }).toList();
    }
}
