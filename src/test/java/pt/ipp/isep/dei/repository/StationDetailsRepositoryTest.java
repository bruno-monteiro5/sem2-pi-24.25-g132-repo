package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StationDetailsRepository} class.
 *
 * Ensures all public methods of the repository behave correctly under various scenarios,
 * with special attention to edge cases and consistent state management.
 */
class StationDetailsRepositoryTest {

    private StationDetailsRepository repository;

    /**
     * Resets the singleton instance before each test using reflection to ensure independence of tests.
     */
    @BeforeEach
    void resetRepository() throws Exception {
        var instanceField = StationDetailsRepository.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
        repository = StationDetailsRepository.getInstance();
    }

    /**
     * Tests that the singleton instance is properly returned and ensures it is unique.
     */
    @Test
    void testGetInstance() {
        StationDetailsRepository instance1 = StationDetailsRepository.getInstance();
        StationDetailsRepository instance2 = StationDetailsRepository.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    /**
     * Tests adding a new StationDetails object to the repository.
     */
    @Test
    void testAddStationDetails() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.DEPOT);
        repository.addStationDetails(details);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(1, result.size());
        assertEquals(details, result.get(0));
    }

    /**
     * Tests retrieving all StationDetails objects from the repository when it is empty.
     */
    @Test
    void testGetAllStationDetailsWhenEmpty() {
        List<StationDetails> result = repository.getAllStationDetails();
        assertTrue(result.isEmpty());
    }

    /**
     * Tests retrieving all StationDetails objects after adding multiple entries.
     */
    @Test
    void testGetAllStationDetailsWithEntries() {
        StationDetails details1 = new StationDetails("Station 1", new Position(10, 20), StationType.STATION);
        StationDetails details2 = new StationDetails("Station 2", new Position(30, 40), StationType.TERMINAL);

        repository.addStationDetails(details1);
        repository.addStationDetails(details2);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(2, result.size());
        assertTrue(result.contains(details1));
        assertTrue(result.contains(details2));
    }

    /**
     * Tests retrieving station details by name when the station exists.
     */
    @Test
    void testGetStationDetailsByNameExists() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.STATION);
        repository.addStationDetails(details);

        StationDetails result = repository.getStationDetailsByName("Test Station");
        assertNotNull(result);
        assertEquals(details, result);
    }

    /**
     * Tests retrieving station details by name when the station does not exist.
     */
    @Test
    void testGetStationDetailsByNameNotExists() {
        StationDetails result = repository.getStationDetailsByName("Nonexistent Station");
        assertNull(result);
    }

    /**
     * Tests removing a station's details by name when the station exists.
     */
    @Test
    void testRemoveStationDetailsByNameExists() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.STATION);
        repository.addStationDetails(details);

        boolean removed = repository.removeStationDetailsByName("Test Station");
        assertTrue(removed);

        assertTrue(repository.getAllStationDetails().isEmpty());
    }

    /**
     * Tests removing a station's details by name when the station does not exist.
     */
    @Test
    void testRemoveStationDetailsByNameNotExists() {
        boolean removed = repository.removeStationDetailsByName("Nonexistent Station");
        assertFalse(removed);
    }

    /**
     * Tests adding a station to the repository from a Station object.
     */
    @Test
    void testAddStationFromStation() {
        Station station = new Station("Generated Station", new Position(50, 60), StationType.TERMINAL);
        repository.addStationFromStation(station);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(1, result.size());

        StationDetails details = result.get(0);
        assertEquals("Generated Station", details.getStationName());
        assertEquals(new Position(50, 60), details.getPosition());
        assertEquals(StationType.TERMINAL, details.getStationType());
    }
}