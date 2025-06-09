package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.Scanner;

/**
 * User interface for creating a new railway map.
 *
 * <p>This class provides a console-based user interface to create a
 * new map, collecting data such as name, width, and height. It interacts
 * with the {@link CreateMapController} for handling the map creation
 * logic and saving the map to the repository.</p>
 *
 * <p>The user must be logged in as an "EDITOR" to create a map. The class
 * handles input validation, including ensuring dimensions are positive
 * integers.</p>
 */
public class CreateMapUI implements Runnable{
    public Scanner scanner = new Scanner(System.in);

    /**
     * Entry point for the Create Map UI.
     *
     * <p>Displays the interface for creating a new map, prompting the user for:
     * <ul>
     *     <li>Map name</li>
     *     <li>Map dimensions (width and height)</li>
     * </ul>
     * Validates the input and uses the {@link CreateMapController} to create and save the map.</p>
     *
     * <p>If the logged-in user does not have the required "EDITOR" role, the creation
     * process is aborted.</p>
     */
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
            Map map = new Map(name, width, height);
            Map savedMap = controller.saveMap(map);

            if (savedMap != null) {
                System.out.println("Map created successfully:");
                System.out.printf("- Name: %s%n- Dimensions: %dx%d%n",
                        savedMap.getName(), savedMap.getWidth(), savedMap.getHeight());
                Repositories.getInstance().getMapRepository().setActiveMap(savedMap);
            } else {
                System.out.println("Map creation failed.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reads a positive integer from the console.
     *
     * <p>This method ensures the input is a valid positive integer,
     * continuously prompting the user until a valid value is provided.</p>
     *
     * @return the positive integer entered by the user.
     */
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
