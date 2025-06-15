package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link IndustryType} enum.
 *
 * These tests ensure that the enum values return the correct descriptions
 * and are properly defined and accessible.
 */
public class IndustryTypeTest {

    /**
     * Tests the getDescription method for each enum value.
     *
     * Checks that each type returns the expected human-readable description.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Primary Industry", IndustryType.PRIMARY.getDescription());
        assertEquals("Transforming Industry", IndustryType.TRANSFORMING.getDescription());
        assertEquals("Mixed Industry", IndustryType.MIXED.getDescription());
    }

    /**
     * Tests that all enum values exist and can be accessed by name.
     *
     * Also confirms that the total number of enum constants is correct.
     */
    @Test
    public void testEnumValues() {
        IndustryType[] values = IndustryType.values();
        assertEquals(3, values.length);

        assertEquals(IndustryType.PRIMARY, IndustryType.valueOf("PRIMARY"));
        assertEquals(IndustryType.TRANSFORMING, IndustryType.valueOf("TRANSFORMING"));
        assertEquals(IndustryType.MIXED, IndustryType.valueOf("MIXED"));
    }
}
