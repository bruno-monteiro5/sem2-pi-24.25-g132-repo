package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildStationController;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.StationType;

import java.util.Scanner;

public class BuildStationUI {
    private final BuildStationController controller;
    private final Scanner scanner;

    public BuildStationUI(BuildStationController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Build Station ===");

        /*
        // Escolher tipo de estação
        System.out.println("Available Station Types:");
        for (StationType type : controller.getStationTypes()) {
            System.out.printf("- %s (Cost: %d, Radius: %d)%n",
                    type.name(), type.getCost(), type.getRadius());
        }

         */

        System.out.print("Enter station type (DEPOT, STATION, TERMINAL): ");
        StationType type = StationType.valueOf(scanner.nextLine().trim().toUpperCase());

        // Introduzir posição
        System.out.print("Enter X coordinate: ");
        int x = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Y coordinate: ");
        int y = Integer.parseInt(scanner.nextLine());

        Position position = new Position(x, y);

        // Centro apenas se for STATION
        String center = null;
        if (type == StationType.STATION) {
            System.out.print("Enter center (NE, SE, NW, SW): ");
            center = scanner.nextLine().trim().toUpperCase();
        }

        /*
        StationDto dto = new StationDto(type, position, center);

        boolean success = controller.buildStation(dto);
        if (!success) {
            System.out.println("Station could not be built.");
        }

         */
    }
}
