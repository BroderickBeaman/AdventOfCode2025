package aoc2021.dec04;

import framework.utils.Coordinate;
import framework.utils.Grid;

import java.util.List;

public class BingoBoard extends Grid<Integer> {

    private boolean[][] marked;
    private boolean hasWon = false;
    private long score = 0;

    public BingoBoard(Integer[][] grid) {
        super(grid);
        marked = new boolean[grid.length][grid[0].length];
    }


    public boolean markNum(Integer num) {
        if (hasWon) {
            return true;
        }
        List<Coordinate> locationsOfNum = findValues(num);
        for (Coordinate location : locationsOfNum) {
            marked[location.row()][location.col()] = true;
        }
        hasWon = hasWon();
        if (hasWon) {
            score = sumOfUnmarked() * num;
        }
        return hasWon;
    }

    public long getScore() {
        return score;
    }

    private boolean hasWon() {
        //check rows
        for (int row = 0; row < rows(); row++) {
            boolean check = true;
            for (int col = 0; col < cols(); col++) {
                check = check && marked[row][col];
            }
            if (check) {
                return true;
            }
        }

        //check cols
        for (int col = 0; col < cols(); col++) {
            boolean check = true;
            for (int row = 0; row < rows(); row++) {
                check = check && marked[row][col];
            }
            if (check) {
                return true;
            }
        }
        return false;
    }

    private long sumOfUnmarked() {
        long sum = 0;
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                if (!marked[row][col]) {
                    sum += get(row, col);
                }
            }
        }
        return sum;
    }

}
