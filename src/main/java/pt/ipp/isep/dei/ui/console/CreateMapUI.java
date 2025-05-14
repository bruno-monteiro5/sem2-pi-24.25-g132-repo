package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.MapRepository;

import java.util.Scanner;


public class CreateMapUI {

    private final CreateMapController controller;

    public CreateMapUI() {
        MapRepository repository = new MapRepository();
        this.controller = new CreateMapController(repository);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create New Map ===");

        System.out.print("Enter map name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter map width (positive integer): ");
        int width = readPositiveInt(scanner);

        System.out.print("Enter map height (positive integer): ");
        int height = readPositiveInt(scanner);

        try {
            Map newMap = controller.createMap(name, width, height);
            System.out.println("Map created successfully!");
            System.out.println("Name: " + newMap.getName());
            System.out.println("Dimensions: " + newMap.getWidth() + " x " + newMap.getHeight());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int readPositiveInt(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.print("Please enter a positive integer: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }





}
