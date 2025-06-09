package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateCityController;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.Repositories;
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
        Repositories.getInstance().getMapRepository().setActiveMap(selectedMap);

        String name = null;
        boolean validName = false;
        while (!validName) {
            System.out.print("Enter city name: ");
            name = scanner.nextLine().trim();

            if (!City.isValidName(name)) {
                System.out.println("❌ Error: Name must only contain letters and spaces.");
            } else if (controller.cityExists(name)) {
                System.out.println("❌ Error: A city with this name already exists.");
            } else {
                validName = true;
            }
        }

        int x = -1;
        boolean validX = false;
        while (!validX) {
            try {
                x = Utils.readIntegerFromConsole("Enter city x-coordinate: ");
                if (x < 0 || x >= selectedMap.getWidth()) {
                    System.out.println("❌ Error: x must be between 0 and " + (selectedMap.getWidth() - 1));
                } else {
                    validX = true;
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }

        int y = -1;
        boolean validY = false;
        while (!validY) {
            try {
                y = Utils.readIntegerFromConsole("Enter city y-coordinate: ");
                if (y < 0 || y >= selectedMap.getHeight()) {
                    System.out.println("❌ Error: y must be between 0 and " + (selectedMap.getHeight() - 1));
                } else {
                    validY = true;
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }


        int houseBlocks = -1;
        boolean validBlocks = false;
        while (!validBlocks) {
            try {
                houseBlocks = Utils.readIntegerFromConsole("Enter the number of house blocks: ");
                if (houseBlocks <= 0) {
                    System.out.println("❌ Error: number of blocks must be positive.");
                } else {
                    validBlocks = true;
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }

        boolean manual = Utils.confirm("Do you want to add house blocks manually?(Y-yes/N-no)");
        List<Position> manualPositions = null;

        if (manual) {
            manualPositions = new ArrayList<>();
            City tempCity = new City(name, new Position(x, y), houseBlocks);  // temporária, usada só para validar

            for (int i = 0; i < houseBlocks; i++) {
                Position blockPos = null;
                boolean validBlock = false;

                while (!validBlock) {
                    int blockX = Utils.readIntegerFromConsole("Enter x-coordinate of house block #" + (i + 1) + ": ");
                    int blockY = Utils.readIntegerFromConsole("Enter y-coordinate of house block #" + (i + 1) + ": ");

                    blockPos = new Position(blockX, blockY);

                    if (!tempCity.isValidBlockPosition(blockPos, selectedMap)) {
                        System.out.println("❌ Error: Invalid block position (outside map, overlaps city, or too far).");
                    } else {
                        validBlock = true;
                    }
                }

                manualPositions.add(blockPos);
            }
        }


        System.out.println("\nPlease confirm the city details:");
        System.out.println("Name: " + name);
        System.out.println("Position: [" + x + ", " + y + "]");
        System.out.println("House blocks: " + houseBlocks);

        if (manual) {
            System.out.println("Manual house block positions:");
            for (int i = 0; i < manualPositions.size(); i++) {
                Position pos = manualPositions.get(i);
                System.out.println(" - Block #" + (i + 1) + ": [" + pos.getX() + ", " + pos.getY() + "]");
            }
        }

        String confirm = Utils.readLineFromConsole("Confirm city creation? (Y-yes/N-no): ");
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("❌ City creation cancelled.");
            return;
        }

        try {
            City city = controller.createCity(name, new Position(x, y), houseBlocks, manual, manualPositions);
            System.out.println("✅ City created successfully! Details:");
            System.out.println(city);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}