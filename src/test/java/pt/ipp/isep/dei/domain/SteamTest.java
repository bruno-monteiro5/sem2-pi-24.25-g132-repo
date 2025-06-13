package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Steam class.
 */
public class SteamTest {

    /**
     * Tests that Steam returns the correct name.
     */
    @Test
    void testName() {
        FuelType steam = new Steam();
        assertEquals("Steam", steam.name());
    }

    /**
     * Tests that Steam returns the correct string representation.
     */
    @Test
    void testToString() {
        FuelType steam = new Steam();
        assertEquals("Steam", steam.toString());
    }

    /**
     * Tests that Steam does not require electrification.
     */
    @Test
    void testRequiresElectrifiedLine() {
        FuelType steam = new Steam();
        assertFalse(steam.requiresElectrifiedLine());
    }
}
