package aoc2021.dec15;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Grid;

import java.util.*;

public class Dec15 extends AOCParent {

    private Grid<Integer> riskMapP1;
    private Grid<Integer> riskMapP2;

    @Override
    public void loadInput() {
        riskMapP1 = InputLoader.loadRiskMapP1();
        riskMapP2 = InputLoader.loadRiskMapP2();
    }

    @Override
    public void part1() {
        printSolution(shortestPath(riskMapP1));
    }

    @Override
    public void part2() {
        printSolution(shortestPath(riskMapP2));
    }

    private long shortestPath(Grid<Integer> grid) {
        Map<Coordinate, Long> lowestTotal = new HashMap<>();
        Coordinate start = new Coordinate(0, 0);
        Coordinate target = new Coordinate(grid.rows() - 1, grid.cols() - 1);
        Queue<PathTotal> pathQueue = new PriorityQueue<>();
        pathQueue.add(new PathTotal(start, 0L));
        PathTotal current = null;

        while (!pathQueue.isEmpty()) {
            current = pathQueue.poll();
            if (current.location().equals(target)) {
                break;
            }

            if (lowestTotal.containsKey(current.location()) && lowestTotal.get(current.location()) <= current.currentTotal()) {
                continue;
            }

            for (Coordinate next : current.location().orthogonal().stream().filter(grid::isInBounds).toList()) {
                pathQueue.add(new PathTotal(next, current.currentTotal() + grid.get(next)));
            }

            lowestTotal.put(current.location(), current.currentTotal());
        }

        return current.currentTotal();
    }
}
