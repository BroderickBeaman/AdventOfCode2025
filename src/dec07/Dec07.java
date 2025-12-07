package dec07;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Grid;

import java.util.*;

public class Dec07 extends AOCParent {

    private Grid<Manifold> manifold;
    private Map<Coordinate, Long> timelineCache;

    @Override
    public void loadInput() {
        manifold = InputLoader.loadManifold();
    }

    @Override
    public void part1() {
        Coordinate start = manifold.findValue(Manifold.START);

        Set<Coordinate> splittersSeen = new HashSet<>();

        Queue<Coordinate> locationQueue = new LinkedList<>();
        locationQueue.add(start);

        while (!locationQueue.isEmpty()) {
            Coordinate current = locationQueue.poll();
            if (!manifold.isInBounds(current) || splittersSeen.contains(current)) {
                continue;
            }
            Manifold atLocation = manifold.get(current);
            if (atLocation.equals(Manifold.EMPTY)) {
                locationQueue.add(current.addRow(1));
            } else {
                splittersSeen.add(current);
                locationQueue.add(current.addCol(1));
                locationQueue.add(current.addCol(-1));
            }
        }
        printSolution(splittersSeen.size());
    }

    @Override
    public void part2() {
        Coordinate start = manifold.findValue(Manifold.START);
        timelineCache = new HashMap<>();
        long tally = timelinesAt(start);
        printSolution(tally);
    }

    private Long timelinesAt(Coordinate location) {
        if (timelineCache.containsKey(location)) {
            return timelineCache.get(location);
        }

        if (!manifold.isInBounds(location)) {
            return 1L;
        }

        Long tally = 0L;
        if (manifold.get(location).equals(Manifold.SPLITTER)) {
            tally += timelinesAt(location.addCol(1));
            tally += timelinesAt(location.addCol(-1));
        } else {
            tally += timelinesAt(location.addRow(1));
        }

        timelineCache.put(location, tally);
        return tally;
    }
}