package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Industry} class.
 *
 * These tests verify object creation, accessors, mutators,
 * position checking, and equality logic.
 */
public class IndustryTest {

    /**
     * Tests the constructor with manual name, position and type.
     *
     * It checks that all fields are correctly stored,
     * and that cargoType and yearlyQuantity are default.
     */
    @Test
    public void testConstructorWithNamePositionType() {
        Position pos = new Position(1, 2);
        Industry industry = new Industry("FactoryA", pos, IndustryType.MIXED);

        assertEquals("FactoryA", industry.getName());
        assertEquals(pos, industry.getPosition());
        assertEquals(IndustryType.MIXED, industry.getType());
        assertNull(industry.getCargoType());
        assertEquals(0, industry.getYearlyQuantity());
    }

    /**
     * Tests the constructor that uses IndustryData.
     *
     * It checks that the name and type are taken from IndustryData,
     * and that the position is assigned as expected.
     */
    @Test
    public void testConstructorWithIndustryData() {
        Position pos = new Position(3, 4);
        Industry industry = new Industry(IndustryData.COAL_MINE, pos);

        assertEquals(IndustryData.COAL_MINE.getName(), industry.getName());
        assertEquals(IndustryData.COAL_MINE.getType(), industry.getType());
        assertEquals(pos, industry.getPosition());
    }

    /**
     * Tests the setters for name, position and type.
     *
     * This test ensures that the values are correctly updated.
     */
    @Test
    public void testSetters() {
        Industry industry = new Industry("Old", new Position(0, 0), IndustryType.PRIMARY);
        industry.setName("New");
        industry.setPosition(new Position(5, 5));
        industry.setType(IndustryType.TRANSFORMING);

        assertEquals("New", industry.getName());
        assertEquals(new Position(5, 5), industry.getPosition());
        assertEquals(IndustryType.TRANSFORMING, industry.getType());
    }

    /**
     * Tests the string representation of the Industry object.
     *
     * Checks that it includes the name, position and type.
     */
    @Test
    public void testToString() {
        Industry industry = new Industry("Ind", new Position(1, 1), IndustryType.PRIMARY);
        String str = industry.toString();

        assertTrue(str.contains("Ind"));
        assertTrue(str.contains("1"));
        assertTrue(str.contains("PRIMARY"));
    }

    /**
     * Tests the method isAtPosition.
     *
     * It should return true if the position matches, false otherwise.
     */
    @Test
    public void testIsAtPosition() {
        Position pos = new Position(2, 2);
        Industry industry = new Industry("Test", pos, IndustryType.MIXED);

        assertTrue(industry.isAtPosition(new Position(2, 2)));
        assertFalse(industry.isAtPosition(new Position(0, 0)));
    }

    /**
     * Tests the equals method.
     *
     * Verifies equality using:
     * - same name (case-insensitive)
     * - same position
     * - same type
     *
     * Also tests inequality in all cases and with invalid types or null.
     */
    @Test
    public void testEquals() {
        Position pos = new Position(3, 3);
        Industry i1 = new Industry("Same", pos, IndustryType.PRIMARY);
        Industry i2 = new Industry("same", new Position(3, 3), IndustryType.PRIMARY);
        Industry i3 = new Industry("Different", pos, IndustryType.PRIMARY);
        Industry i4 = new Industry("Same", new Position(4, 4), IndustryType.PRIMARY);
        Industry i5 = new Industry("Same", pos, IndustryType.MIXED);

        assertEquals(i1, i2);              // same name (case-insensitive), position, type
        assertNotEquals(i1, i3);           // different name
        assertNotEquals(i1, i4);           // different position
        assertNotEquals(i1, i5);           // different type
        assertNotEquals(i1, null);         // null comparison
        assertNotEquals(i1, "not an industry"); // different class
    }
}
