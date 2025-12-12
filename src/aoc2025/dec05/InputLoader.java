package aoc2025.dec05;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Range> loadRanges() {
        List<String> lines = loadLines("ranges.txt");
        List<Range> ranges = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("-");
            ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
        }

        return ranges;
    }

    public static List<Long> loadIngredients() {
        List<String> lines = loadLines("ingredients.txt");
        return lines.stream().map(Long::parseLong).toList();
    }
}
