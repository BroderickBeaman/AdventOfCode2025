package dec10;

import java.util.List;

public record IndicatorState(List<Boolean> indicators, List<Integer> buttonPressed, long numPresses) {
}
