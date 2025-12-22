package framework.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class to assist in grid based problems
 * @param <T> The type of the values to insert into the grid
 */
public class Grid<T> {

    private T[][] grid;

    public Grid(T[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("grid must not be null");
        }
        this.grid = grid;
    }

    @SuppressWarnings("unchecked")
    public Grid(Class<? extends T> clazz, Integer rows, Integer cols) {
        if (rows == null || cols == null) {
            throw new IllegalArgumentException("rows and cols must not be null");
        }

        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("rows and cols must be at least 1");
        }

        this.grid = (T[][]) Array.newInstance(clazz, rows, cols);
    }

    @SuppressWarnings("unchecked")
    public Grid(Class<? extends T> clazz, Grid<T> from) {
        this.grid = (T[][]) Array.newInstance(clazz, from.rows(), from.cols());
        for (int row = 0; row < from.rows(); row++) {
            for (int col = 0; col < from.cols(); col++) {
                grid[row][col] = from.get(row, col);
            }
        }
    }

    /**
     * Returns the number of rows in the grid
     * @return The number of rows in the grid
     */
    public Integer rows() {
        return grid.length;
    }

    /**
     * Returns the number of columns in the grid
     * @return The number of columns in the grid
     */
    public Integer cols() {
        return grid[0].length;
    }

    /**
     * Returns the size of the grid (rows * cols)
     * @return The size of the grid
     */
    public Integer size() {
        return cols() * rows();
    }

    /**
     * If required, return the underlying 2-dimensional array
     * @return The 2-dimensional array this class wraps
     */
    public T[][] grid() {
        return grid;
    }

    /**
     * Place some default value in every position in the grid
     * @param defaultValue The value to insert
     */
    public void initWithDefault(T defaultValue) {
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                grid[row][col] = defaultValue;
            }
        }
    }

    /**
     * Determines if the provided coordinate resides within the bounds of the grid
     * @param coordinate The coordinate to validate
     * @return true if the coordinate resides within the bounds of the grid
     */
    public Boolean isInBounds(Coordinate coordinate) {
        return isInBounds(coordinate.row(), coordinate.col());
    }

    /**
     * Determines if the provided coordinate resides within the bounds of the grid
     * @param row The row portion of the coordinate
     * @param col The column portion of the coordinate
     * @return true if the coordinate resides within the bounds of the grid
     */
    public Boolean isInBounds(Integer row, Integer col) {
        return row >= 0 && row < rows() && col >= 0 && col < cols();
    }

    /**
     * Returns the object stored at the specified location in the grid
     * @param coordinate The location to look up
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     * @return The object store at the specified location in the grid
     */
    public T get(Coordinate coordinate) throws IllegalArgumentException {
        return get(coordinate.row(), coordinate.col());
    }

    /**
     * Returns the object stored at the specified location in the grid
     * @param row The row portion of the coordinate
     * @param col The column portion of the coordinate
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     * @return The object store at the specified location in the grid
     */
    public T get(Integer row, Integer col) throws IllegalArgumentException {
        if (isInBounds(row, col)) {
            return grid[row][col];
        }
        throw new IndexOutOfBoundsException("(" + row + "," + col + ") does not reside within the grid");
    }

    /**
     * Inserts a value into the grid at a specified location
     * @param value The value to insert
     * @param coordinate The location to insert the value
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     */
    public void set(T value, Coordinate coordinate) {
        set(value, coordinate.row(), coordinate.col());
    }

    /**
     * Inserts a value into the grid at a specified location
     * @param value The value to insert
     * @param row The row to insert the value into
     * @param col The column to insert the value into
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     */
    public void set(T value, Integer row, Integer col) {
        if (isInBounds(row, col)) {
            grid[row][col] = value;
            return;
        }
        throw new IndexOutOfBoundsException("(" + row + "," + col + ") does not reside within the grid");
    }

    /**
     * Finds the first location of a specified value in the grid
     * @param value The value to search for
     * @return The first location where that value is located
     */
    public Coordinate findValue(T value) {
        return findValues(value).getFirst();
    }

    /**
     * Finds all locations where a specified value is located
     * @param value The value to search for
     * @return All locations where that value is located
     */
    public List<Coordinate> findValues(T value) {
        List<Coordinate> locations = new ArrayList<>();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                if (Objects.equals(get(row, col), value)) {
                    locations.add(new Coordinate(row, col));
                }
            }
        }
        return locations;
    }

    /**
     * Finds all locations where a specified criteria is matched
     * @param criteria The criteria to evaluate
     * @return All locations that meet the criteria
     */
    public List<Coordinate> findAllMatching(Predicate<Coordinate> criteria) {
        List<Coordinate> locations = new ArrayList<>();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                Coordinate at = new Coordinate(row, col);
                if (criteria.test(at)) {
                    locations.add(at);
                }
            }
        }
        return locations;
    }

    /**
     * Finds all locations where a specified criteria (applied to the value at the location) is matched
     * @param criteria The criteria to evaluate
     * @return All locations that meet the criteria
     */
    public List<Coordinate> findAllMatchingValue(Predicate<T> criteria) {
        List<Coordinate> locations = new ArrayList<>();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                Coordinate at = new Coordinate(row, col);
                if (criteria.test(get(at))) {
                    locations.add(at);
                }
            }
        }
        return locations;
    }

    /**
     * Finds any location where a specified criteria is matched
     * @param criteria The criteria to evaluate
     * @return The location (if any) matching the criteria
     */
    public Optional<Coordinate> findAnyMatching(Predicate<Coordinate> criteria) {
        return Optional.ofNullable(findAllMatching(criteria).getFirst());
    }

    /**
     * Finds any location where a specified criteria (applied to the value at the location) is matched
     * @param criteria The criteria to evaluate
     * @return The location (if any) matching the criteria
     */
    public Optional<Coordinate> findAnyMatchingValue(Predicate<T> criteria) {
        return Optional.ofNullable(findAllMatchingValue(criteria).getFirst());
    }

    /**
     * Applies the specified operation on all elements of the grid
     * @param function The operation to apply
     */
    public void applyToAll(Function<T, T> function) {
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                set(function.apply(get(row, col)), row, col);
            }
        }
    }

    /**
     * Applies the specified operation on an element of the grid at the specified location
     * @param function The operation to apply
     * @param location The location of the element to apply the operation on
     */
    public void applyToLocation(Function<T, T> function, Coordinate location) {
        set(function.apply(get(location)), location);
    }

    /**
     * Applies the specified operation on an element of the grid at the specified location
     * @param function The operation to apply
     * @param row The row of the element to apply the operation on
     * @param col The col of the element to apply the operation on
     */
    public void applyToLocation(Function<T, T> function, Integer row, Integer col) {
        applyToLocation(function, new Coordinate(row, col));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grid<?> grid1 = (Grid<?>) o;
        return Objects.deepEquals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                stringBuilder.append(get(row, col));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
