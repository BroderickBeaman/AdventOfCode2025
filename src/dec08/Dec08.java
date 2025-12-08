package dec08;

import framework.AOCParent;

import java.util.*;

public class Dec08 extends AOCParent {

    private static final int NUM_PAIRS = 10;

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

        List<Set<Point3D>> circuits = new ArrayList<>();
        int connections = 0;
        int counter = 0;
        while (connections < NUM_PAIRS) {
            Wire wire = wires.get(counter);
            boolean foundMatch = false;
            for (Set<Point3D> circuit : circuits) {
                if (circuit.contains(wire.left()) && circuit.contains(wire.right())) {
                    foundMatch = true;
                    break;
                }
                if (circuit.contains(wire.left()) || circuit.contains(wire.right())) {
                    circuit.add(wire.left());
                    circuit.add(wire.right());
                    connections++;
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                Set<Point3D> newCircuit = new HashSet<>();
                newCircuit.add(wire.left());
                newCircuit.add(wire.right());
                circuits.add(newCircuit);
                connections++;
            }
            counter++;
        }

        circuits.sort(Comparator.comparingInt(Set::size));
        Collections.reverse(circuits);
        printSolution(circuits.get(0).size() * circuits.get(1).size() * circuits.get(2).size());
    }

    @Override
    public void part2() {

    }
}
