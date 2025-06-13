package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Electric class.
 */
public class ElectricTest {

    /**
     * Tests that Electric returns the correct name.
     */
    @Test
    void testName() {
        FuelType electric = new Electric();
        assertEquals("Electric", electric.name());
    }

    /**
     * Tests that Electric returns the correct string representation.
     */
    @Test
    void testToString() {
        FuelType electric = new Electric();
        assertEquals("Electric", electric.toString());
    }

    /**
     * Tests that Electric requires electrification.
     */
    @Test
    void testRequiresElectrifiedLine() {
        FuelType electric = new Electric();
        assertTrue(electric.requiresElectrifiedLine());
    }
}
