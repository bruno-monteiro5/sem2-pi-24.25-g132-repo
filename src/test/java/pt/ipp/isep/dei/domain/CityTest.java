
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link City}.
 *
 * <p>The {@code CityTest} class contains unit tests for the {@link City} class, ensuring its
 * methods and constructors behave as expected. The tests include:
 * <ul>
 *     <li>Validating correct behavior with proper inputs.</li>
 *     <li>Ensuring exceptions are thrown for invalid inputs.</li>
 *     <li>Testing key functionalities like adding house blocks manually or automatically.</li>
 *     <li>Testing the string representation of a City object.</li>
 * </ul>
 * </p>
 *
 * <p>The tests use {@link BeforeEach} to set up common test data and various assertions to validate
 * the expected behavior of the {@link City} class.</p>
 */
class CityTest {

    /**
     * The position of the city for use in the tests.
     *
     * <p>This attribute is initialized before each test and represents a valid
     * {@link Position} object to be used in city creation and other tests.</p>
     */
    private Position position;

    /**
     * The map object used for boundary and positional validation when testing
     * house block generation within the {@link City}.
     *
     * <p>This map is initialized with a size of 20x20 and represents a valid configuration
     * for testing the placement and generation of house blocks.</p>
     */
    private Map map;

    /**
     * Sets up common test data before each test.
     *
     * <p>This method is executed before every test and initializes the {@link Position}
     * and {@link Map} objects with default, valid configurations to be used
     * in the unit tests.</p>
     */
    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        map = new Map("testmap", 20, 20, "test@example.com");
    }

    /**
     * Tests the creation of a City using a valid constructor.
     *
     * <p>Asserts that the created {@link City} has the correct name, position,
     * number of house blocks, and an empty list of house blocks.</p>
     */
    @Test
    void testValidConstructor() {
        City city = new City("Lisboa", position, 3);
        assertEquals("Lisboa", city.getName());
        assertEquals(position, city.getPosition());
        assertEquals(3, city.getHouseBlocks());
        assertTrue(city.getHouseBlocksList().isEmpty());
    }

    /**
     * Tests the constructor of {@link City} with an invalid name.
     *
     * <p>Asserts that an {@link IllegalArgumentException} is thrown when the city name
     * contains invalid characters (e.g., digits or special symbols).</p>
     */
    @Test
    void testInvalidNameInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new City("Porto123!", position, 2));
    }

    /**
     * Tests the constructor of {@link City} with an invalid position.
     *
     * <p>Asserts that an {@link IllegalArgumentException} is thrown when the
     * position is null or falls outside the valid boundaries.</p>
     */
    @Test
    void testInvalidPositionInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new City("Porto", new Position(-1, 3), 2));
        assertThrows(IllegalArgumentException.class, () -> new City("Porto", null, 2));
    }

    /**
     * Tests the constructor of {@link City} with an invalid number of house blocks.
     *
     * <p>Asserts that an {@link IllegalArgumentException} is thrown when
     * the given number of house blocks is less than or equal to zero.</p>
     */
    @Test
    void testInvalidHouseBlocksInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new City("Valongo", position, 0));
    }

    /**
     * Tests adding house blocks manually with valid positions.
     *
     * <p>Asserts that the list of house blocks is updated correctly when providing
     * valid positions for manual block placement.</p>
     */
    @Test
    void testAddHouseBlocksManually_Valid() {
        City city = new City("Porto", position, 2);
        List<Position> positions = Arrays.asList(new Position(1, 1), new Position(2, 2));
        city.addHouseBlocksManually(positions);

        List<HouseBlock> blocks = city.getHouseBlocksList();
        assertEquals(2, blocks.size());
        assertEquals(1, blocks.get(0).getPosition().getX());
        assertEquals(2, blocks.get(1).getPosition().getX());
    }

    /**
     * Tests adding house blocks manually with an invalid size.
     *
     * <p>Asserts that an {@link IllegalArgumentException} is thrown when the size of
     * the provided list of positions does not match the expected number of house blocks.</p>
     */
    @Test
    void testAddHouseBlocksManually_InvalidSize() {
        City city = new City("Coimbra", position, 3);
        List<Position> wrongList = Collections.singletonList(new Position(1, 1));
        assertThrows(IllegalArgumentException.class, () -> city.addHouseBlocksManually(wrongList));
    }

    /**
     * Tests generating house blocks automatically.
     *
     * <p>Asserts that the method creates the correct number of house blocks at positions
     * within the map's boundaries and avoids overlapping the city's main position.</p>
     */
    @Test
    void testGenerateHouseBlocksAutomatically() {
        City city = new City("New York", position, 5);
        city.generateHouseBlocksAutomatically(map);

        List<HouseBlock> blocks = city.getHouseBlocksList();
        assertEquals(5, blocks.size());

        for (HouseBlock block : blocks) {
            Position pos = block.getPosition();
            assertTrue(map.isInsideBounds(pos));
            assertNotEquals(position, pos);
        }
    }

    /**
     * Tests the string representation of a city.
     *
     * <p>Asserts that the {@link City#toString()} includes all relevant city details,
     * such as the name, position, and house block information.</p>
     */
    @Test
    void testToStringIncludesAllDetails() {
        City city = new City("Los Angeles", position, 1);
        city.addHouseBlocksManually(List.of(new Position(2, 3)));
        String str = city.toString();
        assertTrue(str.contains("Los Angeles"));
        assertTrue(str.contains("position=[5, 5]"));
        assertTrue(str.contains("houseBlocks=1"));
        assertTrue(str.contains("HouseBlock"));
    }
}
