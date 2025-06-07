package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Industry} class.
 * This test class ensures 100% statement and branch coverage.
 */
public class IndustryTest {

    /**
     * Tests the constructor with custom parameters.
     */
    @Test
    void testConstructorWithNameTypeAndPosition() {
        Position position = new Position(5, 10);
        Industry industry = new Industry("Coal Mine", position, IndustryType.PRIMARY);

        assertEquals("Coal Mine", industry.getName());
        assertEquals(position, industry.getPosition());
        assertEquals(IndustryType.PRIMARY, industry.getType());
    }

    /**
     * Tests the constructor using predefined IndustryData.
     */
    @Test
    void testConstructorWithIndustryData() {
        IndustryData data = IndustryData.COAL_MINE;
        Position position = new Position(3, 6);
        Industry industry = new Industry(data, position);

        assertEquals(data.getName(), industry.getName());
        assertEquals(position, industry.getPosition());
        assertEquals(data.getType(), industry.getType());
    }

    /**
     * Tests the get and set methods for name, position, and type.
     */
    @Test
    void testGettersAndSetters() {
        Position pos1 = new Position(2, 2);
        Position pos2 = new Position(3, 3);
        Industry industry = new Industry("Test", pos1, IndustryType.MIXED);

        industry.setName("Updated");
        assertEquals("Updated", industry.getName());

        industry.setPosition(pos2);
        assertEquals(pos2, industry.getPosition());

        industry.setType(IndustryType.TRANSFORMING);
        assertEquals(IndustryType.TRANSFORMING, industry.getType());
    }

    /**
     * Tests the toString() method for expected formatting.
     */
    @Test
    void testToString() {
        Industry industry = new Industry("Bakery", new Position(1, 1), IndustryType.MIXED);
        String result = industry.toString();

        assertTrue(result.contains("Bakery"));
        assertTrue(result.contains("Position"));
        assertTrue(result.contains("MIXED"));
    }

    /**
     * Tests the isAtPosition method for both matching and non-matching positions.
     */
    @Test
    void testIsAtPosition() {
        Position p1 = new Position(7, 7);
        Position p2 = new Position(8, 8);
        Industry industry = new Industry("Oil Well", p1, IndustryType.PRIMARY);

        assertTrue(industry.isAtPosition(new Position(7, 7)));
        assertFalse(industry.isAtPosition(p2));
    }

    /**
     * Tests the equals method for various conditions:
     * - Equal industries (same name, position, type)
     * - Different name (case-insensitive)
     * - Different position
     * - Different type
     * - Null and wrong class
     */
    @Test
    void testEquals() {
        Position pos = new Position(4, 4);
        Industry i1 = new Industry("Iron Mine", pos, IndustryType.PRIMARY);
        Industry i2 = new Industry("iron mine", new Position(4, 4), IndustryType.PRIMARY);
        Industry i3 = new Industry("Different", pos, IndustryType.PRIMARY);
        Industry i4 = new Industry("Iron Mine", new Position(1, 1), IndustryType.PRIMARY);
        Industry i5 = new Industry("Iron Mine", pos, IndustryType.MIXED);

        assertEquals(i1, i2);
        assertNotEquals(i1, i3);
        assertNotEquals(i1, i4);
        assertNotEquals(i1, i5);
        assertNotEquals(i1, null);
        assertNotEquals(i1, "not an industry");
    }
}

