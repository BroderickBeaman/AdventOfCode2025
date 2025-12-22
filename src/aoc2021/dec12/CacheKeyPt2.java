package aoc2021.dec12;

import java.util.Set;

public record CacheKeyPt2(String node, Set<String> smallSeen, boolean doubleVisitedSmall) {
}
