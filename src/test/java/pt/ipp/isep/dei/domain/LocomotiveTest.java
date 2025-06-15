package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Locomotive} class.
 *
 * This class checks if the Locomotive object is created correctly,
 * and if its methods work as expected.
 */
public class LocomotiveTest {

    /**
     * Tests the constructor and getter methods.
     *
     * This test checks if the locomotive type is stored correctly after creation,
     * and that the start year is initialized to 0 by default.
     */
    @Test
    public void testConstructorAndGetters() {
        LocomotiveType type = LocomotiveType.GG1;
        Locomotive loco = new Locomotive(type);

        assertEquals(type, loco.getType()); // check if type is stored
        assertEquals(0, loco.getStartYear()); // check default start year
    }

    /**
     * Tests the toString method.
     *
     * This test checks if the string representation of the locomotive includes
     * the name, fuel type, and top speed from its type.
     */
    @Test
    public void testToString() {
        Locomotive loco = new Locomotive(LocomotiveType.HUDSON);
        String result = loco.toString();

        assertTrue(result.contains("Hudson")); // name from type
        assertTrue(result.contains("Steam"));  // fuel type
        assertTrue(result.contains(String.valueOf(LocomotiveType.HUDSON.getTopSpeed()))); // top speed
    }
}
