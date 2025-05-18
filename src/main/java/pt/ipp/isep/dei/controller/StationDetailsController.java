package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.StationDetails;
import pt.ipp.isep.dei.repository.StationDetailsRepository;

import java.util.List;

/**
 * Controller to handle operations related to displaying station details.
 */
public class StationDetailsController {

    private final StationDetailsRepository stationDetailsRepository;

    /**
     * Constructor initializes the StationDetailsController.
     */
    public StationDetailsController() {
        this.stationDetailsRepository = StationDetailsRepository.getInstance();
    }

    /**
     * Retrieves all station details from the repository.
     *
     * @return A list of all station details.
     */
    public List<StationDetails> listStationDetails() {
        return stationDetailsRepository.getAllStationDetails();
    }

    /**
     * Retrieves the details of a specific station by name.
     *
     * @param stationName The name of the station.
     * @return The StationDetails object, or null if not found.
     */
    public StationDetails getStationDetails(String stationName) {
        return stationDetailsRepository.getStationDetailsByName(stationName);
    }
}