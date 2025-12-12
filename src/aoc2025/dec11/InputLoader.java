package aoc2025.dec11;

import framework.InputLoaderParent;

import java.util.*;

public class InputLoader extends InputLoaderParent {

    public static Map<String, Set<String>> loadServerRack() {
        Map<String, Set<String>> map = new HashMap<>();
        for (String line : loadLines()) {
            String[] parts = line.split(" ");
            String key = parts[0].substring(0, parts[0].length() - 1);
            Set<String> values = new HashSet<>();
            for (int i = 1; i < parts.length; i++) {
                values.add(parts[i]);
            }
            map.put(key, values);
        }
        return map;
    }
}
