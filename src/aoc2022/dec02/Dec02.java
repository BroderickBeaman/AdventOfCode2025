package aoc2022.dec02;

import java.util.List;

public class Dec02 {
    public static void main(String[] args) {
        List<Fight> fights = FightsLoader.loadFights();
        int fightScore = fights.stream().mapToInt(Fight::getScore).sum();
        System.out.println("Total fight score: " + fightScore);
    }
}