package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.BuildingType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BuildingTypeRepository} class.
 * Ensures 100% coverage of class, methods, and lines.
 */
class BuildingTypeRepositoryTest {

    private BuildingTypeRepository repository;

    /**
     * Initializes a new instance of {@link BuildingTypeRepository} before each test.
     */
    @BeforeEach
    void setUp() {
        repository = new BuildingTypeRepository();
    }

    /**
     * Tests if {@code getAllBuildingTypes()} returns a list containing all {@link BuildingType} values.
     */
    @Test
    void testGetAllBuildingTypes() {
        List<BuildingType> types = repository.getAllBuildingTypes();
        assertNotNull(types, "The list of building types should not be null");
        assertEquals(BuildingType.values().length, types.size(),
                "The list should contain all available BuildingType values");
        for (BuildingType type : BuildingType.values()) {
            assertTrue(types.contains(type), "The list should contain: " + type);
        }
    }

    /**
     * Tests if {@code getBuildingTypeDetails()} returns the same {@link BuildingType} passed as input.
     */
    @Test
    void testGetBuildingTypeDetails() {
        for (BuildingType type : BuildingType.values()) {
            BuildingType result = repository.getBuildingTypeDetails(type);
            assertEquals(type, result, "Expected the same BuildingType to be returned");
        }
    }
}
