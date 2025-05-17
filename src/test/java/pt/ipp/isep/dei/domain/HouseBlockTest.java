package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HouseBlock class.
 */
public class HouseBlockTest {

    /**
     * Tests the creation of a valid HouseBlock and verifies its position.
     */
    @Test
    void testValidHouseBlockCreation() {
        Position position = new Position(2, 3);
        HouseBlock houseBlock = new HouseBlock(position);
        assertEquals(position, houseBlock.getPosition());
    }

    /**
     * Ensures that creating a HouseBlock with a null position throws an IllegalArgumentException.
     */
    @Test
    void testConstructorNullPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new HouseBlock(null));
    }

    /**
     * Tests setting a valid position for a HouseBlock.
     */
    @Test
    void testSetPositionValid() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(5, 6);
        HouseBlock houseBlock = new HouseBlock(position1);
        houseBlock.setPosition(position2);
        assertEquals(position2, houseBlock.getPosition());
    }

    /**
     * Ensures that setting a null position throws an IllegalArgumentException.
     */
    @Test
    void testSetPositionNullThrowsException() {
        Position position = new Position(1, 1);
        HouseBlock houseBlock = new HouseBlock(position);
        assertThrows(IllegalArgumentException.class, () -> houseBlock.setPosition(null));
    }

    /**
     * Tests the string representation of a HouseBlock.
     */
    @Test
    void testToString() {
        Position position = new Position(3, 4);
        HouseBlock houseBlock = new HouseBlock(position);
        String str = houseBlock.toString();
        assertTrue(str.contains("HouseBlock"));
        assertTrue(str.contains("position=" + position));
    }
}
