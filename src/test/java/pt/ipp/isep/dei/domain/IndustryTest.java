package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Industry class.
 */
public class IndustryTest {

    /**
     * Test that a valid Industry can be created successfully.
     */
    @Test
    public void ensureValidIndustryCanBeCreated() {
        Position position = new Position(10, 20);
        IndustryTypes.IndustryType type = IndustryTypes.IndustryType.PRIMARY;
        Industry industry = new Industry("IndustryName", position, type);

        assertNotNull(industry);
        assertEquals("IndustryName", industry.getName());
        assertEquals(position, industry.getPosition());
        assertEquals(type, industry.getType());
    }

    /**
     * Test the isAtPosition method when the positions match.
     */
    @Test
    public void testIsAtPositionTrue() {
        Position position = new Position(10, 20);
        Industry industry = new Industry("IndustryName", position, IndustryTypes.IndustryType.PRIMARY);

        assertTrue(industry.isAtPosition(new Position(10, 20)));
    }

    /**
     * Test the isAtPosition method when the positions do not match.
     */
    @Test
    public void testIsAtPositionFalse() {
        Position position = new Position(10, 20);
        Industry industry = new Industry("IndustryName", position, IndustryTypes.IndustryType.PRIMARY);

        assertFalse(industry.isAtPosition(new Position(5, 5)));
    }

    /**
     * Test setting a new name in the Industry.
     */
    @Test
    public void testSetName() {
        Industry industry = new Industry("OldName", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        industry.setName("NewName");

        assertEquals("NewName", industry.getName());
    }

    /**
     * Test setting a new position in the Industry.
     */
    @Test
    public void testSetPosition() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        Position newPosition = new Position(15, 25);
        industry.setPosition(newPosition);

        assertEquals(newPosition, industry.getPosition());
    }

    /**
     * Test setting a new industry type in the Industry.
     */
    @Test
    public void testSetType() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        IndustryTypes.IndustryType newType = IndustryTypes.IndustryType.MIXED;
        industry.setType(newType);

        assertEquals(newType, industry.getType());
    }

    /**
     * Test the toString method to verify the string representation of an Industry.
     */
    @Test
    public void testToString() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        String expected = "Industry{-Name='Industry'-Position=Position{x=10, y=20}-Type=PRIMARY}";

        assertEquals(expected, industry.toString());
    }
}
