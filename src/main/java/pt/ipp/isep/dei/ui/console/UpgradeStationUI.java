package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.UpgradeStationController;
import pt.ipp.isep.dei.domain.BuildingType;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for upgrading railway stations with buildings.
 * <p>
 * Allows the player to select a station and apply an upgrade
 * if available, showing its cost and effects.
 * </p>
 */
public class UpgradeStationUI implements Runnable {

    private final UpgradeStationController controller;
    private Map currentMap;
    private List<Station> stations;
    private Station selectedStation;
    private BuildingType selectedUpgrade;

    /**
     * Constructs a new UpgradeStationUI.
     */
    public UpgradeStationUI() {
        controller = new UpgradeStationController();
    }

    /**
     * Main entry point for the upgrade UI.
     * Loads stations, allows selection, and executes upgrade.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Upgrade Station ------------------------");

        if (!controller.hasActiveScenario()) {
            System.out.println("No active map selected. Please create a map first.");
            return;
        }

        loadScenarioAndStations();

        if (stations == null || stations.isEmpty()) {
            System.out.println("No stations available for upgrade.");
            return;
        }

        selectedStation = displayAndSelectStation();
        if (selectedStation == null) {
            System.out.println("Cancelled.");
            return;
        }

        List<BuildingType> availableUpgrades;
        try {
            availableUpgrades = controller.getAvailableUpgrades(selectedStation);
        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        if (availableUpgrades.isEmpty()) {
            System.out.println("No upgrades available for this station.");
            return;
        }

        selectedUpgrade = displayAndSelectUpgrade(availableUpgrades);
        if (selectedUpgrade == null) {
            System.out.println("Cancelled.");
            return;
        }

        BuildingType details = controller.getUpgradeDetails(selectedUpgrade);
        double cost = details.getCost();

        if (confirmUpgrade(details, cost)) {
            executeUpgrade();
        } else {
            System.out.println("Upgrade cancelled.");
        }
    }

    /**
     * Loads the current map and the list of stations from the controller.
     */
    private void loadScenarioAndStations() {
        Object[] result = controller.loadScenarioAndStations();
        if (result == null) {
            System.out.println("Failed to load scenario and stations.");
            return;
        }

        Scenario scenario = (Scenario) result[0];
        currentMap = scenario.getMap();
        stations = (List<Station>) result[1];
    }

    /**
     * Displays a list of stations and prompts the user to select one.
     *
     * @return the selected Station, or null if cancelled
     */
    private Station displayAndSelectStation() {
        int listSize = stations.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayStationOptions();
            System.out.print("Select a station to upgrade: ");
            if (input.hasNextInt()) {
                answer = input.nextInt();
            } else {
                input.next();
            }
        }

        return stations.get(answer - 1);
    }

    /**
     * Prints the list of stations for selection.
     */
    private void displayStationOptions() {
        int i = 1;
        for (Station station : stations) {
            System.out.printf("  %d - %s%n", i, station.getName());
            i++;
        }
    }

    /**
     * Displays available upgrades and allows the user to choose one.
     *
     * @param availableUpgrades list of building types available
     * @return the selected BuildingType, or null if cancelled
     */
    private BuildingType displayAndSelectUpgrade(List<BuildingType> availableUpgrades) {
        int listSize = availableUpgrades.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayUpgradeOptions(availableUpgrades);
            System.out.print("Select an upgrade: ");
            if (input.hasNextInt()) {
                answer = input.nextInt();
            } else {
                input.next();
            }
        }

        return availableUpgrades.get(answer - 1);
    }

    /**
     * Prints the upgrade options with cost.
     *
     * @param availableUpgrades list of building types to show
     */
    private void displayUpgradeOptions(List<BuildingType> availableUpgrades) {
        int i = 1;
        for (BuildingType upgrade : availableUpgrades) {
            System.out.printf("  %d - %s (Cost: %.2f)%n", i, upgrade.name(), upgrade.getCost());
            i++;
        }
    }

    /**
     * Shows detailed information about the selected upgrade and asks for confirmation.
     *
     * @param details building type selected
     * @param cost    cost of the upgrade
     * @return true if the user confirms the upgrade
     */
    private boolean confirmUpgrade(BuildingType details, double cost) {
        Scanner input = new Scanner(System.in);

        System.out.printf("Upgrade: %s%n", details.name());
        System.out.printf("Cost: %.2f%n", cost);

        if (details.getReplaces() != null) {
            System.out.printf("Replaces: %s%n", details.getReplaces().name());
        }

        // Show upgrade effects
        System.out.printf("Efficiency Bonus: +%.0f%%%n", details.getEfficiencyBonus() * 100);
        System.out.printf("Revenue Multiplier: x%.2f%n", details.getRevenueMultiplier());
        System.out.printf("Extra Storage: +%d units%n", details.getExtraStorageCapacity());

        System.out.print("Confirm upgrade? (Y/N): ");
        String response = input.nextLine().trim().toUpperCase();

        return response.equals("Y") || response.equals("YES");
    }

    /**
     * Executes the upgrade through the controller and displays the result.
     */
    private void executeUpgrade() {
        boolean success = controller.executeUpgrade(selectedStation, selectedUpgrade);

        if (success) {
            System.out.println("\nStation successfully upgraded!");
        } else {
            System.out.println("\nFailed to upgrade station. Please check your funds and try again.");
        }
    }
}
