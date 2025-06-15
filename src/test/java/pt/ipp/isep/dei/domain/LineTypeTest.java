package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LineType} enum.
 * <p>
 * This test class verifies that all enum values exist and can be retrieved correctly.
 */
public class LineTypeTest {

    /**
     * Tests that all expected enum values exist and are not null.
     */
    @Test
    public void testEnumValuesExistence() {
        assertNotNull(LineType.SINGLE_ELECTRIFIED);
        assertNotNull(LineType.DOUBLE_ELECTRIFIED);
        assertNotNull(LineType.SINGLE_NOT_ELECTRICITY);
        assertNotNull(LineType.DOUBLE_NOT_ELECTRICITY);
    }

    /**
     * Tests the number and names of enum constants using {@code values()}.
     */
    @Test
    public void testEnumValues() {
        LineType[] values = LineType.values();
        assertEquals(4, values.length);
        assertEquals(LineType.SINGLE_ELECTRIFIED, LineType.valueOf("SINGLE_ELECTRIFIED"));
        assertEquals(LineType.DOUBLE_ELECTRIFIED, LineType.valueOf("DOUBLE_ELECTRIFIED"));
        assertEquals(LineType.SINGLE_NOT_ELECTRICITY, LineType.valueOf("SINGLE_NOT_ELECTRICITY"));
        assertEquals(LineType.DOUBLE_NOT_ELECTRICITY, LineType.valueOf("DOUBLE_NOT_ELECTRICITY"));
    }

    /**
     * Tests that the enum returns the correct string representation (name).
     */
    @Test
    public void testEnumToString() {
        assertEquals("SINGLE_ELECTRIFIED", LineType.SINGLE_ELECTRIFIED.toString());
        assertEquals("DOUBLE_ELECTRIFIED", LineType.DOUBLE_ELECTRIFIED.toString());
        assertEquals("SINGLE_NOT_ELECTRICITY", LineType.SINGLE_NOT_ELECTRICITY.toString());
        assertEquals("DOUBLE_NOT_ELECTRICITY", LineType.DOUBLE_NOT_ELECTRICITY.toString());
    }
}
