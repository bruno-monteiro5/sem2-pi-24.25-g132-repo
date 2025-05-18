package pt.ipp.isep.dei.domain;

/**
 * Represents a position in a two-dimensional space with X and Y coordinates.
 *
 * <p>The {@code Position} class allows for creating, modifying, and comparing
 * positions in a 2D space. It includes methods for retrieving and updating
 * the X and Y coordinates, as well as overridden methods for {@code toString}
 * and {@code equals} for better representation and comparison.</p>
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructs a {@code Position} instance with the specified X and Y coordinates.
     *
     * @param x The X coordinate of the position.
     * @param y The Y coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the X coordinate of the position.
     *
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Updates the X coordinate of the position.
     *
     * @param x The new X coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the Y coordinate of the position.
     *
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Updates the Y coordinate of the position.
     *
     * @param y The new Y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Provides a string representation of the position in the format "Position{x=X, y=Y}".
     *
     * @return A string representation of the position.
     */
    @Override
    public String toString() {
        return "Position{x=" + x + ", y=" + y + "}";
    }

    /**
     * Compares this position with another object to determine equality.
     *
     * <p>The positions are considered equal if the other object is also
     * an instance of {@code Position} and their X and Y coordinates match.</p>
     *
     * @param o The object to compare with.
     * @return {@code true} if the positions are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }





}
