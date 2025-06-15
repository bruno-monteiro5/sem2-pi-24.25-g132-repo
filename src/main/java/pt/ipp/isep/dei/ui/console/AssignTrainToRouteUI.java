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

        boolean addMore = true;
        do {
            System.out.println("\n--- Add Point to Route ---");

            List<Station> stations = controller.getAllStations();
            if (stations.isEmpty()) {
                System.out.println("No stations available.");
                return;
            }

            System.out.println("Available Stations:");
            for (int i = 0; i < stations.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, stations.get(i).getName());
            }

            System.out.print("Enter station number: ");
            int stationIndex;
            try {
                stationIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (stationIndex < 0 || stationIndex >= stations.size()) {
                    System.out.println("Invalid station selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                return;
            }
            Station station = stations.get(stationIndex);

            CargoType[] cargoTypes = CargoType.values();
            System.out.println("Available cargo types:");
            for (int i = 0; i < cargoTypes.length; i++) {
                System.out.printf("%d. %s\n", i + 1, cargoTypes[i].name());
            }

            System.out.print("Enter the numbers of cargo types to pick up (comma separated): ");
            String input = scanner.nextLine().trim();
            String[] cargoStrings = input.split(",");
            List<String> cargoes = new ArrayList<>();

            for (String s : cargoStrings) {
                try {
                    int index = Integer.parseInt(s.trim()) - 1;
                    if (index >= 0 && index < cargoTypes.length) {
                        cargoes.add(cargoTypes[index].name());
                    } else {
                        System.out.println("Invalid cargo number: " + (index + 1));
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + s);
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
                int modeIdx = Integer.parseInt(modeInput) - 1;
                if (modeIdx >= 0 && modeIdx < modes.length) {
                    selectedMode = modes[modeIdx];
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

            List<PointOfRoute> points = route.getPointsOfRoute();
            if (!points.isEmpty()) {
                PointOfRoute lastPoint = points.get(points.size() - 1);
                if (lastPoint.getStation().getName().equalsIgnoreCase(station.getName())) {
                    System.out.println("Cannot add the same station twice in a row.");
                    return;
                }
            }

            boolean validToAdd = true;
            if (!points.isEmpty()) {
                Station previous = points.get(points.size() - 1).getStation();
                RailwayLine line = controller.getRailwayLineBetween(previous, station);
                if (line == null) {
                    System.out.printf("⚠️ There is no line between %s and %s. Station not added.%n",
                            previous.getName(), station.getName());
                    System.out.print("Add another point to the route? (Y/N): ");
                    String choice = scanner.nextLine().trim();
                    addMore = choice.equalsIgnoreCase("Y");
                    validToAdd = false;
                }
            }

            if (validToAdd) {
                PointOfRoute point = new PointOfRoute(station, cargoes, selectedMode);
                route.addPointOfRoute(point);

                System.out.print("Add another point to the route? (Y/N): ");
                String choice = scanner.nextLine().trim();
                addMore = choice.equalsIgnoreCase("Y");
            }

        } while (addMore);

        controller.assignRouteToTrain(selectedTrain, route);
        System.out.println("Train successfully assigned to route: " + route.getName());
    }
}
