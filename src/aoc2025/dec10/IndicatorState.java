package aoc2025.dec10;

import java.util.List;

public record IndicatorState(List<Boolean> indicators, List<Integer> buttonPressed, long numPresses) {
}
