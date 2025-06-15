package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.Map;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MapRepository}.
 * Ensures full coverage including singleton, validation, and repository logic.
 */
class MapRepositoryTest {

    private MapRepository repository;

    /**
     * Resets the singleton instance and clears internal state before each test.
     */
    @BeforeEach
    void resetSingleton() throws Exception {
        Field instanceField = MapRepository.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null); // Reset singleton instance

        repository = MapRepository.getInstance();
        repository.getMaps().clear();

        // Reset activeMap field
        Field activeMapField = MapRepository.class.getDeclaredField("activeMap");
        activeMapField.setAccessible(true);
        activeMapField.set(repository, null);
    }

    @Test
    void testSingletonInstance() {
        MapRepository repo1 = MapRepository.getInstance();
        MapRepository repo2 = MapRepository.getInstance();
        assertSame(repo1, repo2, "Should return same singleton instance");
    }

    @Test
    void testAddValidMap() {
        Map map = new Map("Map1", 60, 60, 1);
        assertTrue(repository.addMap(map));
        assertTrue(repository.getMaps().contains(map));
    }

    @Test
    void testAddNullMap() {
        assertFalse(repository.addMap(null));
    }

    @Test
    void testAddDuplicateMap() {
        Map map = new Map("MapX", 60, 60, 1);
        assertTrue(repository.addMap(map));
        assertFalse(repository.addMap(map), "Should not add duplicate map");
    }

    @Test
    void testSetAndGetActiveMapSuccess() {
        Map map = new Map("ActiveMap", 60, 60, 1);
        assertTrue(repository.setActiveMap(map));
        assertEquals(map, repository.getActiveMap());
    }

    @Test
    void testSetActiveMapAlreadySet() {
        Map map1 = new Map("Map1", 60, 60, 1);
        Map map2 = new Map("Map2", 60, 60, 1);
        repository.setActiveMap(map1);
        assertFalse(repository.setActiveMap(map2));
    }

    @Test
    void testGetActiveMapWhenUnsetThrowsException() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> repository.getActiveMap());
        assertEquals("No active map", ex.getMessage());
    }

    @Test
    void testGetMapsReturnsInternalList() {
        Map map = new Map("TestMap", 60, 60, 1);
        repository.addMap(map);
        List<Map> result = repository.getMaps();
        assertEquals(1, result.size());
        assertSame(map, result.get(0));
    }

    @Test
    void testGetLastCreatedReturnsNullWhenEmpty() {
        assertNull(repository.getLastCreated());
    }

    @Test
    void testGetLastCreatedReturnsLastMap() {
        Map map1 = new Map("First", 60, 60, 1);
        Map map2 = new Map("Last", 70, 70, 1);
        repository.addMap(map1);
        repository.addMap(map2);
        assertEquals(map2, repository.getLastCreated());
    }
}
