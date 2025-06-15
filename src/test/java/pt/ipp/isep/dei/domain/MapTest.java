package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Map} class.
 *
 * This class checks that the Map is created correctly, handles validation,
 * and allows adding and managing entities like cities and industries.
 */
public class MapTest {

    /**
     * Tests creation of a valid map.
     *
     * Checks if the map stores its name, width, height, and scale correctly.
     */
    @Test
    public void testValidMapCreation() {
        Map map = new Map("Map01", 100, 100, 1);
        assertEquals("Map01", map.getName());
        assertEquals(100, map.getWidth());
        assertEquals(100, map.getHeight());
        assertEquals(1, map.getScale());
    }

    /**
     * Tests creation of a map with invalid dimensions.
     *
     * Expects an exception because dimensions are below the minimum allowed.
     */
    @Test
    public void testInvalidMapDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Map("Map02", 20, 20, 1));
    }

    /**
     * Tests creation of a map with an invalid name.
     *
     * Expects an exception because the name contains spaces and punctuation.
     */
    @Test
    public void testInvalidMapName() {
        assertThrows(IllegalArgumentException.class, () -> new Map("Invalid Name!", 100, 100, 1));
    }

    /**
     * Tests static validation methods.
     *
     * Confirms that the helper methods for name and dimensions work correctly.
     */
    @Test
    public void testStaticValidationMethods() {
        assertTrue(Map.isDimensionValid(60, 60));
        assertFalse(Map.isDimensionValid(10, 10));

        assertTrue(Map.isValidMapName("Valid_Name123"));
        assertFalse(Map.isValidMapName("Invalid Name!"));
    }

    /**
     * Tests adding a city to the map.
     *
     * Ensures the city is added and its position is marked as occupied.
     */
    @Test
    public void testAddCity() {
        Map map = new Map("MapCity", 100, 100, 1);
        City city = new City("Porto", new Position(10, 10), 2);
        map.addCity(city);

        List<City> cities = map.getCities();
        assertEquals(1, cities.size());
        assertEquals(city, cities.get(0));

        List<Position> positions = map.getOcupiedPositions();
        assertEquals(1, positions.size());
        assertEquals(city.getPosition(), positions.get(0));
    }

    /**
     * Tests adding a valid industry.
     *
     * Confirms that the industry and its position are added properly.
     */
    @Test
    public void testAddIndustry() {
        Map map = new Map("MapInd", 100, 100, 1);
        Industry ind1 = new Industry("Factory", new Position(5, 5), IndustryType.PRIMARY);
        map.addIndustry(ind1);

        assertEquals(1, map.getIndustries().size());
        assertEquals(ind1, map.getIndustries().get(0));
        assertEquals(1, map.getOcupiedPositions().size());
        assertEquals(ind1.getPosition(), map.getOcupiedPositions().get(0));
    }

    /**
     * Tests adding industries with invalid data.
     *
     * Checks for null input, null position, and duplicate position.
     */
    @Test
    public void testAddIndustryInvalid() {
        Map map = new Map("MapIndInvalid", 100, 100, 1);
        assertThrows(IllegalArgumentException.class, () -> map.addIndustry(null));
        assertThrows(IllegalArgumentException.class, () -> map.addIndustry(new Industry("X", null, IndustryType.PRIMARY)));

        Industry ind1 = new Industry("Factory", new Position(5, 5), IndustryType.PRIMARY);
        map.addIndustry(ind1);
        Industry ind2 = new Industry("Another", new Position(5, 5), IndustryType.PRIMARY);
        assertThrows(IllegalArgumentException.class, () -> map.addIndustry(ind2));
    }

    /**
     * Tests creation and validation of a position.
     *
     * Adds an industry to a valid position and checks that
     * the same position becomes invalid afterwards.
     */
    @Test
    public void testCreateAndValidatePosition() {
        Map map = new Map("MapPos", 100, 100, 1);
        Position pos = map.createPosition(20, 20);
        assertTrue(map.validatePosition(pos));

        Industry ind = new Industry("F", pos, IndustryType.PRIMARY);
        map.addIndustry(ind);
        assertFalse(map.validatePosition(pos));
    }

    /**
     * Tests getting scenarios and toString method.
     *
     * Ensures scenarios list is initially empty and toString includes map name.
     */
    @Test
    public void testGetScenariosAndToString() {
        Map map = new Map("MapStr", 100, 100, 1);
        assertTrue(map.getScenarios().isEmpty());

        String output = map.toString();
        assertTrue(output.contains("Map{"));
        assertTrue(output.contains("MapStr"));
    }
}
