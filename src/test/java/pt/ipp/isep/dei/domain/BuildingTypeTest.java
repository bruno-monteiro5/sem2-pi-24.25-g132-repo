package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BuildingType} enum.
 * Verifies correctness of getters, availability logic, and toString formatting.
 */
public class BuildingTypeTest {

    /**
     * Tests getter methods for all core attributes.
     */
    @Test
    public void testBasicGetters() {
        BuildingType type = BuildingType.TELEPHONE;

        assertEquals(10000, type.getCost());
        assertEquals(BuildingType.TELEGRAPH, type.getReplaces());
        assertEquals(1900, type.getAvailableYear());
        assertEquals(0.10, type.getEfficiencyBonus());
        assertEquals(1.1, type.getRevenueMultiplier());
        assertEquals(0, type.getExtraStorageCapacity());
    }

    /**
     * Tests the availability check for buildings.
     */
    @Test
    public void testIsAvailableIn() {
        BuildingType type = BuildingType.CAFE_LARGE;

        assertFalse(type.isAvailableIn(1850)); // before availability
        assertTrue(type.isAvailableIn(1900));  // at year
        assertTrue(type.isAvailableIn(2000));  // after
    }

    /**
     * Tests the getBuildingTypeDetails method returns self.
     */
    @Test
    public void testSelfReferenceMethod() {
        BuildingType type = BuildingType.HOTEL_SMALL;

        assertSame(type, type.getBuildingTypeDetails());
    }

    /**
     * Tests the toString method with basic info.
     */
    @Test
    public void testToStringBasicInfo() {
        String result = BuildingType.SILO.toString();

        assertTrue(result.contains("SILO"));
        assertTrue(result.contains("availableYear=1850"));
        assertTrue(result.contains("extraStorageCapacity=20"));
    }
}
