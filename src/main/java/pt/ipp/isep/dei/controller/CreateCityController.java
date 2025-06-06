package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.CityRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

/**
 * Controller class responsible for managing the creation of cities.
 *
 * <p>The {@code CreateCityController} serves as the intermediary layer between the user interface
 * and the application's domain logic related to cities. Its main responsibilities include:
 * <ul>
 *     <li>Handling the creation of a new city and validating any constraints (e.g., unique names,
 *     valid positions).</li>
 *     <li>Retrieving the list of available maps for city placement.</li>
 *     <li>Fetching all existing cities.</li>
 * </ul>
 * This controller interacts with repositories to persist and retrieve data and with domain
 * classes to perform city-specific operations, such as manual or automatic creation of house blocks.</p>
 */
public class CreateCityController {

    /**
     * Repository responsible for managing city-related data.
     * <p>The {@link CityRepository} is used to check for existing cities, validate positioning,
     * and save new cities in the persistence layer.</p>
     */
    private final CityRepository cityRepository;

    /**
     * Repository for accessing and managing maps.
     */
    private MapRepository mapRepository;

    /**
     * Constructs an instance of {@code CreateCityController}.
     *
     * <p>This constructor initializes the city repository through the singleton instance of
     * {@link Repositories}.</p>
     */
    public CreateCityController() {
        this.cityRepository = Repositories.getInstance().getCityRepository();
        this.mapRepository = Repositories.getInstance().getMapRepository();
    }




    /**
     * Retrieves a list of all available maps for city creation.
     *
     * <p>This method communicates with the map repository to fetch the available map objects,
     * which will guide the placement and validation of cities and their house blocks.</p>
     *
     * @return A {@link List} of {@link Map} objects, representing all available maps in the system.
     */
    public List<Map> getMaps() {
        return Repositories.getInstance().getMapRepository().getMaps();
    }

    /**
     * Creates a new city with the specified attributes.
     *
     * <p>This method handles the entire workflow of creating a city, including validation of the
     * city's name and position for uniqueness. It supports both manual and automatic assignment
     * of house blocks. If creation succeeds, the city is persisted in the repository.</p>
     *
     * <p>The following validations are performed prior to city creation:
     * <ul>
     *     <li>The city's name must not already exist.</li>
     *     <li>The position provided must not conflict with any other city's position.</li>
     * </ul>
     * Depending on the value of the {@code manual} parameter, either manual or automatic assignment
     * of house blocks is performed.</p>
     *
     * @param name           The name of the city. It must be unique.
     * @param position       The {@link Position} object representing the location of the city on the map.
     * @param houseBlocks    The total number of house blocks in the city.
     * @param manual         {@code true} if house blocks should be assigned manually; {@code false} for automatic assignment.
     * @param manualPositions A {@link List} of {@link Position} objects representing the manually assigned positions
     *                        for the house blocks (only used if {@code manual} is {@code true}).
     * @return The newly created {@link City} object.
     * @throws IllegalArgumentException If:
     *                                  <ul>
     *                                      <li>A city with the given name already exists.</li>
     *                                      <li>The provided position is already occupied by another city.</li>
     *                                      <li>Manual positions are invalid or conflict with other house blocks
     *                                      (only when {@code manual} is {@code true}).</li>
     *                                  </ul>
     */
    public City createCity(String name, Position position, int houseBlocks, boolean manual, List<Position> manualPositions) {

        Map map = mapRepository.getActiveMap();

        if (position.getX() >= map.getWidth() || position.getY() >= map.getHeight()) {
            throw new IllegalArgumentException("City position is outside the map boundaries.");
        }

        if (cityRepository.cityExists(name)) {
            throw new IllegalArgumentException("City with that name already exists.");
        }

        if (cityRepository.positionInvalid(position)) {
            throw new IllegalArgumentException("Position is already taken by another city.");
        }

        City city = new City(name, position, houseBlocks);

        if (manual) {
            city.addHouseBlocksManually(manualPositions, map);
        } else {
            city.generateHouseBlocksAutomatically(map);
        }
        map.addCity(city);
        cityRepository.save(city);
        return city;
    }

    /**
     * Retrieves the list of all cities in the system.
     *
     * <p>This method fetches all the city objects stored in the persistence layer,
     * which can be used for display or further operations.</p>
     *
     * @return A {@link List} of {@link City} objects representing all the cities.
     */
    public List<City> getCities() {
        return cityRepository.getCities();
    }

    public boolean cityExists(String name) {
        return cityRepository.cityExists(name);
    }

}