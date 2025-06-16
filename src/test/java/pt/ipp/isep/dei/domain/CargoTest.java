package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cargo} class.
 * <p>
 * Tests constructor, getters, setters, copy constructor, and toString.
 */
public class CargoTest {

    /**
     * Tests that the constructor correctly sets all attributes and that getters return expected values.
     */
    @Test
    public void testConstructorAndGetters() {
        Cargo cargo = new Cargo(CargoType.COAL, 50, true, 100.0);

        assertEquals(CargoType.COAL, cargo.getType());
        assertEquals(50, cargo.getQuantity());
        assertTrue(cargo.isSupply());
        assertEquals(100.0, cargo.getBaseValue());
    }

    /**
     * Tests that the copy constructor correctly clones all fields from the original Cargo.
     */
    @Test
    public void testCopyConstructor() {
        Cargo original = new Cargo(CargoType.MAIL, 30, false, 25.5);
        Cargo copy = new Cargo(original);

        assertEquals(original.getType(), copy.getType());
        assertEquals(original.getQuantity(), copy.getQuantity());
        assertEquals(original.isSupply(), copy.isSupply());
        assertEquals(original.getBaseValue(), copy.getBaseValue());
    }

    /**
     * Tests the setter methods and verifies that updates are reflected correctly.
     */
    @Test
    public void testSetters() {
        Cargo cargo = new Cargo(CargoType.IRON, 10, false, 15.0);

        cargo.setType(CargoType.PASSENGERS);
        cargo.setQuantity(99);
        cargo.setSupply(true);

        assertEquals(CargoType.PASSENGERS, cargo.getType());
        assertEquals(99, cargo.getQuantity());
        assertTrue(cargo.isSupply());
    }

    /**
     * Tests the string representation of the cargo.
     */
    @Test
    public void testToString() {
        Cargo cargo = new Cargo(CargoType.WASTE, 20, false, 30.75);
        String result = cargo.toString();

        assertTrue(result.contains("WASTE"));
        assertTrue(result.contains("quantity=20"));
        assertTrue(result.contains("isSupply=false"));
        assertTrue(result.contains("30.75"));
    }
}
