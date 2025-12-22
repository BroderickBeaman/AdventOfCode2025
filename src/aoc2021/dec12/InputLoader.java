package aoc2021.dec12;

import framework.InputLoaderParent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputLoader extends InputLoaderParent {
    public static Map<String, Set<String>> loadCaveMap() {
        Map<String, Set<String>> caveMap = new HashMap<>();
        for (String line : loadLines()) {
            String[] parts = line.split("-");
            caveMap.putIfAbsent(parts[0], new HashSet<>());
            caveMap.putIfAbsent(parts[1], new HashSet<>());
            caveMap.get(parts[0]).add(parts[1]);
            caveMap.get(parts[1]).add(parts[0]);
        }
        return caveMap;
    }
}
