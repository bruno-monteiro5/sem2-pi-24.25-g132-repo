package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.Scanner;

/**
 * UI for creating a new railway map.
 */
public class CreateMapUI implements Runnable{
    public Scanner scanner = new Scanner(System.in);
    public void run() {
        System.out.println("\n=== Create New Map ===");

        System.out.print("Enter map name: ");
        String name = scanner.nextLine();

        System.out.print("Enter map width: ");
        int width = readPositiveInt();

        System.out.print("Enter map height: ");
        int height = readPositiveInt();
        CreateMapController controller = new CreateMapController();
        String email = controller.getUserEmailFromSession();
        if (email == null) {
            System.out.println("You must be logged in as an EDITOR to create a map.");
            return;
        }

        try {
            Map map = new Map(name, width, height, email);
            Map savedMap = controller.saveMap(map);

            if (savedMap != null) {
                System.out.println("Map created successfully:");
                System.out.printf("- Name: %s%n- Dimensions: %dx%d%n- Editor: %s%n",
                        savedMap.getName(), savedMap.getWidth(), savedMap.getHeight(), savedMap.getEmailEditor());
                Repositories.getInstance().getMapRepository().setActiveMap(savedMap);
            } else {
                System.out.println("Map creation failed.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int readPositiveInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) return value;
                System.out.print("Value must be positive. Try again: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
