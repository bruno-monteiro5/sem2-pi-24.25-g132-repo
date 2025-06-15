package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HouseBlock} class.
 *
 * This class verifies that house blocks can be created, updated, and represented correctly,
 * and that null positions are properly handled with exceptions.
 */
public class HouseBlockTest {

    /**
     * Tests creation of a valid HouseBlock.
     *
     * Ensures that the position is stored correctly.
     */
    @Test
    public void testConstructorAndGetPosition() {
        Position pos = new Position(5, 5);
        HouseBlock block = new HouseBlock(pos);

        assertEquals(pos, block.getPosition());
    }

    /**
     * Tests that creating a HouseBlock with a null position throws an exception.
     */
    @Test
    public void testConstructorWithNullPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new HouseBlock(null));
    }

    /**
     * Tests updating the position of a HouseBlock with a valid new position.
     */
    @Test
    public void testSetPosition() {
        HouseBlock block = new HouseBlock(new Position(0, 0));
        Position newPos = new Position(10, 10);

        block.setPosition(newPos);
        assertEquals(newPos, block.getPosition());
    }

    /**
     * Tests that setting a null position throws an exception.
     */
    @Test
    public void testSetPositionNullThrowsException() {
        HouseBlock block = new HouseBlock(new Position(1, 1));
        assertThrows(IllegalArgumentException.class, () -> block.setPosition(null));
    }

    /**
     * Tests the string representation of a HouseBlock.
     *
     * Ensures that it includes the word "HouseBlock" and the position.
     */
    @Test
    public void testToString() {
        Position pos = new Position(2, 3);
        HouseBlock block = new HouseBlock(pos);
        String result = block.toString();

        assertTrue(result.contains("HouseBlock"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }
}
