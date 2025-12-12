package aoc2022.dec04;

import java.util.List;

public class Dec04Part1 {

    public static void main(String[] args) {
        List<ElfShift> elfShifts = ElfShiftLoader.loadElfShifts();
        long count = elfShifts.stream().filter(ElfShift::containsCompleteOverlap).count();
        System.out.println("Number of overlapping shifts: " + count);
    }
}
