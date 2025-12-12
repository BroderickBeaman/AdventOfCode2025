package aoc2022.dec04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ElfShiftLoader {

    public static List<ElfShift> loadElfShifts() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/Dec04Input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(line -> {
            String[] tokens = line.split("-");
            int min1 = Integer.parseInt(tokens[0]);
            int max2 = Integer.parseInt(tokens[2]);

            tokens = tokens[1].split(",");
            int max1 = Integer.parseInt(tokens[0]);
            int min2 = Integer.parseInt(tokens[1]);
            return new ElfShift(min1, max1, min2, max2);
        }).collect(Collectors.toList());
    }
}
