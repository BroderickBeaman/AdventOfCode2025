package aoc2025.dec06;

import framework.AOCParent;
import framework.utils.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dec06 extends AOCParent {

    Grid<String> homework;
    Grid<Character> homeworkPt2;

    @Override
    public void loadInput() {
        homework = InputLoader.loadHomework();
        homeworkPt2 = InputLoader.loadHomeworkPt2();
    }

    @Override
    public void part1() {
        long totalSum = 0;
        for (int col = 0; col < homework.cols(); col++) {
            long colTotal = Long.parseLong(homework.get(0, col));
            boolean multiply = homework.get(homework.rows() - 1, col).equals("*");
            for (int row = 1; row < homework.rows() - 1; row++) {
                long operand = Long.parseLong(homework.get(row, col));
                colTotal = multiply ? colTotal * operand : colTotal + operand;
            }
            totalSum += colTotal;
        }

        printSolution(totalSum);
    }

    @Override
    public void part2() {
        long totalSum = 0;
        List<Long> operands = new ArrayList<>();
        for (int col = homeworkPt2.cols() - 1; col >= 0; col--) {
            String operandString = "";
            for (int row = 0; row < homeworkPt2.rows() - 1; row ++) {
                operandString += homeworkPt2.get(row, col);
            }

            if (operandString.isBlank()) {
                operands = new ArrayList<>();
                continue;
            }

            operands.add(Long.parseLong(operandString.trim()));

            Optional<Operator> operator = Operator.fromChar(homeworkPt2.get(homeworkPt2.rows() - 1, col));
            if (operator.isPresent()) {
                totalSum += operands.subList(1, operands.size()).stream()
                        .reduce(operands.getFirst(), operator.get().operation());
            }
        }
        printSolution(totalSum);
    }
}































