package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing {@link City} entities in memory.
 *
 * <p>The {@code CityRepository} class acts as a storage mechanism for {@link City} objects.
 * It provides methods for adding, retrieving, and validating city entities, ensuring that
 * the following constraints are upheld:
 * <ul>
 *     <li>Each city must have a unique name (case-insensitive).</li>
 *     <li>Each city must occupy a unique position on the map.</li>
 * </ul>
 *
 * This repository is a key component in ensuring data integrity and providing city-related
 * data for the application's domain and business logic.</p>
 */
public class CityRepository {

    /**
     * List of all {@link City} objects stored in the repository.
     *
     * <p>This list acts as the storage mechanism for saved city entities, simulating an
     * in-memory database.</p>
     */
    private List<City> cities = new ArrayList<>();

    /**
     * Retrieves the list of all cities stored in the repository.
     *
     * @return A {@link List} of {@link City} objects representing all the stored cities.
     */
    public List<City> getCities() {
        return cities;
    }


    /**
     * Checks if a city with the given name already exists in the repository (case-insensitive).
     *
     * <p>This method iterates through the stored {@link City} objects and performs a
     * case-insensitive comparison of the names to determine uniqueness.</p>
     *
     * @param name The name of the city to check.
     * @return {@code true} if a city with the given name exists, {@code false} otherwise.
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
     * <p>This method adds the provided {@link City} object to the list of stored cities.
     * It assumes that the city has already been validated for uniqueness (e.g., name and position).</p>
     *
     * @param city The {@link City} object to add to the repository.
     */
    public void save(City city) {
        cities.add(city);
    }

    /**
     * Checks if the given position is already occupied by another city in the repository.
     *
     * <p>This method iterates through the stored {@link City} objects and compares their
     * positions with the provided {@link Position} object to ensure there are no conflicts.</p>
     *
     * @param position The {@link Position} to check for conflicts.
     * @return {@code true} if the position is already used by another city, {@code false} otherwise.
     */
    public boolean positionInvalid(Position position) {
        for (City city : cities) {
            if (city.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

}
