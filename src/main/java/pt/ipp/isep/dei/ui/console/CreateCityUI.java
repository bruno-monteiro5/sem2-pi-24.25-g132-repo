package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateCityController;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.HouseBlock;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * User interface for creating a new city via the console.
 *
 * <p>This class provides an interactive console-based interface for creating a new city.
 * It manages user input for various details about the city, including:
 * <ul>
 *     <li>City name</li>
 *     <li>Position (coordinates x, y)</li>
 *     <li>Map selection</li>
 *     <li>Number of house blocks</li>
 *     <li>Manual or automatic assignment of house blocks</li>
 * </ul>
 * The {@link CreateCityUI} interacts with the {@link CreateCityController} to handle the business logic
 * of creating and validating a city. Errors are handled gracefully with appropriate feedback to the user.
 * </p>
 */

public class CreateCityUI implements Runnable {

    private final CreateCityController controller;

    /**
     * Constructs a new {@code CreateCityUI} instance.
     *
     * <p>This constructor initializes the controller, {@link CreateCityController}, which
     * is responsible for connecting the user interface with the application's business logic
     * for creating cities.</p>
     */
    public CreateCityUI() {
        controller = new CreateCityController();
    }

    /**
     * Runs the city creation interactive console workflow.
     *
     * <p>This method displays a menu-like structure where the user provides details to create a city.
     * Details requested include:
     * <ul>
     *     <li>The name of the city</li>
     *     <li>The coordinates of the city (x and y)</li>
     *     <li>The number of house blocks</li>
     *     <li>A choice of map (selected from the existing maps in the application)</li>
     *     <li>Whether house blocks should be added manually or automatically</li>
     * </ul>
     * The user is guided step-by-step, with validation for invalid inputs where required.
     * Based on the user's preferences, the city is created (via the {@link CreateCityController})
     * and the result is displayed back to the user.</p>
     *
     * <p>If errors occur during city creation (e.g., invalid name, duplicate city, or invalid positions),
     * an error message is presented to the user, and the process may continue as needed.</p>
     *
     * <strong>Workflow Overview:</strong>
     * <ol>
     *     <li>Let the user select a map from a list of available maps</li>
     *     <li>Ask for the city name</li>
     *     <li>Request the city position (x, y)</li>
     *     <li>Ask for the number of house blocks</li>
     *     <li>Ask if house blocks should be assigned manually</li>
     *     <li>If manual, request coordinates for each house block</li>
     *     <li>Create the city and display its summary</li>
     * </ol>
     *
     * <p>This method is the main entry point for this class, as it is triggered
     * when an instance of {@link CreateCityUI} is executed in a separate thread or as part of the application flow.</p>
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n--- Create City ------------------------");

        List<Map> maps = controller.getMaps();
        int selectedMapIndex = Utils.showAndSelectIndex(maps, "\nSelect a map:");
        if (selectedMapIndex == -1) {
            return;
        }
        Map selectedMap = maps.get(selectedMapIndex);

        System.out.print("Enter city name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter city x-coordinate: ");
        int x = scanner.nextInt();

        System.out.println("Enter city y-coordinate: ");
        int y = scanner.nextInt();

        System.out.print("Enter the number of house blocks: ");
        int houseBlocks = scanner.nextInt();

        boolean manual = Utils.confirm("Do you want to add house blocks manually?(Y-yes/N-no)");
        List<Position> manualPositions = null;

        if (manual) {
            manualPositions = new ArrayList<>();
            for (int i = 0; i < houseBlocks; i++) {
                int blockX = 0;
                int blockY = 0;

                boolean isValidX = false;
                while (!isValidX) {
                    System.out.print("Enter x-coordinate of house block #" + (i + 1) + ": ");
                    try {
                        blockX = scanner.nextInt();
                        isValidX = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid numeric value.");
                        scanner.next();
                    }
                }

                boolean isValidY = false;
                while (!isValidY) {
                    System.out.print("Enter y-coordinate of house block #" + (i + 1) + ": ");
                    try {
                        blockY = scanner.nextInt();
                        isValidY = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid numeric value.");
                        scanner.next();
                    }
                }

                manualPositions.add(new Position(blockX, blockY));
            }
        }

        try {
            City city = controller.createCity(selectedMap, name, new Position(x, y), houseBlocks, manual, manualPositions);
            System.out.println("City created successfully! Details:");
            System.out.println(city);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
