package aoc2021.dec12;

import framework.AOCParent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dec12 extends AOCParent {

    private Map<String, Set<String>> caveMap;
    private Map<CacheKeyPt1, Long> cachePt1;
    private Map<CacheKeyPt2, Long> cachePt2;

    @Override
    public void loadInput() {
        caveMap = InputLoader.loadCaveMap();
    }

    @Override
    public void part1() {
        cachePt1 = new HashMap<>();
        Set<String> seen = new HashSet<>();
        seen.add("start");
        printSolution(numPathsAtNodePt1(new CacheKeyPt1("start", seen)));
    }

    @Override
    public void part2() {
        cachePt2 = new HashMap<>();
        Set<String> seen = new HashSet<>();
        seen.add("start");
        printSolution(numPathsAtNodePt2(new CacheKeyPt2("start", seen, false)));
    }

    private long numPathsAtNodePt1(CacheKeyPt1 cacheKey) {
        if (cacheKey.node().equals("end")) {
            return 1L;
        }

        if (cachePt1.containsKey(cacheKey)) {
            return cachePt1.get(cacheKey);
        }

        long total = 0;
        for (String connected : caveMap.get(cacheKey.node())) {
            Set<String> seen = new HashSet<>(cacheKey.smallSeen());
            if (connected.toLowerCase().equals(connected)) {
                if (cacheKey.smallSeen().contains(connected)) {
                    continue; // Already seen in this timeline
                }
                seen.add(connected);
            }
            total += numPathsAtNodePt1(new CacheKeyPt1(connected, seen));
        }

        cachePt1.put(cacheKey, total);
        return total;
    }

    private long numPathsAtNodePt2(CacheKeyPt2 cacheKey) {
        if (cachePt2.containsKey(cacheKey)) {
            return cachePt2.get(cacheKey);
        }

        long total = 0;
        for (String connected : caveMap.get(cacheKey.node())) {
            if (connected.equals("end")) {
                total += 1L;
                continue;
            }

            if (connected.equals("start")) {
                continue;
            }

            Set<String> smallSeen = new HashSet<>(cacheKey.smallSeen());

            if (connected.toLowerCase().equals(connected)) {
                if (cacheKey.smallSeen().contains(connected)) {
                    if (!cacheKey.doubleVisitedSmall()) {
                        total += numPathsAtNodePt2(new CacheKeyPt2(connected, smallSeen, true));
                    }
                } else {
                    smallSeen.add(connected);
                    total += numPathsAtNodePt2(new CacheKeyPt2(connected, smallSeen, cacheKey.doubleVisitedSmall()));
                }
            } else {
                total += numPathsAtNodePt2(new CacheKeyPt2(connected, smallSeen, cacheKey.doubleVisitedSmall()));
            }
        }

        cachePt2.put(cacheKey, total);
        return total;
    }

}
