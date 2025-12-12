package aoc2015.dec02;

import framework.AOCParent;

import java.util.List;

public class Dec02 extends AOCParent {

    @Override
    public void loadInput() {

    }

    @Override
    public void part1() {
        List<Box> boxes = InputLoader.loadBoxes();

        long total = boxes.stream().mapToLong(Box::surfaceArea).sum();

        System.out.println("Sum of wrapping paper: " + total);
    }

    @Override
    public void part2() {
        List<Box> boxes = InputLoader.loadBoxes();

        long total = boxes.stream().mapToLong(Box::ribbon).sum();

        System.out.println("Sum of ribbon paper: " + total);
    }
}
