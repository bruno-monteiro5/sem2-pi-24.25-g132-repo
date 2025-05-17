package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the IndustryTypes class.
 * Tests functionality related to industry types.
 */
public class IndustryTypesTest {

    /**
     * Tests if the getAllTypes method returns an array with all industry types.
     * Verifies that the array has the correct size and contains all expected enum values.
     */
    @Test
    public void testGetAllTypes() {
        String[] types = IndustryTypes.getAllTypes();

        assertEquals(3, types.length);
        assertEquals("PRIMARY", types[0]);
        assertEquals("TRANSFORMING", types[1]);
        assertEquals("MIXED", types[2]);
    }

    /**
     * Tests if the description of industry types is correctly configured.
     * Verifies that each enum returns the expected description.
     */
    @Test
    public void testIndustryTypeDescription() {
        assertEquals("Primary Industry", IndustryTypes.IndustryType.PRIMARY.getDescription());
        assertEquals("Transforming Industry", IndustryTypes.IndustryType.TRANSFORMING.getDescription());
        assertEquals("Mixed Industry", IndustryTypes.IndustryType.MIXED.getDescription());
    }

    /**
     * Tests if the enum IndustryType values are being correctly returned.
     * Verifies that values() returns all expected values.
     */
    @Test
    public void testIndustryTypeValues() {
        IndustryTypes.IndustryType[] types = IndustryTypes.IndustryType.values();

        assertEquals(3, types.length);
        assertEquals(IndustryTypes.IndustryType.PRIMARY, types[0]);
        assertEquals(IndustryTypes.IndustryType.TRANSFORMING, types[1]);
        assertEquals(IndustryTypes.IndustryType.MIXED, types[2]);
    }
}
