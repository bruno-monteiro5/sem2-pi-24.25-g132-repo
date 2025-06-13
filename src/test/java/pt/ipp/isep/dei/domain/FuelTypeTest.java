package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FuelType class and its concrete subclasses:
 * Steam, Diesel, and Electric.
 */
public class FuelTypeTest {

    /**
     * Tests the name() method of each fuel type.
     */
    @Test
    void testName() {
        FuelType steam = new Steam();
        FuelType diesel = new Diesel();
        FuelType electric = new Electric();

        assertEquals("Steam", steam.name());
        assertEquals("Diesel", diesel.name());
        assertEquals("Electric", electric.name());
    }

    /**
     * Tests the toString() method of each fuel type.
     */
    @Test
    void testToString() {
        FuelType steam = new Steam();
        FuelType diesel = new Diesel();
        FuelType electric = new Electric();

        assertEquals("Steam", steam.toString());
        assertEquals("Diesel", diesel.toString());
        assertEquals("Electric", electric.toString());
    }

    /**
     * Tests the requiresElectrifiedLine() method of each fuel type.
     */
    @Test
    void testRequiresElectrifiedLine() {
        FuelType steam = new Steam();
        FuelType diesel = new Diesel();
        FuelType electric = new Electric();

        assertFalse(steam.requiresElectrifiedLine());
        assertFalse(diesel.requiresElectrifiedLine());
        assertTrue(electric.requiresElectrifiedLine());
    }

    /**
     * Tests the compareTo() method for comparing fuel types by name.
     */
    @Test
    void testCompareTo() {
        FuelType diesel1 = new Diesel();
        FuelType diesel2 = new Diesel();
        FuelType steam = new Steam();
        FuelType electric = new Electric();

        assertEquals(0, diesel1.compareTo(diesel2));
        assertTrue(steam.compareTo(diesel1) > 0);
        assertTrue(diesel1.compareTo(electric) < 0);
    }

    /**
     * Tests the availableFuelTypes() method to ensure all types are returned.
     */
    @Test
    void testAvailableFuelTypes() {
        List<FuelType> types = FuelType.availableFuelTypes();

        assertEquals(3, types.size());

        boolean hasSteam = false;
        boolean hasDiesel = false;
        boolean hasElectric = false;

        for (int i = 0; i < types.size(); i++) {
            FuelType type = types.get(i);
            if (type instanceof Steam) {
                hasSteam = true;
            } else if (type instanceof Diesel) {
                hasDiesel = true;
            } else if (type instanceof Electric) {
                hasElectric = true;
            }
        }

        assertTrue(hasSteam, "Expected list to contain Steam");
        assertTrue(hasDiesel, "Expected list to contain Diesel");
        assertTrue(hasElectric, "Expected list to contain Electric");
    }
}

