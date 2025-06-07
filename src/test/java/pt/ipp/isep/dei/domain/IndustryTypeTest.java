package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link IndustryType} enum.
 * Ensures full coverage of enum constants and the getDescription() method.
 */
public class IndustryTypeTest {

    /**
     * Tests the PRIMARY industry type and its description.
     */
    @Test
    void testPrimaryIndustryType() {
        IndustryType type = IndustryType.PRIMARY;
        assertEquals("Primary Industry", type.getDescription());
    }

    /**
     * Tests the TRANSFORMING industry type and its description.
     */
    @Test
    void testTransformingIndustryType() {
        IndustryType type = IndustryType.TRANSFORMING;
        assertEquals("Transforming Industry", type.getDescription());
    }

    /**
     * Tests the MIXED industry type and its description.
     */
    @Test
    void testMixedIndustryType() {
        IndustryType type = IndustryType.MIXED;
        assertEquals("Mixed Industry", type.getDescription());
    }

    /**
     * Verifies all enum values are present and in the correct order.
     */
    @Test
    void testValues() {
        IndustryType[] values = IndustryType.values();
        assertEquals(3, values.length);
        assertEquals(IndustryType.PRIMARY, values[0]);
        assertEquals(IndustryType.TRANSFORMING, values[1]);
        assertEquals(IndustryType.MIXED, values[2]);
    }

    /**
     * Verifies valueOf() returns correct enum constants.
     */
    @Test
    void testValueOf() {
        assertEquals(IndustryType.PRIMARY, IndustryType.valueOf("PRIMARY"));
        assertEquals(IndustryType.TRANSFORMING, IndustryType.valueOf("TRANSFORMING"));
        assertEquals(IndustryType.MIXED, IndustryType.valueOf("MIXED"));
    }
}

