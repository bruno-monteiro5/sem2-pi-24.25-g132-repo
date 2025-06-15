package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Position} class.
 *
 * These tests verify correct behavior of the constructor, getters, setters,
 * equals method, and string representation.
 */
public class PositionTest {

    /**
     * Tests the constructor and getter methods.
     *
     * Verifies that the position stores and returns the correct X and Y values.
     */
    @Test
    public void testConstructorAndGetters() {
        Position pos = new Position(10, 20);
        assertEquals(10, pos.getX());
        assertEquals(20, pos.getY());
    }

    /**
     * Tests the setter methods.
     *
     * Checks that the X and Y coordinates can be updated after creation.
     */
    @Test
    public void testSetters() {
        Position pos = new Position(0, 0);
        pos.setX(5);
        pos.setY(15);
        assertEquals(5, pos.getX());
        assertEquals(15, pos.getY());
    }

    /**
     * Tests the toString method.
     *
     * Confirms that the string output is formatted correctly and includes both coordinates.
     */
    @Test
    public void testToString() {
        Position pos = new Position(3, 7);
        String output = pos.toString();
        assertEquals("Position{x=3, y=7}", output);
    }

    /**
     * Tests the equals method.
     *
     * Checks that two positions are equal if they have the same coordinates,
     * and not equal otherwise or when compared to null or other types.
     */
    @Test
    public void testEquals() {
        Position p1 = new Position(4, 5);
        Position p2 = new Position(4, 5);
        Position p3 = new Position(5, 4);
        Position p4 = new Position(4, 6);

        assertEquals(p1, p2);              // same coordinates
        assertNotEquals(p1, p3);           // different x and y
        assertNotEquals(p1, p4);           // same x, different y
        assertNotEquals(p1, null);         // null check
        assertNotEquals(p1, "notPosition"); // different class
    }
}
