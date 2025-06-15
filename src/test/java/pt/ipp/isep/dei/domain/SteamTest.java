package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link Steam} fuel type.
 *
 * This test checks that the Steam fuel type behaves as expected.
 */
public class SteamTest {

    /**
     * Tests all public methods of the Steam class.
     *
     * It checks that:
     * - The name is "Steam"
     * - The toString() returns "Steam"
     * - The method requiresElectrifiedLine() returns false
     */
    @Test
    public void testSteamMethods() {
        Steam steam = new Steam();

        assertEquals("Steam", steam.name());             // name should be "Steam"
        assertEquals("Steam", steam.toString());         // toString should also return "Steam"
        assertFalse(steam.requiresElectrifiedLine());    // Steam trains do not need electrified lines
    }
}
