package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link Diesel} fuel type.
 *
 * This test checks that the Diesel fuel type behaves as expected.
 */
public class DieselTest {

    /**
     * Tests all public methods of the Diesel class.
     *
     * It checks that:
     * - The name is "Diesel"
     * - The toString() returns "Diesel"
     * - The method requiresElectrifiedLine() returns false
     */
    @Test
    public void testDieselMethods() {
        Diesel diesel = new Diesel();

        assertEquals("Diesel", diesel.name());             // name should be "Diesel"
        assertEquals("Diesel", diesel.toString());         // toString should return "Diesel"
        assertFalse(diesel.requiresElectrifiedLine());     // Diesel trains do not need electrified lines
    }
}
