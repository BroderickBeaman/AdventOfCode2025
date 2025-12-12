package aoc2022.dec02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FightsLoader {

    public static List<Fight> loadFights() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/Dec02Input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream()
            .map(line -> {
                RPSElement other = RPSElement.fromString(line.substring(0, 1));
                RPSElement me = other.fromStrategyString(line.substring(2, 3));

                return new Fight(me, other);
            }).collect(Collectors.toList());
    }
}
