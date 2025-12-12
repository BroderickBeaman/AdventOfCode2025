package aoc2015.dec01;

import framework.AOCParent;

public class Dec01 extends AOCParent {

    @Override
    public void loadInput() {

    }

    @Override
    public void part1() {
        String input = InputLoader.loadInput();

        long floor = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                floor++;
            } else {
                floor--;
            }
        }

        System.out.println("Floor number: " + floor);
    }

    @Override
    public void part2() {
        String input = InputLoader.loadInput();

        long floor = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                floor++;
            } else {
                floor--;
            }

            if (floor < 0) {
                System.out.println("Index of basement: " + (i + 1));
                break;
            }
        }
    }
}
