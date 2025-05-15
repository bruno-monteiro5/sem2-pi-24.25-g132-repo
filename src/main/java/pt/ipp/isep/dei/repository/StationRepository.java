package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing all stations in the system.
 */
public class StationRepository {
    private final List<Station> stations = new ArrayList<>();

    /**
     * Checks if a station is valid at a global level (e.g., does not overlap existing stations).
     * @param station the station to validate
     * @return true if the station is valid, false otherwise
     */
    public boolean validateStationGlobally(Station station) {
        for (Station s : stations) {
            if (s.getLocation().equals(station.getLocation())) {
                return false; // Cannot place two stations at the same location
            }
        }
        return true;
    }

    /**
     * Saves a new station to the repository.
     * @param station the station to save
     */
    public void save(Station station) {
        stations.add(station);
    }

    /**
     * Gets the list of all saved stations.
     * @return list of stations
     */
    public List<Station> getAllStations() {
        return new ArrayList<>(stations); // Defensive copy
    }
}
