package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CityRepository class.
 */
class CityRepositoryTest {

    /**
     * Tests saving and retrieving cities.
     */
    @Test
    void testSaveAndGetCities() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map1", 10, 10, "editor@this.app");
        City city = new City(map, "Porto", new Position(1, 1), 2);
        repository.save(city);
        List<City> cities = repository.getCities();
        assertEquals(1, cities.size());
        assertEquals("Porto", cities.get(0).getName());
    }

    /**
     * Tests cityExists returns true for existing city (case-insensitive).
     */
    @Test
    void testCityExists() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map2", 10, 10, "editor@this.app");
        City city = new City(map, "Lisboa", new Position(2, 2), 3);
        repository.save(city);
        assertTrue(repository.cityExists("Lisboa"));
        assertTrue(repository.cityExists("lisboa"));
        assertFalse(repository.cityExists("Porto"));
    }

    /**
     * Tests positionInvalid returns true if position is already used.
     */
    @Test
    void testPositionInvalid() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map3", 10, 10, "editor@this.app");
        Position pos = new Position(3, 3);
        City city = new City(map, "Valongo", pos, 1);
        repository.save(city);
        assertTrue(repository.positionInvalid(new Position(3, 3)));
        assertFalse(repository.positionInvalid(new Position(4, 4)));
    }
}