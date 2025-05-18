package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.UpgradeStationController;
import pt.ipp.isep.dei.domain.BuildingType;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Station;

import java.util.List;
import java.util.Scanner;

/**
 * UI for upgrading stations with buildings.
 */
public class UpgradeStationUI implements Runnable {
    private final UpgradeStationController controller;
    private Map currentMap;
    private List<Station> stations;
    private Station selectedStation;
    private BuildingType selectedUpgrade;

    /**
     * Creates a new UpgradeStationUI.
     */
    public UpgradeStationUI() {
        controller = new UpgradeStationController();
    }

    private UpgradeStationController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Upgrade Station ------------------------");

        // Check if an active map exists
        if (!controller.hasActiveMap()) {
            System.out.println("No active map selected. Please create a map first.");
            return;
        }

        loadMapAndStations();

        // Check if stations were loaded successfully
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

    private void loadMapAndStations() {
        Object[] result = controller.loadMapAndStations();
        if (result == null) {
            System.out.println("Failed to load map and stations.");
            return;
        }
        currentMap = (Map) result[0];
        stations = (List<Station>) result[1];
    }

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
                input.next(); // Consume invalid input
            }
        }

        return stations.get(answer - 1);
    }

    private void displayStationOptions() {
        int i = 1;
        for (Station station : stations) {
            System.out.printf("  %d - %s%n", i, station.getName());
            i++;
        }
    }

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
                input.next(); // Consume invalid input
            }
        }

        return availableUpgrades.get(answer - 1);
    }

    private void displayUpgradeOptions(List<BuildingType> availableUpgrades) {
        int i = 1;
        for (BuildingType upgrade : availableUpgrades) {
            System.out.printf("  %d - %s (Cost: %.2f)%n", i, upgrade.name(), upgrade.getCost());
            i++;
        }
    }

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

    private void executeUpgrade() {
        boolean success = controller.executeUpgrade(selectedStation, selectedUpgrade);

        if (success) {
            System.out.println("\nStation successfully upgraded!");

        } else {
            System.out.println("\nFailed to upgrade station. Please check your funds and try again.");
        }
    }


}