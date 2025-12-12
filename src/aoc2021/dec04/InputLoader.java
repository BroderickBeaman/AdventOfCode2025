package aoc2021.dec04;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Integer> loadDrawnNumbers() {
        String[] parts = loadLines("bingoDraws.txt").getFirst().split(",");
        return Arrays.stream(parts).map(Integer::parseInt).toList();
    }

    public static List<BingoBoard> loadBingoBoards() {
        List<String> lines = loadLines("boards.txt");
        List<BingoBoard> boards = new ArrayList<>();
        Integer[][] grid = new Integer[5][5];
        int lineCounter = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isBlank()) {
                lineCounter = 0;
                boards.add(new BingoBoard(grid));
                grid = new Integer[5][5];
                continue;
            }

            String[] lineParts = line.split("\\s+");
            for (int j = 0; j < lineParts.length; j++) {
                Integer num = Integer.parseInt(lineParts[j]);
                grid[lineCounter][j] = num;
            }
            lineCounter++;
        }
        boards.add(new BingoBoard(grid));
        return boards;
    }
}
