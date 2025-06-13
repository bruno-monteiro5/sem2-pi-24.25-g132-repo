package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Diesel class.
 */
public class DieselTest {

    /**
     * Tests that Diesel returns the correct name.
     */
    @Test
    void testName() {
        FuelType diesel = new Diesel();
        assertEquals("Diesel", diesel.name());
    }

    /**
     * Tests that Diesel returns the correct string representation.
     */
    @Test
    void testToString() {
        FuelType diesel = new Diesel();
        assertEquals("Diesel", diesel.toString());
    }

    /**
     * Tests that Diesel does not require electrification.
     */
    @Test
    void testRequiresElectrifiedLine() {
        FuelType diesel = new Diesel();
        assertFalse(diesel.requiresElectrifiedLine());
    }
}
