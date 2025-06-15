package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LocomotiveRepository} class.
 * Ensures 100% coverage of methods and logic.
 */
class LocomotiveRepositoryTest {

    private LocomotiveRepository repository;

    /**
     * Setup before each test case.
     */
    @BeforeEach
    void setUp() {
        repository = new LocomotiveRepository();
    }

    /**
     * Tests adding a valid locomotive.
     */
    @Test
    void testAddLocomotiveSuccess() {
        Locomotive loco = new Locomotive(LocomotiveType.HUDSON);
        assertTrue(repository.addLocomotive(loco), "Should add valid locomotive");
        assertTrue(repository.getLocomotives().contains(loco), "Locomotive should be in the list");
    }

    /**
     * Tests adding a null locomotive.
     */
    @Test
    void testAddNullLocomotive() {
        assertFalse(repository.addLocomotive(null), "Should not add null locomotive");
    }

    /**
     * Tests adding a duplicate locomotive.
     */
    @Test
    void testAddDuplicateLocomotive() {
        Locomotive loco = new Locomotive(LocomotiveType.BIG_BOY);
        assertTrue(repository.addLocomotive(loco), "First add should succeed");
        assertFalse(repository.addLocomotive(loco), "Duplicate should be rejected");
    }

    /**
     * Tests the save method, which internally calls addLocomotive().
     */
    @Test
    void testSave() {
        Locomotive loco = new Locomotive(LocomotiveType.PACIFIC);
        repository.save(loco);
        assertTrue(repository.getLocomotives().contains(loco), "Saved locomotive should be present");
    }

    /**
     * Tests getLocomotives returns a copy, not the internal list.
     */
    @Test
    void testGetLocomotivesReturnsCopy() {
        Locomotive loco = new Locomotive(LocomotiveType.STREAMLINER);
        repository.addLocomotive(loco);
        List<Locomotive> list = repository.getLocomotives();
        list.clear();  // Should not affect internal list
        assertEquals(1, repository.getLocomotives().size(), "Internal list should remain unchanged");
    }
}
