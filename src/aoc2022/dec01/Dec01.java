package aoc2022.dec01;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dec01 {
    public static void main(String[] args) {

        List<Elf> elves = ElfLoader.loadElves();

        PriorityQueue<Elf> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (Elf elf : elves) {
            heap.add(elf);
        }
        System.out.println("Top Three Calories Totaled");

        int calorieTotal = 0;
        for (int i = 0; i<3; i++) {
            calorieTotal += heap.poll().sumFood();
        }

        System.out.println(calorieTotal);
    }
}