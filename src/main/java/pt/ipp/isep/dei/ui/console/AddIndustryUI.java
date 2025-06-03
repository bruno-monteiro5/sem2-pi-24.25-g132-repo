package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AddIndustryController;
import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryData;
import pt.ipp.isep.dei.domain.IndustryType;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User Interface for adding a new Industry to the system.
 */
public class AddIndustryUI implements Runnable {

    private final AddIndustryController controller;
    private final Scanner scanner;

    public AddIndustryUI() {
        this.controller = new AddIndustryController();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("\n=== Add New Industry ===");

        try {
            IndustryType[] types = IndustryType.values();
            System.out.println("Select an industry type:");
            for (int i = 0; i < types.length; i++) {
                System.out.printf("%d. %s\n", i + 1, types[i].getDescription());
            }

            System.out.print("Enter your choice: ");
            int typeChoice = Integer.parseInt(scanner.nextLine().trim());

            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid type choice.");
                return;
            }

            IndustryType selectedType = types[typeChoice - 1];

            List<IndustryData> filtered = new ArrayList<>();
            for (IndustryData data : IndustryData.values()) {
                if (data.getType() == selectedType) {
                    filtered.add(data);
                }
            }

            if (filtered.isEmpty()) {
                System.out.println("No industries available for this type.");
                return;
            }

            System.out.println("\nAvailable "+ selectedType.getDescription() + ":");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, filtered.get(i).getName());
            }

            System.out.print("Select an industry: ");
            int industryChoice = Integer.parseInt(scanner.nextLine().trim());

            if (industryChoice < 1 || industryChoice > filtered.size()) {
                System.out.println("Invalid industry choice.");
                return;
            }

            IndustryData selectedData = filtered.get(industryChoice - 1);

            System.out.print("Enter X coordinate: ");
            int x = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Y coordinate: ");
            int y = Integer.parseInt(scanner.nextLine().trim());

            Position pos = new Position(x, y);
            Industry industry = controller.addIndustry(selectedData, pos);

            System.out.println("\nIndustry added successfully!");
            System.out.println(industry);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while adding the industry: " + e.getMessage());
        }
    }
}