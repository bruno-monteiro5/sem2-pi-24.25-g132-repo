package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.console.utils.Utils;

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
    @Override
    public void run() {
        System.out.println("\n=== Create New Map ===");

        CreateMapController controller = new CreateMapController();

        String name = null;
        boolean validName = false;

        while (!validName) {
            name = Utils.readLineFromConsole("Enter map name: ");

            if (!Map.isValidMapName(name)) {
                System.out.println("❌ Error: Map name must contain only letters, numbers, underscores or hyphens.");
            } else {
                validName = true;
            }
        }

        int width = -1;
        boolean validWidth = false;
        while (!validWidth) {
            try {
                width = Utils.readIntegerFromConsole("Enter map width: ");
                if (width <= 0) {
                    System.out.println("❌ Error: Width must be a positive number.");
                } else {
                    validWidth = true;
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        int height = -1;
        boolean validHeight = false;
        while (!validHeight) {
            try {
                height = Utils.readIntegerFromConsole("Enter map height: ");
                if (height <= 0) {
                    System.out.println("❌ Error: Height must be a positive number.");
                } else {
                    validHeight = true;
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        String email = controller.getUserEmailFromSession();

        if (email == null) {
            System.out.println("⚠️  Warning: You must be logged in as an EDITOR to create a map.");
            return;
        }

        try {
            Map map = new Map(name, width, height);
            Map savedMap = controller.saveMap(map);

            if (savedMap != null) {
                System.out.println("✅ Map created successfully:");
                System.out.printf("- Name: %s%n- Dimensions: %dx%d%n",
                        savedMap.getName(), savedMap.getWidth(), savedMap.getHeight());
                Repositories.getInstance().getMapRepository().setActiveMap(savedMap);
            } else {
                System.out.println("❌ Map creation failed.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

}
