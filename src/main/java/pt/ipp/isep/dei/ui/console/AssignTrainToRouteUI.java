package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AssignTrainToRouteController;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;

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

        List<Route> routes = controller.getAllRoutes();
        if (routes.isEmpty()) {
            System.out.println("No routes available.");
            return;
        }

        System.out.println("Available Routes:");
        for (int i = 0; i < routes.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, routes.get(i).getName());
        }

        System.out.print("Select a route by number: ");
        int routeIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (routeIndex < 0 || routeIndex >= routes.size()) {
            System.out.println("Invalid route selection.");
            return;
        }
        Route selectedRoute = routes.get(routeIndex);

        System.out.println("\n--- Add Station and Cargos to Route ---");

        System.out.print("Enter Station name: ");
        String stationName = scanner.nextLine().trim();


        Station station = Repositories.getInstance().getStationRepository().getStationByName(stationName);
        if (station == null) {
            System.out.println("Station not found.");
            return;
        }

        List<Cargo> cargos = new ArrayList<>();
        System.out.print("Enter number of cargo types to add: ");
        int numCargos = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < numCargos; i++) {
            System.out.print("Enter cargo type: ");
            String type = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());

            try {
                CargoType cargoType = CargoType.valueOf(type);
                cargos.add(new Cargo(cargoType, qty, true));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid cargo type: " + type);
                return;
            }
        }

        System.out.println("Select Cargo Mode:");
        CargoMode[] modes = CargoMode.values();
        for (int i = 0; i < modes.length; i++) {
            System.out.printf("%d. %s\n", i + 1, modes[i]);
        }
        System.out.print("Choice: ");
        int modeIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (modeIndex < 0 || modeIndex >= modes.length) {
            System.out.println("Invalid cargo mode.");
            return;
        }

        CargoMode selectedMode = modes[modeIndex];
        selectedRoute.addStationWithCargoAndMode(station, cargos, selectedMode);

        boolean success = controller.assignTrainToRoute(selectedTrain.getId(), selectedRoute.getName());

        if (success) {
            System.out.println("Train successfully assigned to route.");
        } else {
            System.out.println("Failed to assign train to route.");
        }
    }
}