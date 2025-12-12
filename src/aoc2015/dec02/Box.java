package aoc2015.dec02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Box(long l, long w, long h) {

    public long surfaceArea() {
        long face1 = l * w;
        long face2 = l * h;
        long face3 = w * h;

        return 2 * face1 +
                2 * face2 +
                2 * face3 +
                Math.min(face1, Math.min(face2, face3));
    }

    public long ribbon() {
        List<Long> dimensions = new ArrayList<>(List.of(l, w, h));
        Collections.sort(dimensions);

        return dimensions.get(0) * 2 +
                dimensions.get(1) * 2 +
                l * w * h;
    }
}
