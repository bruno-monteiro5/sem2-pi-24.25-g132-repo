package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link IndustryData} enum.
 * Covers all accessor methods and validates data consistency.
 */
public class IndustryDataTest {

    /**
     * Tests basic accessors for a typical enum value.
     */
    @Test
    void testAccessorsForCoalMine() {
        IndustryData data = IndustryData.COAL_MINE;

        assertEquals("Coal Mine", data.getName());
        assertEquals(1800, data.getStartYear());
        assertEquals(List.of("Coal"), data.getSuppliedCargos());
        assertEquals(List.of(), data.getDemandedCargos());
        assertEquals(300000, data.getCost());
        assertEquals(0, data.getProfitability());
        assertEquals(IndustryType.PRIMARY, data.getType());
    }

    /**
     * Tests a mixed industry with both supplied and demanded cargos.
     */
    @Test
    void testAccessorsForSteelMill() {
        IndustryData data = IndustryData.STEEL_MILL;

        assertEquals("Steel Mill", data.getName());
        assertEquals(1856, data.getStartYear());
        assertEquals(List.of("Steel"), data.getSuppliedCargos());
        assertEquals(List.of("Coal", "Iron"), data.getDemandedCargos());
        assertEquals(800000, data.getCost());
        assertEquals(60, data.getProfitability());
        assertEquals(IndustryType.MIXED, data.getType());
    }

    /**
     * Verifies that all enum constants are unique and accessible.
     */
    @Test
    void testAllEnumValuesAreAccessible() {
        for (IndustryData data : IndustryData.values()) {
            assertNotNull(data.getName());
            assertNotNull(data.getSuppliedCargos());
            assertNotNull(data.getDemandedCargos());
            assertNotNull(data.getType());
        }

        assertTrue(IndustryData.values().length >= 45);
    }
}
