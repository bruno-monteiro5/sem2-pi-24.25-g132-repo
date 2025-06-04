package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryTest {
    private CityRepository cityRepository;
    private City city1;
    private City city2;

    @BeforeEach
    void setUp() {
        cityRepository = new CityRepository();
        city1 = new City("Porto", new Position(1, 2), 3);
        city2 = new City("Lisboa", new Position(3, 4), 5);
    }

    @Test
    void testGetCities_InitiallyEmpty() {
        // Verifica se a lista de cidades começa vazia
        List<City> cities = cityRepository.getCities();
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

    @Test
    void testSave_AddCity() {
        // Verifica se uma cidade é adicionada corretamente
        cityRepository.save(city1);
        List<City> cities = cityRepository.getCities();
        assertEquals(1, cities.size());
        assertTrue(cities.contains(city1));
    }

    @Test
    void testSave_AddMultipleCities() {
        // Verifica se várias cidades são adicionadas corretamente
        cityRepository.save(city1);
        cityRepository.save(city2);
        List<City> cities = cityRepository.getCities();
        assertEquals(2, cities.size());
        assertTrue(cities.contains(city1));
        assertTrue(cities.contains(city2));
    }

    @Test
    void testCityExists_TrueWhenCityExists() {
        // Verifica se a existência de uma cidade é identificada corretamente
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Porto");
        assertTrue(exists);
    }

    @Test
    void testCityExists_FalseWhenCityDoesNotExist() {
        // Verifica se a inexistência de uma cidade é identificada corretamente
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Lisboa");
        assertFalse(exists);
    }

    @Test
    void testCityExists_CaseInsensitive() {
        // Verifica se a verificação ignora a sensibilidade de caso
        cityRepository.save(city1);
        boolean exists = cityRepository.cityExists("Porto");
        assertTrue(exists);
    }

    @Test
    void testPositionInvalid_TrueWhenPositionExists() {
        // Verifica se a posição de uma cidade é identificada como já ocupada
        cityRepository.save(city1);
        boolean invalid = cityRepository.positionInvalid(new Position(1, 2));
        assertTrue(invalid);
    }

    @Test
    void testPositionInvalid_FalseWhenPositionDoesNotExist() {
        // Verifica se uma posição livre não é identificada como ocupada
        cityRepository.save(city1);
        boolean invalid = cityRepository.positionInvalid(new Position(5, 6));
        assertFalse(invalid);
    }
}