package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryType;
import pt.ipp.isep.dei.domain.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link IndustryRepository} class.
 * Ensures correct behavior of storing, checking, and retrieving industries.
 */
public class IndustryRepositoryTest {

    private IndustryRepository repository;
    private Industry industry1;
    private Industry industry2;

    /**
     * Sets up a fresh repository and example industries before each test.
     */
    @BeforeEach
    void setUp() {
        repository = new IndustryRepository();
        industry1 = new Industry("Coal Mine", new Position(1, 1), IndustryType.PRIMARY);
        industry2 = new Industry("Steel Mill", new Position(2, 2), IndustryType.MIXED);
    }

    /**
     * Tests the save() method when the industry does not yet exist.
     * The repository should accept and store it.
     */
    @Test
    void testSaveWhenNotExists() {
        repository.save(industry1);
        assertTrue(repository.contains(industry1));
    }

    /**
     * Tests the save() method when trying to add a duplicate industry.
     * An exception should be thrown.
     */
    @Test
    void testSaveWhenAlreadyExistsThrowsException() {
        repository.save(industry1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            repository.save(new Industry("Coal Mine", new Position(1, 1), IndustryType.PRIMARY));
        });
        assertEquals("Industry already exists.", ex.getMessage());
    }

    /**
     * Tests the contains() method for an industry that is present.
     */
    @Test
    void testContainsWhenExists() {
        repository.save(industry2);
        assertTrue(repository.contains(industry2));
    }

    /**
     * Tests the contains() method for an industry that is not present.
     */
    @Test
    void testContainsWhenNotExists() {
        assertFalse(repository.contains(industry2));
    }

    /**
     * Tests that findAll() returns all saved industries.
     */
    @Test
    void testFindAllReturnsAllIndustries() {
        repository.save(industry1);
        repository.save(industry2);

        List<Industry> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(industry1));
        assertTrue(all.contains(industry2));
    }

    /**
     * Tests that findAll() returns an empty list when nothing is added.
     */
    @Test
    void testFindAllEmptyWhenNoneAdded() {
        List<Industry> all = repository.findAll();
        assertNotNull(all);
        assertTrue(all.isEmpty());
    }

    /**
     * Ensures that modifying the list returned by findAll() does not affect the repository.
     */
    @Test
    void testFindAllReturnsDefensiveCopy() {
        repository.save(industry1);
        List<Industry> all = repository.findAll();
        all.clear(); // modify the copy
        assertEquals(1, repository.findAll().size()); // repository remains unchanged
    }
}
