package aoc2025.dec02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Range(long low, long high) {

    private static final Pattern repeatedDigitsPattern = Pattern.compile("^(.*?)\\1+$");

    public int isInRange(long test) {
        if (test < low) {
            return -1;
        }
        if (test > high) {
            return 1;
        }
        return 0;
    }

    public long computeInvalidIdsSumPart1() {
        String lowerString = String.valueOf(low);
        String highString = String.valueOf(high);

        // There can't be repeated halves if the length is odd and the range is all the same length
        if (lowerString.length() % 2 == 1 && lowerString.length() == highString.length()) {
            return 0;
        }

        String compareString = "";

        if (lowerString.length() % 2 == 1) {
            compareString = "1";
            for (int i = 0; i < lowerString.length() / 2; i++) {
                compareString += "0";
            }
        } else {
            compareString = lowerString.substring(0, lowerString.length() / 2);
        }

        long halfValue = Long.parseLong(compareString);
        compareString = compareString + compareString;
        long compareValue = Long.parseLong(compareString);
        long sum = 0;

        while (isInRange(compareValue) <= 0) {
            if (isInRange(compareValue) == 0) {
                sum += compareValue;
            }
            halfValue++;
            compareString = String.valueOf(halfValue) + String.valueOf(halfValue);
            compareValue = Long.parseLong(compareString);
        }

        return sum;
    }

    public long computeInvalidIdsSumPart2 () {
        long compareValue = low;
        long sum = 0;
        while (compareValue <= high) {
            String compareString = String.valueOf(compareValue);
            Matcher matcher = repeatedDigitsPattern.matcher(compareString);
            if (matcher.find()) {
                sum += compareValue;
            }
            compareValue++;
        }
        return sum;
    }
}
