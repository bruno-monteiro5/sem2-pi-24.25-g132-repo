package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StationType} enum.
 * <p>
 * Tests getters, boolean flags, and toString output.
 */
public class StationTypeTest {

    /**
     * Tests the getter methods for all station types.
     */
    @Test
    public void testGetters() {
        StationType depot = StationType.DEPOT;
        StationType station = StationType.STATION;
        StationType terminal = StationType.TERMINAL;

        // DEPOT
        assertEquals(50000, depot.getCost());
        assertEquals(3, depot.getRadius());
        assertEquals("Depot", depot.getName());
        assertFalse(depot.needsCenter());

        // STATION
        assertEquals(100000, station.getCost());
        assertEquals(4, station.getRadius());
        assertEquals("Station", station.getName());
        assertTrue(station.needsCenter());

        // TERMINAL
        assertEquals(200000, terminal.getCost());
        assertEquals(5, terminal.getRadius());
        assertEquals("Terminal", terminal.getName());
        assertFalse(terminal.needsCenter());
    }

    /**
     * Tests the toString method returns a readable name with cost.
     */
    @Test
    public void testToStringFormat() {
        assertEquals("DEPOT (50k)", StationType.DEPOT.toString());
        assertEquals("STATION (100k)", StationType.STATION.toString());
        assertEquals("TERMINAL (200k)", StationType.TERMINAL.toString());
    }

    /**
     * Ensures all enum values are accessible and valid.
     */
    @Test
    public void testEnumValuesExist() {
        StationType[] values = StationType.values();
        assertEquals(3, values.length);
        assertEquals(StationType.DEPOT, StationType.valueOf("DEPOT"));
        assertEquals(StationType.STATION, StationType.valueOf("STATION"));
        assertEquals(StationType.TERMINAL, StationType.valueOf("TERMINAL"));
    }
}
