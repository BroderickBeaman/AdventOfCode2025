package aoc2025.dec08;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Point3D> loadPoints() {
        List<String> lines = loadLines();
        return lines.stream().map(line -> {
            String[] parts = line.split(",");
            return new Point3D(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
        }).toList();
    }
}
