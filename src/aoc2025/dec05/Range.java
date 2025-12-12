package aoc2025.dec05;

import java.util.Optional;

public record Range(Long low, Long high) implements Comparable<Range>{

    public boolean isInRange(long test) {
        return test >= low && test <= high;
    }

    public long size() {
        return high - low + 1;
    }

    public Optional<Range> nonOverlap(Range other) {
        //complete overlap
        if (other.high() >= this.high()) {
            return Optional.empty();
        }
        return Optional.of(new Range(Math.max(other.high() + 1, this.low()), this.high()));
    }

    @Override
    public int compareTo(Range o) {
        int lowerCompare = this.low().compareTo(o.low());
        if (lowerCompare == 0) {
            return this.high().compareTo(o.high());
        }
        return lowerCompare;
    }
}
