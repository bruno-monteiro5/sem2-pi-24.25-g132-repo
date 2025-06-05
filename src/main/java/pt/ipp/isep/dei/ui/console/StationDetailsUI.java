package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildStationController;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;

/**
 * User interface for displaying station details via console.
 * <p>
 * This class handles user input for listing stations and selecting one
 * to view its details. It interacts with the {@link BuildStationController}
 * to retrieve and display information.
 * </p>
 */
public class StationDetailsUI implements Runnable {

    private final BuildStationController controller;

    /**
     * Constructs a new StationDetailsUI instance.
     * Initializes the controller responsible for fetching station data.
     */
    public StationDetailsUI() {
        this.controller = new BuildStationController();
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

        List<Station> stationList = controller.listAllStations();

        if (stationList.isEmpty()) {
            System.out.println("No stations available.");
            return;
        }

        int chosenIndex = Utils.showAndSelectIndex(stationList, "Select a station to view details:");
        if (chosenIndex == -1) {
            System.out.println("No station selected. Exiting...");
            return;
        }

        Station chosenStation = stationList.get(chosenIndex);

        System.out.println("\nStation Details:");
        System.out.println(chosenStation.getFullDetails());
    }
}