package aoc2022.dec06;

import java.util.LinkedList;

public class LimitedSizeLinkedList<E> extends LinkedList<E> {

    private int sizeLimit;

    public LimitedSizeLinkedList(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        while (added && size() > sizeLimit) {
            super.poll();
        }
        return added;
    }

    public boolean isAtLimit() {
        return size() == sizeLimit;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }
}
