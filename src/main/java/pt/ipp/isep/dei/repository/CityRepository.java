package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;


/**
 * Repository for managing City entities in memory.
 * <p>
 * Provides methods to add, retrieve, and validate cities,
 * including checks for name uniqueness and position conflicts.
 * </p>
 */
public class CityRepository {
    private List<City> cities = new ArrayList<>();


    /**
     * Retrieves the list of all cities.
     *
     * @return List of cities.
     */
    public List<City> getCities() {
        return cities;
    }


    /**
     * Checks if a city with the given name already exists (case-insensitive).
     *
     * @param name The name of the city to check.
     * @return true if a city with the given name exists, false otherwise.
     */
    public boolean cityExists(String name) {
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Saves a new city to the repository.
     *
     * @param city The city to add.
     */
    public void save(City city) {
        cities.add(city);
    }

    /**
     * Checks if the given position is already occupied by another city.
     *
     * @param position The position to check.
     * @return true if the position is already used, false otherwise.
     */
    public boolean positionInvalid(Position position) {
        for (City city : cities) {
            if (city.getPosition().equals(position)) {
                return true; // Conflict with an existing city's position
            }
        }
        return false;
    }

}
