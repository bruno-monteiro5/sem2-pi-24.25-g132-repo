package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.StationDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing detailed information of stations.
 */
public class StationDetailsRepository {

    private static StationDetailsRepository instance;
    private final List<StationDetails> stationDetailsList;

    /**
     * Private constructor for singleton pattern.
     */
    private StationDetailsRepository() {
        this.stationDetailsList = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the StationDetailsRepository.
     *
     * @return The instance of the repository.
     */
    public static StationDetailsRepository getInstance() {
        if (instance == null) {
            instance = new StationDetailsRepository();
        }
        return instance;
    }

    /**
     * Adds a new StationDetails object to the repository.
     *
     * @param stationDetails The StationDetails object to add.
     */
    public void addStationDetails(StationDetails stationDetails) {
        this.stationDetailsList.add(stationDetails);
    }

    /**
     * Retrieves all station details.
     *
     * @return A list of all StationDetails objects.
     */
    public List<StationDetails> getAllStationDetails() {
        return new ArrayList<>(this.stationDetailsList);
    }

    /**
     * Finds the details of a station by its name.
     *
     * @param stationName The name of the station.
     * @return The StationDetails object, or null if not found.
     */
    public StationDetails getStationDetailsByName(String stationName) {
        for (StationDetails details : stationDetailsList) {
            if (details.getStationName().equalsIgnoreCase(stationName)) {
                return details;
            }
        }
        return null;
    }

    /**
     * Removes the details of a station by its name.
     *
     * @param stationName The name of the station to remove details for.
     * @return True if successfully removed, false otherwise.
     */
    public boolean removeStationDetailsByName(String stationName) {
        return this.stationDetailsList.removeIf(details -> details.getStationName().equalsIgnoreCase(stationName));
    }
}