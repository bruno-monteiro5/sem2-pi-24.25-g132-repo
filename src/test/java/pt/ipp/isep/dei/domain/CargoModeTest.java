package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CargoMode} enum.
 * <p>
 * Verifies correct enum behavior and access to values.
 */
public class CargoModeTest {

    /**
     * Tests that all enum values exist and are correctly named.
     */
    @Test
    public void testEnumValuesExist() {
        assertEquals(CargoMode.FULL, CargoMode.valueOf("FULL"));
        assertEquals(CargoMode.HALF, CargoMode.valueOf("HALF"));
        assertEquals(CargoMode.AVAILABLE, CargoMode.valueOf("AVAILABLE"));
    }

    /**
     * Tests that the values() method returns all defined constants in order.
     */
    @Test
    public void testValuesMethod() {
        CargoMode[] values = CargoMode.values();
        assertEquals(3, values.length);
        assertEquals(CargoMode.FULL, values[0]);
        assertEquals(CargoMode.HALF, values[1]);
        assertEquals(CargoMode.AVAILABLE, values[2]);
    }

    /**
     * Tests that enum constants are not null and of the correct type.
     */
    @Test
    public void testEnumNonNullAndType() {
        for (CargoMode mode : CargoMode.values()) {
            assertNotNull(mode);
            assertTrue(mode instanceof CargoMode);
        }
    }
}
