package aoc2021.dec02;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Command> loadCommands() {
        return loadLines().stream().map(line -> {
            String[] parts = line.split(" ");
            return new Command(SubDirection.fromString(parts[0]), Long.parseLong(parts[1]));
        }).toList();
    }
}
