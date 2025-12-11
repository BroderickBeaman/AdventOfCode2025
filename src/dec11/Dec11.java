package dec11;

import framework.AOCParent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dec11 extends AOCParent {

    private Map<String, Set<String>> serverRack;
    private Map<String, Long> pathCache;

    @Override
    public void loadInput() {
        serverRack = InputLoader.loadServerRack();
    }

    @Override
    public void part1() {
        pathCache = new HashMap<>();
        Set<String> visited = new HashSet<>();
        visited.add("you");
        printSolution(numPathsAtNode("you", visited));
    }

    private long numPathsAtNode(String current, Set<String> visited) {
        if (pathCache.containsKey(current)) {
            return pathCache.get(current);
        }

        if (current.equals("out")) {
            return 1;
        }

        if (!serverRack.containsKey(current)) {
            pathCache.put(current, 0L);
            return 0L;
        }

        Set<String> currentVisited = new HashSet<>(visited);
        currentVisited.add(current);
        long tally = 0;
        for (String connected : serverRack.get(current)) {
            // Prevent infinite loop
            if (currentVisited.contains(connected)) {
                continue;
            }
            tally += numPathsAtNode(connected, currentVisited);
        }

        pathCache.put(current, tally);
        return tally;
    }

    @Override
    public void part2() {

    }
}
