package aoc2025.dec02;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Range> loadRanges() {
        String line = loadLines().getFirst();
        String[] rangeParts = line.split(",");
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < rangeParts.length; i++) {
            String[] rangeHalves = rangeParts[i].split("-");
            ranges.add(new Range(Long.parseLong(rangeHalves[0]), Long.parseLong(rangeHalves[1])));
        }
        return ranges;
    }
}
