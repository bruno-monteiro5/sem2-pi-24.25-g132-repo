package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CargoType} enum.
 * <p>
 * Tests getters, availability logic, and string formatting.
 */
public class CargoTypeTest {

    /**
     * Tests the getters for base value, intro year, and max delivery days.
     */
    @Test
    public void testGetters() {
        CargoType cargo = CargoType.COAL;

        assertEquals(28000, cargo.getBaseValue());
        assertEquals(1800, cargo.getIntroYear());
        assertEquals(1600, cargo.getMaxDeliveryDays());
    }

    /**
     * Tests that cargo is considered available only if year is equal or after intro year.
     */
    @Test
    public void testIsAvailableIn() {
        CargoType cargo = CargoType.URANIUM;

        assertFalse(cargo.isAvailableIn(1949));
        assertTrue(cargo.isAvailableIn(1950));
        assertTrue(cargo.isAvailableIn(2000));
    }

    /**
     * Tests the custom toString method for proper capitalization.
     */
    @Test
    public void testToStringFormatting() {
        assertEquals("Coal", CargoType.COAL.toString());
        assertEquals("Passengers", CargoType.PASSENGERS.toString());
        assertEquals("Uranium", CargoType.URANIUM.toString());
    }

    /**
     * Tests that all enum values are not null and accessible.
     */
    @Test
    public void testAllEnumValuesAccessible() {
        for (CargoType cargo : CargoType.values()) {
            assertNotNull(cargo.getBaseValue());
            assertNotNull(cargo.toString());
            assertTrue(cargo.getIntroYear() >= 1800);
        }
    }
}

