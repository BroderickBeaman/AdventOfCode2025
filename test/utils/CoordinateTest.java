package utils;

import framework.utils.Coordinate;
import framework.utils.Direction;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void rowAndCol() {
        Coordinate coordinate = new Coordinate(5, 6);
        assertEquals(5, coordinate.row());
        assertEquals(6, coordinate.col());
    }

    @Test
    void orthogonalDistance() {
        Coordinate first = new Coordinate(2, 3);
        Coordinate second = new Coordinate(6, 10);
        assertEquals(new Coordinate(4, 7), first.orthogonalDistance(second));
    }

    @Test
    void orthogonalDistanceValue() {
        Coordinate first = new Coordinate(2, 3);
        Coordinate second = new Coordinate(6, 10);
        assertEquals(11, first.orthogonalDistanceValue(second));
    }

    @Test
    void addCoordinate() {
        Coordinate first = new Coordinate(7, 3);
        Coordinate second = new Coordinate(1, 2);
        assertEquals(new Coordinate(8, 5), first.addCoordinate(second));
    }

    @Test
    void addDirection() {
        Coordinate origin = new Coordinate(4, 5);
        assertEquals(new Coordinate(3, 5), origin.addDirection(Direction.N));
        assertEquals(new Coordinate(5, 5), origin.addDirection(Direction.S));
        assertEquals(new Coordinate(4, 6), origin.addDirection(Direction.E));
        assertEquals(new Coordinate(4, 4), origin.addDirection(Direction.W));
    }

    @Test
    void orthogonal() {
        Coordinate origin = new Coordinate(4, 5);
        Set<Coordinate> expected = Set.of(
                new Coordinate(3, 5),
                new Coordinate(5, 5),
                new Coordinate(4, 6),
                new Coordinate(4, 4)
        );
        assertEquals(expected, origin.orthogonal());
    }

    @Test
    void addRow() {
        Coordinate origin = new Coordinate(4, 7);
        assertEquals(new Coordinate(9, 7), origin.addRow(5));
    }

    @Test
    void addCol() {
        Coordinate origin = new Coordinate(3, 1);
        assertEquals(new Coordinate(3, 5), origin.addCol(4));
    }
}