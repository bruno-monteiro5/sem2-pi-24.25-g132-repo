package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link Electric} fuel type.
 *
 * This test checks that the Electric fuel type behaves as expected.
 */
public class ElectricTest {

    /**
     * Tests all public methods of the Electric class.
     *
     * It checks that:
     * - The name is "Electric"
     * - The toString() returns "Electric"
     * - The method requiresElectrifiedLine() returns true
     */
    @Test
    public void testElectricMethods() {
        Electric electric = new Electric();

        assertEquals("Electric", electric.name());             // name should be "Electric"
        assertEquals("Electric", electric.toString());         // toString should return "Electric"
        assertTrue(electric.requiresElectrifiedLine());        // Electric trains need electrified lines
    }
}
