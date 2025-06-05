package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AssignTrainToRouteController;
import pt.ipp.isep.dei.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignTrainToRouteUI implements Runnable {

    private final AssignTrainToRouteController controller = new AssignTrainToRouteController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("\n=== Assign Train to Route ===");

        List<Train> trains = controller.getAvailableTrains();
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        System.out.println("Available Trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, trains.get(i));
        }

        System.out.print("Select a train by number: ");
        int trainIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (trainIndex < 0 || trainIndex >= trains.size()) {
            System.out.println("Invalid train selection.");
            return;
        }
        Train selectedTrain = trains.get(trainIndex);

        System.out.print("Enter a name for the new route: ");
        String routeName = scanner.nextLine().trim();

        Route route = new Route();
        route.setName(routeName);

        boolean addMore;
        do {
            System.out.println("\n--- Add Point to Route ---");

            List<Station> stations = controller.getAllStations();
            if (stations.isEmpty()) {
                System.out.println("No stations available.");
                return;
            }

            System.out.println("Available Stations:");
            for (Station s : stations) {
                System.out.println("- " + s.getName());
            }

            System.out.print("Enter Station name: ");
            String stationName = scanner.nextLine().trim();
            Station station = stations.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(stationName))
                    .findFirst()
                    .orElse(null);

            if (station == null) {
                System.out.println("Station not found.");
                return;
            }

            System.out.println("Available cargo types:");
            for (CargoType type : CargoType.values()) {
                System.out.println("- " + type.name());
            }

            System.out.print("Enter cargo types to pick up : ");
            String input = scanner.nextLine().trim();
            String[] cargoStrings = input.split(",");
            List<String> cargoes = new ArrayList<>();

            for (String c : cargoStrings) {
                String cleaned = c.trim().toUpperCase();
                try {
                    CargoType.valueOf(cleaned);
                    cargoes.add(cleaned);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid cargo type: " + cleaned);
                    return;
                }
            }

            System.out.println("Select Cargo Mode:");
            CargoMode[] modes = CargoMode.values();
            for (int i = 0; i < modes.length; i++) {
                System.out.printf("%d. %s\n", i + 1, modes[i]);
            }

            System.out.print("Choice (number or mode name): ");
            String modeInput = scanner.nextLine().trim().toUpperCase();

            CargoMode selectedMode = null;

            try {
                int modeIndex = Integer.parseInt(modeInput) - 1;
                if (modeIndex >= 0 && modeIndex < modes.length) {
                    selectedMode = modes[modeIndex];
                }
            } catch (NumberFormatException ignored) {}

            if (selectedMode == null) {
                try {
                    selectedMode = CargoMode.valueOf(modeInput);
                } catch (IllegalArgumentException ignored) {}
            }

            if (selectedMode == null) {
                System.out.println("Invalid cargo mode.");
                return;
            }



            PointOfRoute point = new PointOfRoute(station, cargoes, selectedMode);
            route.addPointOfRoute(point);

            System.out.print("Add another point to the route? (Y/N): ");
            String choice = scanner.nextLine().trim();
            addMore = choice.equalsIgnoreCase("Y");

        } while (addMore);

        controller.assignRouteToTrain(selectedTrain, route);
        System.out.println("Train successfully assigned to route: " + route.getName());
    }
}
