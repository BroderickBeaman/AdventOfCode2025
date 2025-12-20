package aoc2021.dec06;

import framework.AOCParent;
import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Dec06 extends AOCParent {

    private List<Integer> startingFish;


    @Override
    public void loadInput() {
        startingFish = Arrays.stream(InputLoaderParent.loadLines().getFirst().split(","))
                .map(Integer::parseInt).toList();
    }

    @Override
    public void part1() {
        printSolution(fishCount(80));
    }

    @Override
    public void part2() {
        printSolution(fishCount(256));
    }

    private long fishCount(int numCycles) {
        long[] fishCounts = new long[9];
        for (Integer fish : startingFish) {
            fishCounts[fish]++;
        }

        IntStream.range(0, numCycles).forEachOrdered(_ -> {
            long numNewFish = fishCounts[0];
            for (int i = 1; i < fishCounts.length; i++) {
                fishCounts[i-1] = fishCounts[i];
            }
            fishCounts[6] += numNewFish;
            fishCounts[8] = numNewFish;
        });

        return Arrays.stream(fishCounts).sum();
    }
}
