package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildRailwayLineController;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildRailwayLineUI implements Runnable {
    private final BuildRailwayLineController controller;
    private final Scanner scanner;

    public BuildRailwayLineUI() {
        this.controller = new BuildRailwayLineController();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("\n=== Build Railway Line ===");

        // Check if there's an active map
        if (Repositories.getInstance().getMapRepository().getActiveMap() == null) {
            System.out.println("No active map selected. Please create or load a map first.");
            return;
        }

        // Check if there are enough stations
        List<Station> stations = controller.getAllStations();
        if (stations.size() < 2) {
            System.out.println("You need at least 2 stations to build a railway line.");
            return;
        }

        // Select first station
        System.out.println("\nAvailable Stations:");
        Station station1 = selectStation(stations);
        if (station1 == null) return;

        // Select second station (excluding the first one)
        System.out.println("\nSelect second station:");
        Station station2 = selectStation(stations, station1);
        if (station2 == null) return;

        // Select line type
        System.out.println("\nAvailable Line Types:");
        LineType lineType = selectLineType();
        if (lineType == null) return;

        // Show cost estimate
        int estimatedCost = controller.estimateCost(station1, station2, lineType);
        System.out.printf("\nEstimated cost for this line: %d\n", estimatedCost);

        // Confirm
        System.out.print("\nConfirm construction (Y/N)? ");
        String confirmation = scanner.next();
        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Construction cancelled.");
            return;
        }

        // Build the line
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        boolean success = controller.createRailwayLine(scenario, station1, station2, lineType);
        if (success) {
            System.out.println("\nRailway line successfully built between:");
            System.out.println("- " + station1.getName());
            System.out.println("- " + station2.getName());
            System.out.println("Type: " + lineType);
        } else {
            System.out.println("\nFailed to build railway line. Possible reasons:");
            System.out.println("- Insufficient funds");
            System.out.println("- Line already exists");
            System.out.println("- Invalid station selection");
        }
    }

    private Station selectStation(List<Station> stations) {
        return selectStation(stations, null);
    }

    private Station selectStation(List<Station> stations, Station exclude) {
        List<Station> options = new ArrayList<>();
        for (Station s : stations) {
            if (exclude == null || !s.equals(exclude)) {
                options.add(s);
            }
        }

        if (options.isEmpty()) {
            System.out.println("No stations available for selection.");
            return null;
        }

        for (int i = 0; i < options.size(); i++) {
            Station s = options.get(i);
            System.out.printf("%d - %s (at %d,%d)%n",
                    i + 1, s.getName(),
                    s.getLocation().getX(),
                    s.getLocation().getY());
        }

        System.out.print("Select station (0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        if (choice == 0) return null;
        if (choice < 1 || choice > options.size()) {
            System.out.println("Invalid selection.");
            return null;
        }

        return options.get(choice - 1);
    }


    private LineType selectLineType() {
        List<LineType> types = controller.getAllLineTypes();
        for (int i = 0; i < types.size(); i++) {
            LineType type = types.get(i);
            System.out.printf("%d - %s\n", i+1, type);
        }
        System.out.print("Select line type (0 to cancel): ");

        try {
            int choice = scanner.nextInt();
            if (choice == 0) return null;
            if (choice > 0 && choice <= types.size()) {
                return types.get(choice-1);
            }
        } catch (Exception e) {
            scanner.next(); // Clear invalid input
        }

        System.out.println("Invalid selection.");
        return null;
    }
}