package aoc2022.dec01;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ElfLoader {

    public static List<Elf> loadElves() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/Dec01Input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Elf> elves = new ArrayList<>();
        List<Integer> elfInv = new ArrayList<>();
        for (String s : allLines) {
            if (isInteger(s)) {
                elfInv.add(Integer.parseInt(s));
            } else {
                elves.add(new Elf(elfInv));
                elfInv = new ArrayList<>();
            }
        }

        return elves;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
