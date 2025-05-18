package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.UpgradeStationController;
import pt.ipp.isep.dei.domain.BuildingType;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Station;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for upgrading railway stations with buildings.
 *
 * <p>This class provides a console-based user interface to facilitate the
 * process of upgrading railway stations on a given map. The user can:
 * <ul>
 *     <li>Load the current map and its stations</li>
 *     <li>Select a station to upgrade</li>
 *     <li>Choose an available building type as an upgrade</li>
 *     <li>Confirm and execute the selected upgrade</li>
 * </ul>
 * It relies on the {@link UpgradeStationController} to handle the upgrade logic and
 * ensure consistency with the business rules.</p>
 */

public class UpgradeStationUI implements Runnable {
    private final UpgradeStationController controller;
    private Map currentMap;
    private List<Station> stations;
    private Station selectedStation;
    private BuildingType selectedUpgrade;

    /**
     * Constructs a new UpgradeStationUI.
     * <p>
     * Initializes the necessary controller and prepares the UI for interaction.
     * </p>
     */
    public UpgradeStationUI() {
        controller = new UpgradeStationController();
    }

    private UpgradeStationController getController() {
        return controller;
    }

    /**
     * Entry point for the Upgrade Station UI.
     *
     * <p>This method displays the main menu for station upgrades, guiding the user
     * through the steps of selecting a station and an upgrade, confirming the
     * operation, and executing it. Ensures that the appropriate upgrade logic is
     * delegated to the {@link UpgradeStationController}.</p>
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Upgrade Station ------------------------");

        // Check if an active map exists
        if (!controller.hasActiveMap()) {
            System.out.println("No active map selected. Please create a map first.");
            return;
        }

        loadMapAndStations();

        if (stations == null || stations.isEmpty()) {
            System.out.println("No stations available for upgrade.");
            return;
        }

        selectedStation = displayAndSelectStation();
        if (selectedStation == null) {
            System.out.println("Cancelled.");
            return;
        }

        List<BuildingType> availableUpgrades = controller.getAvailableUpgrades(selectedStation);
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
     * Loads the current map and its stations.
     *
     * <p>This method retrieves the active map and the list of stations from
     * the controller. This data is necessary for the upgrade process.</p>
     */
    private void loadMapAndStations() {
        Object[] result = controller.loadMapAndStations();
        if (result == null) {
            System.out.println("Failed to load map and stations.");
            return;
        }
        currentMap = (Map) result[0];
        stations = (List<Station>) result[1];
    }

    /**
     * Displays a list of stations on the current map and allows the user to select one.
     *
     * @return the selected station, or {@code null} if the user cancels the selection.
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
     * Displays options for the selected station, showing its details and available upgrades.
     */
    private void displayStationOptions() {
        int i = 1;
        for (Station station : stations) {
            System.out.printf("  %d - %s%n", i, station.getName());
            i++;
        }
    }

    /**
     * Displays a list of available upgrades and allows the user to select one.
     *
     * @param availableUpgrades the list of available building types for the upgrade.
     * @return the selected building type, or {@code null} if the user cancels the selection.
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
     * Displays detailed upgrade options for the user.
     *
     * @param availableUpgrades the list of upgrades available for the selected station.
     */
    private void displayUpgradeOptions(List<BuildingType> availableUpgrades) {
        int i = 1;
        for (BuildingType upgrade : availableUpgrades) {
            System.out.printf("  %d - %s (Cost: %.2f)%n", i, upgrade.name(), upgrade.getCost());
            i++;
        }
    }

    /**
     * Asks the user to confirm the upgrade based on building details and the associated cost.
     *
     * @param details the building type details for the upgrade.
     * @param cost the cost of applying the upgrade.
     * @return {@code true} if the user confirms the upgrade, {@code false} otherwise.
     */
    private boolean confirmUpgrade(BuildingType details, double cost) {
        Scanner input = new Scanner(System.in);

        System.out.printf("Upgrade: %s%n", details.name());
        System.out.printf("Cost: %.2f%n", cost);

        if (details.getReplaces() != null) {
            System.out.printf("Replaces: %s%n", details.getReplaces().name());
        }

        System.out.print("Confirm upgrade? (Y/N): ");
        String response = input.nextLine().trim().toUpperCase();

        return response.equals("Y") || response.equals("YES");
    }

    /**
     * Executes the upgrade process.
     *
     * <p>This method applies the selected upgrade to the station, delegating the logic to
     * the controller and displaying the result to the user.</p>
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