package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AddIndustryController;
import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryTypes;
import pt.ipp.isep.dei.domain.Position;

import java.util.Scanner;

/**
 * User Interface for adding a new Industry to the system.
 *
 * This is a console-based interface that allows the user to input the necessary
 * details for creating a new industry, such as its name, position, and type.
 * The class interacts with the {@link AddIndustryController} to process the inputs
 * and create an instance of {@link Industry}.
 */
public class AddIndustryUI implements Runnable {

    private AddIndustryController controller; // Controller to handle business logic
    private Scanner scanner; // Scanner for user input

    /**
     * Constructs a new {@link AddIndustryUI} object and initializes
     * the required components like the controller and scanner.
     */
    public AddIndustryUI() {
        this.controller = new AddIndustryController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for adding a new industry.
     * <p>
     * This method prompts the user to:
     * - Input the industry's name.
     * - Input the industry's geographical position in terms of x and y coordinates.
     * - Choose the industry's type from a list of predefined options in {@link IndustryTypes}.
     * <p>
     * It handles input validation and communicates with the controller to create the industry.
     */
    @Override
    public void run() {
        System.out.println("\n=== Add New Industry ===");

        try {
            // Prompt for industry name
            System.out.print("Industry name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            // Input the industry's position
            System.out.println("\nIndustry position:");
            System.out.print("X coordinate: ");
            int x = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Y coordinate: ");
            int y = Integer.parseInt(scanner.nextLine().trim());

            // Create a Position object based on user input
            Position position = new Position(x, y);

            // Display available industry types
            System.out.println("\nAvailable industry types:");
            IndustryTypes.IndustryType[] types = IndustryTypes.IndustryType.values();

            // List the industry types with indexing
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }

            // Select industry type based on user input
            System.out.print("\nSelect industry type (1-" + types.length + "): ");
            int typeChoice = Integer.parseInt(scanner.nextLine().trim());

            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid option.");
                return;
            }

            IndustryTypes.IndustryType selectedType = types[typeChoice - 1];

            // Create the Industry through the controller
            Industry newIndustry = controller.addIndustry(name, position, selectedType);

            // Success message
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
