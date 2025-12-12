package aoc2025.dec08;

import framework.AOCParent;

import java.util.*;

public class Dec08 extends AOCParent {

    private static final int NUM_PAIRS = 1000;

    private List<Point3D> points;
    private List<Wire> wires;

    @Override
    public void loadInput() {
        points = InputLoader.loadPoints();
    }

    @Override
    public void part1() {
        wires = new ArrayList<>();
        // construct all possible wires
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                wires.add(new Wire(points.get(i), points.get(j)));
            }
        }
        Collections.sort(wires);

        Set<Set<Point3D>> circuits = new HashSet<>();

        for (int i = 0; i < NUM_PAIRS; i++) {
            Wire wire = wires.get(i);
            Set<Set<Point3D>> matchingCircuits = new HashSet<>();
            for (Set<Point3D> circuit : circuits) {
                if (circuit.contains(wire.left()) || circuit.contains(wire.right())) {
                    matchingCircuits.add(circuit);
                }
            }
            if (matchingCircuits.isEmpty()) {
                Set<Point3D> newCircuit = new HashSet<>();
                newCircuit.add(wire.left());
                newCircuit.add(wire.right());
                circuits.add(newCircuit);
            } else {
                Set<Point3D> newCircuit = new HashSet<>();
                for (Set<Point3D> matchingCircuit : matchingCircuits) {
                    circuits.remove(matchingCircuit);
                    newCircuit.addAll(matchingCircuit);
                }
                newCircuit.add(wire.left());
                newCircuit.add(wire.right());
                circuits.add(newCircuit);
            }
        }

        List<Set<Point3D>> circuitList = new ArrayList<>(circuits);
        circuitList.sort(Comparator.comparingInt(Set::size));
        Collections.reverse(circuitList);
        printSolution(circuitList.get(0).size() * circuitList.get(1).size() * circuitList.get(2).size());
    }

    @Override
    public void part2() {
        wires = new ArrayList<>();
        // construct all possible wires
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                wires.add(new Wire(points.get(i), points.get(j)));
            }
        }
        Collections.sort(wires);

        Set<Point3D> connectedPoints = new HashSet<>();
        int wireIndex = 0;
        Wire wire = null;
        while (connectedPoints.size() < points.size()) {
            wire = wires.get(wireIndex);
            connectedPoints.add(wire.left());
            connectedPoints.add(wire.right());
            wireIndex++;
        }

        printSolution(wire.left().x() * wire.right().x());
    }
}
