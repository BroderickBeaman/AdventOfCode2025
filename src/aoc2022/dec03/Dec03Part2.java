package aoc2022.dec03;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dec03Part2 {

    public static void main(String[] args) {
        List<String> compartments = CompartmentLoader.loadCompartments();
        List<String> tempList = new ArrayList<>();
        int count = 0;
        int score = 0;
        for (String compartment: compartments) {
            tempList.add(compartment);
            count++;
            if (count == 3) {
                Set<Character> firstSet = tempList.get(0).chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                Set<Character> secondSet = tempList.get(1).chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                Set<Character> thirdSet = tempList.get(2).chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                firstSet.retainAll(secondSet);
                firstSet.retainAll(thirdSet);
                score += convertCharToInt(firstSet.stream().findAny().get());
                count = 0;
                tempList = new ArrayList<>();
            }
        }

        System.out.println("Priority Score: " + score);
    }

    private static Integer convertCharToInt(char in) {
        return in > 96 ? in - 96 : in - 64 + 26;
    }

}
