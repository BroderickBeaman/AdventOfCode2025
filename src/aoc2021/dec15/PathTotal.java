package aoc2021.dec15;

import framework.utils.Coordinate;

public record PathTotal(Coordinate location, Long currentTotal) implements Comparable<PathTotal> {
    @Override
    public int compareTo(PathTotal o) {
        return this.currentTotal.compareTo(o.currentTotal);
    }
}
