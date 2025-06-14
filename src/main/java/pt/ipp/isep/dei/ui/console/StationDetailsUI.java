package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildStationController;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Building;
import pt.ipp.isep.dei.domain.Cargo;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;

/**
 * User interface for displaying station details via console.
 * <p>
 * This class handles user input for listing stations and selecting one
 * to view its details. It fetches the list directly from the active scenario.
 * </p>
 */
public class StationDetailsUI implements Runnable {

    private final BuildStationController controller;

    /**
     * Constructs a new StationDetailsUI instance using BuildStationController.
     */
    public StationDetailsUI() {
        this.controller = new BuildStationController();
    }

    /**
     * Displays the Station Details menu and handles user interaction.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Station Details ------------------------");

        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        if (scenario == null) {
            System.out.println("No active scenario. Please load a scenario first.");
            return;
        }

        List<Station> stationList = scenario.getStations();

        if (stationList.isEmpty()) {
            System.out.println("⚠️ No stations available.");
            return;
        }

        int chosenIndex = Utils.showAndSelectIndex(stationList, "Select a station to view details:");
        if (chosenIndex == -1) {
            System.out.println("No station selected. Exiting...");
            return;
        }

        Station chosenStation = stationList.get(chosenIndex);

        System.out.println("\n📍 Station Details:");
        System.out.println(chosenStation.getFullDetails());

        List<Building> buildings = chosenStation.getBuildings();
        List<Cargo> supply = chosenStation.getSupplyCargo();
        List<Cargo> demand = chosenStation.getDemandCargo();

        if (buildings.isEmpty()) {
            System.out.println("⚠️ This station has no buildings.");
        } else {
            System.out.println("🏗 Buildings:");
            buildings.forEach(b -> System.out.println("  - " + b));
        }

        if (supply.isEmpty()) {
            System.out.println("⚠️ No supply cargo available at this station.");
        } else {
            System.out.println("📦 Supply Cargo:");
            supply.forEach(c -> System.out.println("  - " + c));
        }

        if (demand.isEmpty()) {
            System.out.println("⚠️ No demand cargo required at this station.");
        } else {
            System.out.println("📥 Demand Cargo:");
            demand.forEach(c -> System.out.println("  - " + c));
        }
    }
}
