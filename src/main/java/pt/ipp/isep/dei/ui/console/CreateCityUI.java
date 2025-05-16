package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateCityController;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.HouseBlock;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateCityUI implements Runnable {

    private final CreateCityController controller;

    public CreateCityUI() {
        controller = new CreateCityController();
    }

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

        System.out.println("Enter the number of house blocks: ");
        int houseblocks = scanner.nextInt();


        try {
            Position position = new Position(x, y);
            City city = controller.createCity(name, position, houseblocks);
            System.out.println("Map created successfully!");
            System.out.println("Name: " + newMap.getName());
            System.out.println("Dimensions: " + newMap.getWidth() + " x " + newMap.getHeight());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }




    }
}
