package aoc2025.dec03;

import framework.AOCParent;

import java.util.*;

public class Dec03 extends AOCParent {

    private List<List<Integer>> banks;

    @Override
    public void loadInput() {
        banks = InputLoader.loadBanks();
    }

    @Override
    public void part1() {
        long sum = banks.stream().mapToLong(bank -> findLargest("", bank, 1)).sum();
        printSolution(sum);
    }

    @Override
    public void part2() {
        long sum = banks.stream().mapToLong(bank -> findLargest("", bank, 11)).sum();
        printSolution(sum);
    }

    private long findLargest(String acc, List<Integer> digits, int target) {
        if (target == -1) {
            return Long.parseLong(acc);
        }

        int largest = 0;
        int index = 0;

        for (int i = 0; i < digits.size() - target; i++) {
            int test = digits.get(i);
            if (test > largest) {
                largest = test;
                index = i;
            }
        }

        return findLargest(acc + largest, digits.subList(index + 1, digits.size()), target - 1);
    }
}
