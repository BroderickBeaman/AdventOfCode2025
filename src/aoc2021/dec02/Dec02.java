package aoc2021.dec02;

import framework.AOCParent;

import java.util.List;

public class Dec02 extends AOCParent {

    private List<Command> commands;

    @Override
    public void loadInput() {
        commands = InputLoader.loadCommands();
    }

    @Override
    public void part1() {
        long vertPos = 0;
        long horzPos = 0;
        for (Command command : commands) {
            switch (command.direction()) {
                case DOWN -> vertPos += command.value();
                case UP -> vertPos -= command.value();
                case FORWARD -> horzPos += command.value();
            }
        }
        printSolution(horzPos * vertPos);
    }

    @Override
    public void part2() {
        long vertPos = 0;
        long horzPos = 0;
        long aim = 0;
        for (Command command : commands) {
            switch (command.direction()) {
                case DOWN -> aim += command.value();
                case UP -> aim -= command.value();
                case FORWARD -> {
                    horzPos += command.value();
                    vertPos += aim * command.value();
                }
            }
        }
        printSolution(horzPos * vertPos);
    }
}
