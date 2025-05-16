package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AddIndustryController;
import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryTypes;
import pt.ipp.isep.dei.domain.Position;

import java.util.Scanner;

public class AddIndustryUI implements Runnable {

    private AddIndustryController controller;
    private Scanner scanner;

    public AddIndustryUI() {
        this.controller = new AddIndustryController();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("\n=== Add New Industry ===");

        try {
            System.out.print("Industry name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            System.out.println("\nIndustry position:");
            System.out.print("X coordinate: ");
            int x = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Y coordinate: ");
            int y = Integer.parseInt(scanner.nextLine().trim());

            Position position = new Position(x, y);

            System.out.println("\nAvailable industry types:");
            IndustryTypes.IndustryType[] types = IndustryTypes.IndustryType.values();

            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }

            System.out.print("\nSelect industry type (1-" + types.length + "): ");
            int typeChoice = Integer.parseInt(scanner.nextLine().trim());

            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid option.");
                return;
            }

            IndustryTypes.IndustryType selectedType = types[typeChoice - 1];

            Industry newIndustry = controller.addIndustry(name, position, selectedType);

            System.out.println("\nIndustry added successfully!");
            System.out.println(newIndustry);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for coordinates.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while adding the industry: " + e.getMessage());
        }
    }
}
