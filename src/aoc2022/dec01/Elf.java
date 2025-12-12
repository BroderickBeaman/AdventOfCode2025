package aoc2022.dec01;

import java.util.List;

public class Elf implements Comparable<Elf> {

    private List<Integer> inventory;

    public Elf(List<Integer> inventory) {
        this.inventory = inventory;
    }

    public List<Integer> getInventory() {
        return inventory;
    }

    public void setInventory(List<Integer> inventory) {
        this.inventory = inventory;
    }

    public Integer sumFood() {
        return inventory.stream().reduce(0, Integer::sum);
    }

    @Override
    public int compareTo(Elf o) {
        return this.sumFood().compareTo(o.sumFood());
    }
}
