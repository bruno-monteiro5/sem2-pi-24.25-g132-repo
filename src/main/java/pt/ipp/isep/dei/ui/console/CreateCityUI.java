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
 * <p>
 * This class handles user input for city creation, including:
 * <ul>
 *     <li>City name</li>
 *     <li>City coordinates (x, y)</li>
 *     <li>Map selection</li>
 *     <li>Automatic or manual house block assignment</li>
 * </ul>
 * It interacts with the {@link CreateCityController} to perform the creation logic.
 * </p>
 */
public class CreateCityUI implements Runnable {

    private final CreateCityController controller;

    /**
     * Constructs a new CreateCityUI instance.
     * Initializes the controller responsible for city creation.
     */
    public CreateCityUI() {
        controller = new CreateCityController();
    }

    /**
     * Displays the city creation menu and handles user input.
     * <p>
     * Prompts the user for city details, including name, coordinates, map selection,
     * and house block assignment. Validates the input and creates a new city using
     * the controller.
     * </p>
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n--- Create City ------------------------");

        System.out.print("Enter city name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter city x-coordinate: ");
        int x = scanner.nextInt();

        System.out.println("Enter city y-coordinate: ");
        int y = scanner.nextInt();

        int houseblocks = -1;
        List<Map> maps = controller.getMaps();
        System.out.println("Enter the map number");
        int chosen = Utils.showAndSelectIndex(maps, "\nMaps:");
        if (chosen == -1) {
            return;
        }

        System.out.println("Debug: " + maps.get(chosen));

        boolean auto = Utils.confirm("Do you want to add house blocks automatically to this city?");
        if (!auto) {
            System.out.println("Enter the number of house blocks to add: ");
            houseblocks = scanner.nextInt();
        }

        try {
            Position position = new Position(x, y);
            City city = controller.createCity(maps.get(chosen), name, position, houseblocks);
            System.out.println("City created successfully!");
            System.out.println("Name: " + city.getName());
            System.out.println("Coordinates: " + city.getX() + " ; " + city.getY());


            System.out.println("House blocks: " + city.getNumberOfHouseBlocks());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        List<City> cities = controller.getCities();
        System.out.println("Cities: " + cities);


    }
}
