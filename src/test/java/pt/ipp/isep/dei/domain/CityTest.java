
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
     * Tests the creation of a {@link City} object using a valid constructor.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>The city's name is correctly set to the provided value.</li>
     *     <li>The city's position is correctly set to the provided position.</li>
     *     <li>The number of house blocks is correctly set to the provided value.</li>
     *     <li>The list of house blocks is initially empty.</li>
     * </ul>
     *
     * @throws AssertionError if any of the properties of the {@link City} object
     * do not match the expected values.
     */

    @Test
    void testValidConstructor() {
        City city = new City("Lisbon", position, 3);
        assertEquals("Lisbon", city.getName());
        assertEquals(position, city.getPosition());
        assertEquals(3, city.getHouseBlocks());
        assertTrue(city.getHouseBlocksList().isEmpty());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city name contains numbers
     * or special characters.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the city name contains invalid characters.
     */
    @Test
    void testInvalidNameInConstructor_NumbersAndSpecialCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("Porto123!", position, 2));
        assertEquals("City name must not be empty or null and it only can contain letters and spaces.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city name is an empty string.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the city name is an empty string.
     */
    @Test
    void testInvalidNameInConstructor_EmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("", new Position(1, 1), 3));
        assertEquals("City name must not be empty or null and it only can contain letters and spaces.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city name is {@code null}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the city name is {@code null}.
     */
    @Test
    void testInvalidNameInConstructor_NullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City(null, new Position(1, 1), 3));
        assertEquals("City name must not be empty or null and it only can contain letters and spaces.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city position has an invalid X coordinate (negative value).
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the X coordinate of the city position is negative.
     */
    @Test
    void testInvalidPositionXNegativeInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("Porto", new Position(-1, 3), 2));
        assertEquals("City position must be valid and positive.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city position has an invalid Y coordinate (negative value).
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the Y coordinate of the city position is negative.
     */
    @Test
    void testInvalidPositionYNegativeInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("Porto", new Position(1, -3), 2));
        assertEquals("City position must be valid and positive.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the city position is null.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the city position is null.
     */
    @Test
    void testInvalidPositionNullInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("Porto", null, 2));
        assertEquals("City position must be valid and positive.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#City(String, Position, int)} constructor throws an
     * {@link IllegalArgumentException} when the number of house blocks is not positive.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message must match the expected message.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the number of house blocks is zero or negative.
     */
    @Test
    void testInvalidHouseBlocksInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new City("Valongo", position, 0));
        assertEquals("Number of house blocks must be positive.", exception.getMessage());
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
     * Tests that the {@link City#addHouseBlocksManually(List)} method throws an
     * {@link IllegalArgumentException} when the size of the provided list of positions
     * does not match the number of house blocks required by the city.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message indicates that the number of positions does not match
     *         the house block count.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the provided list of positions has an incorrect size.
     */
    @Test
    void testAddHouseBlocksManually_InvalidSize() {
        City city = new City("Coimbra", position, 3);
        List<Position> wrongList = Collections.singletonList(new Position(1, 1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> city.addHouseBlocksManually(wrongList));
        assertEquals("The number of house block positions must match the house block count.", exception.getMessage());
    }

    /**
     * Tests that the {@link City#addHouseBlocksManually(List)} method throws an
     * {@link IllegalArgumentException} when the provided list of positions is {@code null}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>An exception is thrown.</li>
     *     <li>The exception message indicates that the number of positions must match
     *         the house block count, even if the positions are invalid (e.g., null).</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the provided list of positions is {@code null}.
     */
    @Test
    void testAddHouseBlocksManually_NullPositions() {
        City city = new City("Braga", new Position(2, 2), 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> city.addHouseBlocksManually(null));
        assertEquals("The number of house block positions must match the house block count.", exception.getMessage());
    }

    /**
     * Tests generating house blocks automatically based on the city's position and the map's boundaries.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>The city generates the correct number of house blocks.</li>
     *     <li>Each house block is placed within the map's boundaries.</li>
     *     <li>No house block overlaps the city's main position.</li>
     * </ul>
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
     * Tests that the string representation of a {@link City} object contains all relevant details.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>The string includes the city's name.</li>
     *     <li>The string includes the city's position.</li>
     *     <li>The string includes the number of house blocks and their details.</li>
     * </ul>
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
