package framework.utils;

import java.util.Objects;
import java.util.Set;

/**
 * Class representing the position in a 2-dimensional grid
 *
 * @param row The row component of the coordinate
 * @param col The column component of the coordinate
 */
public record Coordinate(Integer row, Integer col) {

    /**
     * Generate a new coordinate representing the distance between this coordinate and another
     *
     * @param other The coordinate to compare to
     * @return The coordinate representing the distance between the two coordinates
     */
    public Coordinate orthogonalDistance(Coordinate other) {
        return new Coordinate(other.row - this.row, other.col - this.col);
    }

    /**
     * Returns the distance between two coordinates. Distance is represented by the number of vertical and horizontal
     * steps to reach the other Coordinate
     *
     * @param other the other coordinate
     * @return The distance between the two coordinates
     */
    public Integer orthogonalDistanceValue(Coordinate other) {
        Coordinate distance = this.orthogonalDistance(other);
        return Math.abs(distance.row) + Math.abs(distance.col);
    }

    /**
     * Generate a new coordinate by taking this coordinate and adding another
     *
     * @param other The other coordinate to add
     * @return The sum of both coordinates
     */
    public Coordinate addCoordinate(Coordinate other) {
        return new Coordinate(this.row + other.row, this.col + other.col);
    }

    /**
     * Generate a new coordinate by moving a single space in the specified direction
     *
     * @param direction The direction to move
     * @return The new coordinate
     */
    public Coordinate addDirection(Direction direction) {
        return addCoordinate(direction.toCoordinate());
    }

    /**
     * Returns a set of coordinates orthogonally adjacent to this coordinate
     *
     * @return The set of orthogonally adjacent coordinates
     */
    public Set<Coordinate> orthogonal() {
        return Set.of(
                addDirection(Direction.N),
                addDirection(Direction.E),
                addDirection(Direction.S),
                addDirection(Direction.W)
        );
    }

    /**
     * Return a new Coordinate with the row value incremented by the specified amount
     *
     * @param row The amount to increment
     * @return The new Coordinate
     */
    public Coordinate addRow(Integer row) {
        return new Coordinate(row() + row, col());
    }

    /**
     * Return a new Coordinate with the col value incremented by the specified amount
     *
     * @param col The amount to increment
     * @return The new Coordinate
     */
    public Coordinate addCol(Integer col) {
        return new Coordinate(row(), col() + col);
    }

    @Override
    public String toString() {
        return "Row: " + row + " Col: " + col;
    }
}
