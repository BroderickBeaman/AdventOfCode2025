package dec01;

import framework.AOCParent;

import java.util.List;

public class Dec01 extends AOCParent {

    private List<Integer> commands;

    @Override
    public void loadInput() {
        commands = InputLoader.loadCommands();
    }

    @Override
    public void part1() {
        int current = 50;
        int zeroCount = 0;
        for (Integer command : commands) {
            current += command;
            current = (current + 100) % 100;
            if (current == 0) {
                zeroCount++;
            }
        }

        printSolution(zeroCount);
    }

    @Override
    public void part2() {
        int current = 50;
        int zeroCount = 0;

        for (Integer command : commands) {
            if (Math.abs(command / 100) > 0) {
                zeroCount += Math.abs(command / 100);
                command = command % 100;
            }

            boolean startingAtZero = current == 0;

            current += command;

            if (!startingAtZero && (current <= 0 || current > 99)) {
                zeroCount++;
            }

            current = (current + 100) % 100;
        }

        printSolution(zeroCount);
    }
}
