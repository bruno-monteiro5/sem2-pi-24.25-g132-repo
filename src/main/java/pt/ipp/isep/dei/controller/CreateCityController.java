package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.CityRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

/**
 * Controller responsible for handling the creation of cities.
 * <p>
 * Provides methods to retrieve available maps, create new cities (with manual or automatic house block assignment),
 * and list existing cities. Interacts with the repositories to persist and validate city data.
 * </p>
 */
public class CreateCityController {

    private CityRepository repository;
    private MapRepository mapRepository;

    /**
     * Constructs a new CreateCityController instance.
     * Initializes the city and map repositories for data access.
     */
    public CreateCityController() {
        this.repository = Repositories.getInstance().getCityRepository();
        this.mapRepository = Repositories.getInstance().getMapRepository();
    }

    /**
     * Retrieves the list of available maps.
     *
     * @return List of maps.
     */
    public List<Map> getMaps() {
        return mapRepository.getMaps();
    }

    /**
     * Creates a new city with the specified parameters.
     * <p>
     * Validates the input data, including city name, position, and house block count.
     * If the house block count is -1, it generates a random number of house blocks.
     * </p>
     *
     * @param mapa        The map where the city will be created.
     * @param name        The name of the city.
     * @param position    The position of the city on the map.
     * @param houseblocks The number of house blocks (or -1 for automatic generation).
     * @return The created city.
     */
    public City createCity(Map mapa, String name, Position position, int houseblocks) {
        if (repository.cityExists(name)) {
            throw new IllegalArgumentException("City with that name already exists.");
        }

        if (repository.positionInvalid(position)) {
            throw new IllegalArgumentException("Position is invalid.");
        }

        if (houseblocks != -1) {  // Não é geração automática
            if (houseblocks <= 0) {
                throw new IllegalArgumentException("Número de house blocks deve ser positivo.");
            }

            City city = new City(mapa, name, position, houseblocks);
            repository.save(city);
            return city;

        } else {
            City city = new City(mapa, name, position, 1);
            city.generateRandomHouseBlocks();

            repository.save(city);
            return city;
        }

    }

    /**
     * Retrieves the list of existing cities.
     *
     * @return List of cities.
     */
    public List<City> getCities() {
        return repository.getCities();
    }
}
