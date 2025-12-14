package aoc2021.dec05;

import framework.utils.Coordinate;

import java.util.HashSet;
import java.util.Set;

public record Line(Coordinate left, Coordinate right) {

    public Set<Coordinate> overlapOrthogonal(Line other) {
        if (this.isHorizontal() && other.isHorizontal()) {
            if (!this.left.row().equals(other.left.row())) {
                // On different rows
                return Set.of();
            }

            if (this.minCol() > other.maxCol() || this.maxCol() < other.minCol()) {
                // no overlap
                return Set.of();
            }

            int overlapStart = Math.max(this.minCol(), other.minCol());
            int overlapEnd = Math.min(this.maxCol(), other.maxCol());

            Set<Coordinate> overlaps = new HashSet<>();
            for (int col = overlapStart; col <= overlapEnd; col++) {
                overlaps.add(new Coordinate(this.left.row(), col));
            }

            return overlaps;
        }

        if (this.isVertical() && other.isVertical()) {
            if (!this.left.col().equals(other.left.col())) {
                // On different cols
                return Set.of();
            }

            if (this.minRow() > other.maxRow() || this.maxRow() < other.minRow()) {
                // no overlap
                return Set.of();
            }

            int overlapStart = Math.max(this.minRow(), other.minRow());
            int overlapEnd = Math.min(this.maxRow(), other.maxRow());

            Set<Coordinate> overlaps = new HashSet<>();
            for (int row = overlapStart; row <= overlapEnd; row++) {
                overlaps.add(new Coordinate(row, this.left.col()));
            }

            return overlaps;
        }

        Line verticalLine = isVertical() ? this : other;
        Line horizontalLine = isHorizontal() ? this : other;

        if (verticalLine.left.col() < horizontalLine.minCol() || verticalLine.left.col() > horizontalLine.maxCol()) {
            return Set.of();
        }

        if (verticalLine.minRow() > horizontalLine.left.row() || verticalLine.maxRow() < horizontalLine.left.row()) {
            return Set.of();
        }

        return Set.of(new Coordinate(horizontalLine.left.row(), verticalLine.left.col()));
    }

    public Set<Coordinate> overlap(Line other) {
        if (this.isOrthogonal() && other.isOrthogonal()) {
            return this.overlapOrthogonal(other);
        }

        Set<Coordinate> locations = allLocations();
        locations.retainAll(other.allLocations());
        return locations;
    }

    public Set<Coordinate> allLocations() {
        Set<Coordinate> coordinates = new HashSet<>();
        if (isVertical()) {
            for (int row = minRow(); row <= maxRow(); row++) {
                coordinates.add(new Coordinate(row, minCol()));
            }
            return coordinates;
        }

        if (isHorizontal()) {
            for (int col = minCol(); col <= maxCol(); col++) {
                coordinates.add(new Coordinate(minRow(), col));
            }
            return coordinates;
        }

        Coordinate first = left.col().equals(minCol()) ? left : right;
        Coordinate second = left.col().equals(maxCol()) ? left : right;

        final boolean rising = second.row() > first.row();
        final int length = second.col() - first.col();

        for (int i = 0; i <= length; i++) {
            int newRow = rising ? first.row() + i : first.row() - i;
            int newCol = first.col() + i;
            coordinates.add(new Coordinate(newRow, newCol));
        }
        return coordinates;
    }

    public boolean isOrthogonal() {
        return isHorizontal() || isVertical();
    }

    public boolean isHorizontal() {
        return left.row().equals(right.row());
    }

    public boolean isVertical() {
        return left.col().equals(right.col());
    }

    public int minRow() {
        return Math.min(left.row(), right.row());
    }

    public int minCol() {
        return Math.min(left.col(), right.col());
    }

    public int maxRow() {
        return Math.max(left.row(), right.row());
    }

    public int maxCol() {
        return Math.max(left.col(), right.col());
    }

}
