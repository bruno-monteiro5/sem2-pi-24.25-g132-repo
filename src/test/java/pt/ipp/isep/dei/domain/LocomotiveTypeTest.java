package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link LocomotiveType} enum.
 *
 * This test verifies that all getter methods return the correct values
 * for a specific locomotive type.
 */
public class LocomotiveTypeTest {

    /**
     * Tests all getter methods of the LocomotiveType enum.
     *
     * This test uses the GG1 locomotive type and checks if:
     * - The name, years, price, and other attributes match expected values.
     * - The fuel type is an instance of the correct class (Electric).
     */
    @Test
    public void testGetters() {
        LocomotiveType type = LocomotiveType.GG1;

        assertEquals("GG1", type.getName()); // name of the locomotive
        assertEquals(1935, type.getStartYear()); // starting year
        assertEquals(1970, type.getEndYear()); // ending year
        assertEquals(285000, type.getPrice()); // price value
        assertEquals(19000, type.getMaintenance()); // maintenance cost
        assertEquals(42721, type.getFuelCost()); // fuel cost
        assertEquals("Above Average", type.getAcceleration()); // acceleration description
        assertEquals("Outstanding", type.getReliability()); // reliability description
        assertEquals(100, type.getTopSpeed()); // top speed
        assertTrue(type.getFuelType() instanceof Electric); // correct fuel type
    }
}
