package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link IndustryData} enum.
 *
 * This test class ensures that all predefined industries have correct
 * values for name, start year, cost, cargo, and type.
 */
public class IndustryDataTest {

    /**
     * Tests all getter methods for the COAL_MINE industry.
     *
     * This test checks:
     * - name, year, cost, profitability, and type
     * - that COAL is in the supplied cargos
     * - that there are no demanded cargos
     */
    @Test
    public void testGettersForCoalMine() {
        IndustryData data = IndustryData.COAL_MINE;

        assertEquals("Coal Mine", data.getName());
        assertEquals(1800, data.getStartYear());
        assertEquals(300000, data.getCost());
        assertEquals(0, data.getProfitability());
        assertEquals(IndustryType.PRIMARY, data.getType());

        List<String> supplied = data.getSuppliedCargos();
        assertEquals(1, supplied.size());
        assertTrue(supplied.contains("Coal"));

        List<String> demanded = data.getDemandedCargos();
        assertTrue(demanded.isEmpty());
    }

    /**
     * Tests all getter methods for the STEEL_MILL industry.
     *
     * This test checks:
     * - name, year, cost, profitability, and type
     * - that STEEL is in the supplied cargos
     * - that COAL and IRON are in the demanded cargos
     */
    @Test
    public void testGettersForSteelMill() {
        IndustryData data = IndustryData.STEEL_MILL;

        assertEquals("Steel Mill", data.getName());
        assertEquals(1856, data.getStartYear());
        assertEquals(800000, data.getCost());
        assertEquals(60, data.getProfitability());
        assertEquals(IndustryType.MIXED, data.getType());

        List<String> supplied = data.getSuppliedCargos();
        assertEquals(1, supplied.size());
        assertTrue(supplied.contains("Steel"));

        List<String> demanded = data.getDemandedCargos();
        assertTrue(demanded.contains("Coal"));
        assertTrue(demanded.contains("Iron"));
    }

    /**
     * Tests that all enum values are valid and complete.
     *
     * This test checks that for each industry:
     * - name, cargos and type are not null
     */
    @Test
    public void testAllEnumValuesAccessible() {
        for (IndustryData data : IndustryData.values()) {
            assertNotNull(data.getName());
            assertNotNull(data.getSuppliedCargos());
            assertNotNull(data.getDemandedCargos());
            assertNotNull(data.getType());
        }
    }
}
