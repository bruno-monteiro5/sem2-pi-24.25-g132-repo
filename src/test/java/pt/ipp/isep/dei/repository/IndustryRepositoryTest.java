package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryTypes;
import pt.ipp.isep.dei.domain.Position;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the IndustryRepository class.
 */
public class IndustryRepositoryTest {

    /**
     * Test saving an industry in the repository.
     */
    @Test
    public void testSaveIndustry() {
        IndustryRepository repository = new IndustryRepository();
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);

        repository.save(industry);

        assertTrue(repository.industryExists("Industry"));
    }

    /**
     * Test that industryExists returns true for an existing name.
     */
    @Test
    public void testIndustryExistsTrue() {
        IndustryRepository repository = new IndustryRepository();
        repository.save(new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY));

        assertTrue(repository.industryExists("Industry"));
    }

    /**
     * Test that industryExists returns false for a non-existing name.
     */
    @Test
    public void testIndustryExistsFalse() {
        IndustryRepository repository = new IndustryRepository();
        repository.save(new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY));

        assertFalse(repository.industryExists("NoIndustry"));
    }

    /**
     * Test that isPositionInvalid returns true if the position is already occupied.
     */
    @Test
    public void testIsPositionInvalidTrue() {
        IndustryRepository repository = new IndustryRepository();
        Position position = new Position(10, 20);
        repository.save(new Industry("Industry", position, IndustryTypes.IndustryType.PRIMARY));

        assertTrue(repository.isPositionInvalid(position));
    }

    /**
     * Test that isPositionInvalid returns false if the position is not occupied.
     */
    @Test
    public void testIsPositionInvalidFalse() {
        IndustryRepository repository = new IndustryRepository();
        Position position = new Position(10, 20);
        repository.save(new Industry("Industry", position, IndustryTypes.IndustryType.PRIMARY));

        assertFalse(repository.isPositionInvalid(new Position(5, 5)));
    }
}

