package aoc2025.dec10;

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
            List<Boolean> initialState = new ArrayList<>();
            for (int i = 0; i < machine.indicators().length; i++) {
                initialState.add(false);
            }

            Set<List<Boolean>> seen = new HashSet<>();

            for (List<Integer> button : machine.buttons()) {
                stateQueue.add(new IndicatorState(cloneIndicator(initialState), button, 1));
            }

            while (!stateQueue.isEmpty()) {
                IndicatorState indicatorState = stateQueue.poll();
                List<Boolean> currentState = indicatorState.indicators();
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

    private boolean indicatorsEqual(Boolean[] target, List<Boolean> test) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != test.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void toggleIndicators(List<Boolean> indicators, List<Integer> button) {
        for (Integer index : button) {
            indicators.set(index, !indicators.get(index));
        }
    }

    private List<Boolean> cloneIndicator(List<Boolean> indicator) {
        return new ArrayList<>(indicator);
    }


    @Override
    public void part2() {
        // Used z3, I am not proud :(
    }
}
