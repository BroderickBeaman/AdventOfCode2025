package aoc2021.dec04;

import framework.AOCParent;

import java.util.ArrayList;
import java.util.List;

public class Dec04 extends AOCParent {

    private List<Integer> draws;
    private List<BingoBoard> boards;

    @Override
    public void loadInput() {
        draws = InputLoader.loadDrawnNumbers();
        boards = InputLoader.loadBingoBoards();
    }

    @Override
    public void part1() {
        boolean foundWinner = false;
        long score = 0;
        for (Integer draw : draws) {
            for (BingoBoard board : boards) {
                if (board.markNum(draw)) {
                    foundWinner = true;
                    score = board.getScore();
                    break;
                }
            }
            if (foundWinner) {
                break;
            }
        }

        printSolution(score);
    }

    @Override
    public void part2() {
        long score = 0;
        List<BingoBoard> boardsToProcess = new ArrayList<>(boards);
        for (Integer draw : draws) {
            List<BingoBoard> nextToProcess = new ArrayList<>();
            for (BingoBoard board : boardsToProcess) {
                if (board.markNum(draw)) {
                    score = board.getScore();
                } else {
                    nextToProcess.add(board);
                }
            }
            if (nextToProcess.isEmpty()) {
                break;
            }
            boardsToProcess = new ArrayList<>(nextToProcess);
        }

        printSolution(score);
    }
}
