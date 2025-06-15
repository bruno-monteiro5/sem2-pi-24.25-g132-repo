package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FuelType} and its subclasses.
 *
 * This class checks that all subclasses (Steam, Diesel, Electric) work correctly,
 * and that comparison and availability methods return the expected results.
 */
public class FuelTypeTest {

    /**
     * Tests the Steam fuel type.
     *
     * This test checks that the name is "Steam", it does not require electrified lines,
     * the toString is correct, and compareTo returns 0 when comparing two Steam instances.
     */
    @Test
    public void testSteamConstructorAndMethods() {
        FuelType fuel = new Steam();
        assertEquals("Steam", fuel.name());
        assertEquals("Steam", fuel.toString());
        assertFalse(fuel.requiresElectrifiedLine());
        assertEquals(0, fuel.compareTo(new Steam()));
    }

    /**
     * Tests the Diesel fuel type.
     *
     * This test checks that the name is "Diesel", it does not require electrified lines,
     * and compareTo returns a different value when compared to Steam.
     */
    @Test
    public void testDieselConstructorAndMethods() {
        FuelType fuel = new Diesel();
        assertEquals("Diesel", fuel.name());
        assertEquals("Diesel", fuel.toString());
        assertFalse(fuel.requiresElectrifiedLine());
        assertTrue(fuel.compareTo(new Steam()) != 0);
    }

    /**
     * Tests the Electric fuel type.
     *
     * This test checks that the name is "Electric", it requires electrified lines,
     * and the toString is correct.
     */
    @Test
    public void testElectricConstructorAndMethods() {
        FuelType fuel = new Electric();
        assertEquals("Electric", fuel.name());
        assertEquals("Electric", fuel.toString());
        assertTrue(fuel.requiresElectrifiedLine());
    }

    /**
     * Tests the compareTo method with all fuel types.
     *
     * This test checks that compareTo can be used to sort or compare different types,
     * and always returns a non-zero value between different types.
     */
    @Test
    public void testCompareToOrdering() {
        FuelType steam = new Steam();
        FuelType diesel = new Diesel();
        FuelType electric = new Electric();

        assertTrue(diesel.compareTo(electric) != 0);
        assertTrue(electric.compareTo(steam) != 0);
        assertTrue(steam.compareTo(diesel) != 0);
    }

    /**
     * Tests the list returned by availableFuelTypes().
     *
     * This test checks that the method returns exactly 3 fuel types:
     * one of each subclass (Steam, Diesel, Electric).
     */
    @Test
    public void testAvailableFuelTypesCoversAll() {
        List<FuelType> fuels = FuelType.availableFuelTypes();

        assertEquals(3, fuels.size());
        assertTrue(fuels.stream().anyMatch(f -> f instanceof Steam));
        assertTrue(fuels.stream().anyMatch(f -> f instanceof Diesel));
        assertTrue(fuels.stream().anyMatch(f -> f instanceof Electric));
    }
}
