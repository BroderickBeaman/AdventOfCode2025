package aoc2015.dec02;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Box> loadBoxes() {
        List<String> allLines = loadLines();

        return allLines.stream().map(line -> {
            String[] segments = line.split("x");
            return new Box(Long.parseLong(segments[0]), Long.parseLong(segments[1]), Long.parseLong(segments[2]));
        }).toList();
    }
}
