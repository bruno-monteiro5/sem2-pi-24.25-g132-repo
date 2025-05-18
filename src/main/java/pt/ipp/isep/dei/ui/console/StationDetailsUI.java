package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.StationDetailsController;
import pt.ipp.isep.dei.domain.StationDetails;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;

/**
 * User interface for displaying station details via console.
 * <p>
 * This class handles user input for listing stations and selecting one
 * to view its details. It interacts with the {@link StationDetailsController}
 * to retrieve and display information.
 * </p>
 */
public class StationDetailsUI implements Runnable {

    private final StationDetailsController controller;

    /**
     * Constructs a new StationDetailsUI instance.
     * Initializes the controller responsible for fetching station details.
     */
    public StationDetailsUI() {
        this.controller = new StationDetailsController();
    }

    /**
     * Displays the Station Details menu and handles user interaction.
     * <p>
     * Lists all available stations, prompts the user to select one,
     * and shows its details.
     * </p>
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Station Details ------------------------");

        // Fetch the list of available station details
        List<StationDetails> stationDetailsList = controller.listStationDetails();

        if (stationDetailsList.isEmpty()) {
            System.out.println("No stations available.");
            return;
        }

        // Show the list of stations to the user and allow selection
        int chosenIndex = Utils.showAndSelectIndex(stationDetailsList, "Select a station to view details:");
        if (chosenIndex == -1) {
            System.out.println("No station selected. Exiting...");
            return;
        }

        // Retrieve the chosen station's details
        StationDetails chosenStationDetails = stationDetailsList.get(chosenIndex);

        // Display the details of the chosen station
        System.out.println("\nStation Details:");
        System.out.println(chosenStationDetails);
    }
}