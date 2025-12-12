package aoc2015.dec03;

import framework.AOCParent;

import java.util.HashSet;
import java.util.Set;

public class Dec03 extends AOCParent {

    @Override
    public void loadInput() {

    }

    @Override
    public void part1() {
        String input = InputLoader.loadInput();

        Set<Location> visited = new HashSet<>();
        Location currentLocation = new Location(0, 0);
        visited.add(currentLocation);

        for (Character character : input.toCharArray()) {
            currentLocation = currentLocation.move(character);
            visited.add(currentLocation);
        }

        System.out.println("Houses getting presents: " + visited.size());
    }

    @Override
    public void part2() {
        String input = InputLoader.loadInput();

        Set<Location> visited = new HashSet<>();
        Location roboLocation1 = new Location(0, 0);
        Location roboLocation2 = new Location(0, 0);
        visited.add(roboLocation1);

        for (int i = 0; i < input.length(); i++) {
            Character character = input.charAt(i);
            if (i % 2 == 1) {
                roboLocation1 = roboLocation1.move(character);
                visited.add(roboLocation1);
            } else {
                roboLocation2 = roboLocation2.move(character);
                visited.add(roboLocation2);
            }
        }

        System.out.println("Houses getting presents: " + visited.size());
    }
}
