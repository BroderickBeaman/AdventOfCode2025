package aoc2025.dec12;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<PresentSlot> loadPresents() {
        return loadLines().stream().map(line -> {
            String[] parts = line.split(": ");

            String[] dimensionParts = parts[0].split("x");
            long rows = Long.parseLong(dimensionParts[0]);
            long cols = Long.parseLong(dimensionParts[1]);

            long numPresents = 0;
            for (String num : parts[1].split(" ")) {
                numPresents += Long.parseLong(num);
            }

            return new PresentSlot(rows, cols, numPresents);
        }).toList();
    }
}
