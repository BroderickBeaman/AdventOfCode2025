package aoc2022.dec03;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dec03 {
    public static void main(String[] args) {
        List<String> compartments = CompartmentLoader.loadCompartments();
        int priorityScore = compartments.stream()
                .map(Dec03::getCommonChar)
                .map(Dec03::convertCharToInt)
                .reduce(0, Integer::sum);
        System.out.println("Priority Score: " + priorityScore);
    }

    private static Integer convertCharToInt(char in) {
        return in > 96 ? in - 96 : in - 64 + 26;
    }

    private static Character getCommonChar(String in) {
        String first = in.substring(0, in.length()/2);
        String last = in.substring(in.length()/2);
        Set<Character> firstSet = first.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> lastSet = last.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        firstSet.retainAll(lastSet);
        return firstSet.stream().findAny().get();
    }
}