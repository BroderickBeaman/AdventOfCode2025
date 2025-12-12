package aoc2015.dec01;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static String loadInput() {
        List<String> allLines = loadLines();
        return allLines.get(0);
    }
}
