package aoc2021.dec01;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Long> loadDepths() {
        return loadLines().stream().map(Long::parseLong).toList();
    }
}
