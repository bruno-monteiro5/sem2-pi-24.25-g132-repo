package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing stations in the system.
 *
 * <p>This class provides functionalities to manage a collection of stations,
 * allowing the addition of new stations, retrieval of all stations, and
 * search for a station by its name.</p>
 */
public class StationRepository {

    private static StationRepository instance;
    private final List<Station> stations;

    /**
     * Constructs an empty station repository.
     */
    public StationRepository() {
        this.stations = new ArrayList<>();
    }

    /**
     * Singleton access to the StationRepository instance.
     *
     * @return the singleton instance.
     */
    public static StationRepository getInstance() {
        if (instance == null) {
            instance = new StationRepository();
        }
        return instance;
    }

    /**
     * Adds a new station to the repository.
     *
     * @param station the station to add.
     */
    public void addStation(Station station) {
        this.stations.add(station);
    }

    /**
     * Retrieves all stations in the repository.
     *
     * @return a list of all stations.
     */
    public List<Station> getAll() {
        return new ArrayList<>(stations); // defensivo
    }

    /**
     * Retrieves a station by its name.
     *
     * @param name the name of the station to retrieve.
     * @return the matching station if found, or {@code null} otherwise.
     */
    public Station getStationByName(String name) {
        for (Station s : this.stations) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
