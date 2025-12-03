package dec03;

import framework.AOCParent;

import java.util.*;

public class Dec03 extends AOCParent {

    private List<String> banks;

    @Override
    public void loadInput() {
        banks = InputLoader.loadLines();
    }

    @Override
    public void part1() {
        long sum = banks.stream().mapToLong(bank -> {
            int largestPos = 0;
            for (int i = 9; i > 0; i--) {
                int pos = bank.indexOf(String.valueOf(i));
                if (pos != -1 && pos != bank.length() - 1) {
                    largestPos = pos;
                    break;
                }
            }

            int secondPos = 0;
            for (int i = 9; i > 0; i--) {
                int pos = bank.lastIndexOf(String.valueOf(i));
                if (pos > largestPos) {
                    secondPos = pos;
                    break;
                }
            }

            String resultString = String.valueOf(bank.charAt(largestPos)) + bank.charAt(secondPos);
            return Long.parseLong(resultString);
        }).sum();

        printSolution(sum);
    }

    @Override
    public void part2() {
//        long sum = banks.stream().mapToLong(bank -> {
//            String optimized = bank;
//            List<Integer> digits = new ArrayList<>();
//
//            List<Integer> indexesToRemove = new ArrayList<>();
//
//            int digitOffset = 0;
//            for (int i = 0; i < digits.size() - 1; i++) {
//                if (digits.get(i) > digits.get(i + 1)) {
//                    indexesToRemove.add(i - digitOffset);
//                    digitOffset++;
//                }
//            }
//
//            digits.re
//        }).sum();
//
//        printSolution(sum);

    }

    private List<Integer> allIndexesOfDigit(String bank, int digit) {
        String digitString = String.valueOf(digit);
        List<Integer> indeces = new ArrayList<>();
        int index = bank.indexOf(digitString);
        while (index >= 0) {
            indeces.add(index);
            index = bank.indexOf(digitString, index + 1);
        }
        return indeces;
    }
}
