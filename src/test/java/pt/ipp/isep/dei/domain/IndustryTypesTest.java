package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the IndustryTypes class.
 */
public class IndustryTypesTest {

    /**
     * Test the getAllTypes method to ensure all industry types are returned.
     */
    @Test
    public void testGetAllTypes() {
        String[] expected = {"PRIMARY", "TRANSFORMING", "MIXED"};
        String[] actual = IndustryTypes.getAllTypes();

        assertArrayEquals(expected, actual);
    }

    /**
     * Test the getDescription method to ensure correct descriptions are returned for each enum.
     */
    @Test
    public void testIndustryTypeDescriptions() {
        assertEquals("Primary Industry", IndustryTypes.IndustryType.PRIMARY.getDescription());
        assertEquals("Transforming Industry", IndustryTypes.IndustryType.TRANSFORMING.getDescription());
        assertEquals("Mixed Industry", IndustryTypes.IndustryType.MIXED.getDescription());
    }
}
