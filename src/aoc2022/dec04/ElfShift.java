package aoc2022.dec04;

public class ElfShift {

    private final int min1;
    private final int max1;
    private final int min2;
    private final int max2;

    public ElfShift(int min1, int max1, int min2, int max2) {
        this.min1 = min1;
        this.max1 = max1;
        this.min2 = min2;
        this.max2 = max2;
    }

    public boolean containsCompleteOverlap() {
        int left = min1 - min2;
        int right = max1 - max2;

        // If either side is the same, there must be complete overlap
        if (left == 0 || right == 0) {
            return true;
        }

        // if both sides have opposite parity, we should have complete overlap
        // (A negative number divided by a positive one, or vice versa, will result in a negative number)
        return ((double) left / (double) right) < 0;
    }

    public boolean containsPartialOverlap() {
        return !(max1 < min2 || max2 < min1);
    }
}
