package aoc2025.dec11;

import framework.AOCParent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dec11 extends AOCParent {

    private Map<String, Set<String>> serverRack;
    private Map<String, Long> pathCachePart1;
    private Map<CacheKeyP2, Long> pathCachePart2;

    @Override
    public void loadInput() {
        serverRack = InputLoader.loadServerRack();
    }

    @Override
    public void part1() {
        pathCachePart1 = new HashMap<>();
        Set<String> visited = new HashSet<>();
        visited.add("you");
        printSolution(numPathsAtNode("you", visited));
    }

    private long numPathsAtNode(String current, Set<String> visited) {
        if (pathCachePart1.containsKey(current)) {
            return pathCachePart1.get(current);
        }

        if (current.equals("out")) {
            return 1;
        }

        if (!serverRack.containsKey(current)) {
            pathCachePart1.put(current, 0L);
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

        pathCachePart1.put(current, tally);
        return tally;
    }

    @Override
    public void part2() {
        pathCachePart2 = new HashMap<>();
        printSolution(numPathsAtNodeP2("svr", false, false));
    }

    private long numPathsAtNodeP2(String current, boolean seenFft, boolean seenDac) {
        CacheKeyP2 currentCacheKey = new CacheKeyP2(current, seenFft, seenDac);
        if (pathCachePart2.containsKey(currentCacheKey)) {
            return pathCachePart2.get(currentCacheKey);
        }

        if (current.equals("out")) {
            return seenFft && seenDac ? 1 : 0;
        }

        if (!serverRack.containsKey(current)) {
            return 0L;
        }

        long tally = 0;
        for (String connected : serverRack.get(current)) {
            tally += numPathsAtNodeP2(
                    connected,
                    seenFft || connected.equals("fft"),
                    seenDac || connected.equals("dac")
            );
        }

        pathCachePart2.put(new CacheKeyP2(current, seenFft, seenDac), tally);
        return tally;
    }

}
