package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the Industry class.
 * Tests functionality related to industries.
 */
public class IndustryTest {

    private Industry industry;
    private Position position;

    /**
     * Initial setup for the tests.
     * Creates an Industry object to be used in the tests.
     */
    @BeforeEach
    public void setUp() {
        position = new Position(10, 20);
        industry = new Industry("Test Industry", position, IndustryTypes.IndustryType.PRIMARY);
    }

    /**
     * Tests the creation of an Industry object with valid values.
     * Verifies that getters return the correct values.
     */
    @Test
    public void testCreateIndustry() {
        assertEquals("Test Industry", industry.getName());
        assertEquals(position, industry.getPosition());
        assertEquals(IndustryTypes.IndustryType.PRIMARY, industry.getType());
    }

    /**
     * Tests the setName method of the Industry class.
     * Verifies that the name is correctly updated.
     */
    @Test
    public void testSetName() {
        industry.setName("New Name");
        assertEquals("New Name", industry.getName());
    }

    /**
     * Tests the setPosition method of the Industry class.
     * Verifies that the position is correctly updated.
     */
    @Test
    public void testSetPosition() {
        Position newPosition = new Position(30, 40);
        industry.setPosition(newPosition);
        assertEquals(newPosition, industry.getPosition());
    }

    /**
     * Tests the setType method of the Industry class.
     * Verifies that the type is correctly updated.
     */
    @Test
    public void testSetType() {
        industry.setType(IndustryTypes.IndustryType.MIXED);
        assertEquals(IndustryTypes.IndustryType.MIXED, industry.getType());
    }

    /**
     * Tests the toString method of the Industry class.
     * Verifies that the string representation contains all the expected information.
     */
    @Test
    public void testToString() {
        String str = industry.toString();

        assertTrue(str.contains("Test Industry"));
        assertTrue(str.contains(position.toString()));
        assertTrue(str.contains(IndustryTypes.IndustryType.PRIMARY.toString()));
    }

    /**
     * Tests the isAtPosition method of the Industry class.
     * Verifies that the method returns true for the correct position and false for other positions.
     */
    @Test
    public void testIsAtPosition() {
        assertTrue(industry.isAtPosition(position));
        assertFalse(industry.isAtPosition(new Position(30, 40)));
    }
}
