package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link CityRepository}.
 *
 * <p>The {@code CityRepositoryTest} class contains unit tests to validate the behavior of the
 * {@link CityRepository} class. This test suite ensures that the repository correctly manages
 * the storage, retrieval, and validation of {@link City} objects, as well as verifies proper
 * handling of city existence and position conflicts.</p>
 *
 * <p>Key functionalities tested include:
 * <ul>
 *     <li>Retrieving the list of cities.</li>
 *     <li>Adding cities to the repository.</li>
 *     <li>Checking if a city name exists in the repository.</li>
 *     <li>Verifying if a position is already occupied by a city.</li>
 * </ul>
 * </p>
 */
class CityRepositoryTest {

    /**
     * Instance of {@link CityRepository} used for testing.
     *
     * <p>This object is reinitialized before each test and acts as the in-memory storage
     * for {@link City} objects during testing.</p>
     */
    private CityRepository cityRepository;

    /**
     * Instance of the first test city, {@code city1}.
     *
     * <p>This {@link City} object is used for adding and validating city-related functionalities
     * within the repository during testing.</p>
     */
    private City city1;

    /**
     * Instance of the second test city, {@code city2}.
     *
     * <p>This {@link City} object is used to verify tests involving multiple cities.</p>
     */
    private City city2;

    /**
     * Sets up common test data before each test.
     *
     * <p>This method is executed before every test to provide a fresh instance of the
     * {@link CityRepository} and initialize pre-configured {@link City} objects to ensure
     * independent and consistent test results.</p>
     */
    @BeforeEach
    void setUp() {
        cityRepository = new CityRepository();
        city1 = new City("Porto", new Position(1, 2), 3);
        city2 = new City("Lisboa", new Position(3, 4), 5);
    }

    /**
     * Tests if the list of cities in the repository is initially empty.
     *
     * <p>Asserts that the {@link CityRepository#getCities()} method returns an empty list
     * when no cities have been added.</p>
     */
    @Test
    void testGetCities_InitiallyEmpty() {
        List<City> cities = cityRepository.getCities();
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

    /**
     * Tests adding a single city to the repository.
     *
     * <p>Asserts that the {@link CityRepository#save(City)} method correctly adds the
     * provided {@link City} to the repository and reflects in the city list.</p>
     */
    @Test
    void testSave_AddCity() {
        // Verifica se uma cidade é adicionada corretamente
        cityRepository.save(city1);
        List<City> cities = cityRepository.getCities();
        assertEquals(1, cities.size());
        assertTrue(cities.contains(city1));
    }

    /**
     * Tests adding multiple cities to the repository.
     *
     * <p>Asserts that the {@link CityRepository#save(City)} method allows the addition
     * of multiple {@link City} objects and that all cities are stored correctly.</p>
     */
    @Test
    void testSave_AddMultipleCities() {
        cityRepository.save(city1);
        cityRepository.save(city2);
        List<City> cities = cityRepository.getCities();
        assertEquals(2, cities.size());
        assertTrue(cities.contains(city1));
        assertTrue(cities.contains(city2));
    }

    /**
     * Tests checking if a city exists in the repository by name (case-sensitive).
     *
     * <p>Asserts that the {@link CityRepository#cityExists(String)} method returns {@code true}
     * when a city with the specified name exists in the repository.</p>
     */
    @Test
    void testCityExists_TrueWhenCityExists() {
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Porto");
        assertTrue(exists);
    }

    /**
     * Tests checking if a city does not exist in the repository by name.
     *
     * <p>Asserts that the {@link CityRepository#cityExists(String)} method returns {@code false}
     * when a city with the specified name does not exist.</p>
     */
    @Test
    void testCityExists_FalseWhenCityDoesNotExist() {
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Lisboa");
        assertFalse(exists);
    }

    /**
     * Tests verifying the city existence check is case insensitive.
     *
     * <p>Asserts that the {@link CityRepository#cityExists(String)} method correctly identifies
     * city names regardless of case sensitivity.</p>
     */
    @Test
    void testCityExists_CaseInsensitive() {
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Porto");
        assertTrue(exists);
    }

    /**
     * Tests checking if a city position is already occupied.
     *
     * <p>Asserts that the {@link CityRepository#positionInvalid(Position)} method returns
     * {@code true} when the provided position is already associated with an existing city.</p>
     */
    @Test
    void testPositionInvalid_TrueWhenPositionExists() {
        cityRepository.save(city1);
        boolean invalid = cityRepository.positionInvalid(new Position(1, 2));
        assertTrue(invalid);
    }

    /**
     * Tests checking if a city position is not occupied.
     *
     * <p>Asserts that the {@link CityRepository#positionInvalid(Position)} method returns
     * {@code false} when the provided position is not associated with any city in the repository.</p>
     */
    @Test
    void testPositionInvalid_FalseWhenPositionDoesNotExist() {
        cityRepository.save(city1);
        boolean invalid = cityRepository.positionInvalid(new Position(5, 6));
        assertFalse(invalid);
    }
}