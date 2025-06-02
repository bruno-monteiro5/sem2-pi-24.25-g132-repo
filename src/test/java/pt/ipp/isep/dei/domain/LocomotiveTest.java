
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Locomotive} class.
 * Ensures 100% test coverage of all public methods.
 */
public class LocomotiveTest {

    /**
     * Test constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        Locomotive loco = new Locomotive(1, LocomotiveType.DASH_9);
        assertEquals(1, loco.getId());
        assertEquals(LocomotiveType.DASH_9, loco.getType());
    }

    /**
     * Test toString() method.
     */
    @Test
    public void testToString() {
        Locomotive loco = new Locomotive(1, LocomotiveType.DASH_9);
        String result = loco.toString();
        assertTrue(result.contains("Locomotive {"));
        assertTrue(result.contains("ID=1"));
        assertTrue(result.contains("Dash-9"));
        assertTrue(result.contains("Diesel"));
        assertTrue(result.contains("Speed="));
    }

    /**
     * Test equals() and hashCode().
     */
    @Test
    public void testEqualsAndHashCode() {
        Locomotive loco1 = new Locomotive(1, LocomotiveType.DASH_9);
        Locomotive loco2 = new Locomotive(1, LocomotiveType.STREAMLINER);
        Locomotive loco3 = new Locomotive(2, LocomotiveType.DASH_9);

        assertEquals(loco1, loco2); // Same ID
        assertNotEquals(loco1, loco3); // Different ID

        assertEquals(loco1.hashCode(), loco2.hashCode());
        assertNotEquals(loco1.hashCode(), loco3.hashCode());
    }
}

