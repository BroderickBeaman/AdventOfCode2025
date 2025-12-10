package dec10;

import framework.AOCParent;

import java.util.*;

public class Dec10 extends AOCParent {

    private List<Machine> machines;

    @Override
    public void loadInput() {
        machines = InputLoader.loadMachines();
    }

    @Override
    public void part1() {
        long sum = 0;
        for (Machine machine : machines) {
            Queue<IndicatorState> stateQueue = new LinkedList<>();
            Boolean[] initialState = new Boolean[machine.indicators().length];
            Arrays.fill(initialState, false);

            Set<Boolean[]> seen = new HashSet<>();

            for (List<Integer> button : machine.buttons()) {
                stateQueue.add(new IndicatorState(cloneIndicator(initialState), button, 1));
            }

            while (!stateQueue.isEmpty()) {
                IndicatorState indicatorState = stateQueue.poll();
                Boolean[] currentState = indicatorState.indicators();
                toggleIndicators(currentState, indicatorState.buttonPressed());

                if (indicatorsEqual(machine.indicators(), currentState)) {
                    sum += indicatorState.numPresses();
                    break;
                }

                if (seen.contains(currentState)) {
                    continue;
                }

                seen.add(currentState);

                for (List<Integer> button : machine.buttons()) {
                    stateQueue.add(new IndicatorState(cloneIndicator(currentState), button, indicatorState.numPresses() + 1));
                }
            }
        }
        printSolution(sum);
    }

    private boolean indicatorsEqual(Boolean[] target, Boolean[] test) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != test[i]) {
                return false;
            }
        }
        return true;
    }

    private void toggleIndicators(Boolean[] indicators, List<Integer> button) {
        for (Integer index : button) {
            indicators[index] = !indicators[index];
        }
    }

    private Boolean[] cloneIndicator(Boolean[] indicator) {
        Boolean[] newIndicator = new Boolean[indicator.length];
        for (int i = 0; i < indicator.length; i++) {
            newIndicator[i] = indicator[i];
        }
        return newIndicator;
    }


    @Override
    public void part2() {

    }
}
