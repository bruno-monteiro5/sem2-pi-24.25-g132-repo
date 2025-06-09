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

        IndustryType[] types = IndustryType.values();
        IndustryType selectedType = null;

        while (selectedType == null) {
            System.out.println("Select an industry type:");
            for (int i = 0; i < types.length; i++) {
                System.out.printf("%d. %s\n", i + 1, types[i].getDescription());
            }

            try {
                System.out.print("Enter your choice: ");
                int typeChoice = Integer.parseInt(scanner.nextLine().trim());

                if (typeChoice < 1 || typeChoice > types.length) {
                    System.out.println("❌ Invalid type choice. Please select a valid option.");
                } else {
                    selectedType = types[typeChoice - 1];
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }

        List<IndustryData> filtered = new ArrayList<>();
        for (IndustryData data : IndustryData.values()) {
            if (data.getType() == selectedType) {
                filtered.add(data);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("ℹ️ No industries available for this type.");
            return;
        }

        IndustryData selectedData = null;
        while (selectedData == null) {
            System.out.println("\nAvailable " + selectedType.getDescription() + ":");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, filtered.get(i).getName());
            }

            try {
                System.out.print("Select an industry: ");
                int industryChoice = Integer.parseInt(scanner.nextLine().trim());

                if (industryChoice < 1 || industryChoice > filtered.size()) {
                    System.out.println("❌ Invalid industry choice. Please select a valid option.");
                } else {
                    selectedData = filtered.get(industryChoice - 1);
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }

        Integer x = null;
        while (x == null) {
            try {
                System.out.print("Enter X coordinate: ");
                x = Integer.parseInt(scanner.nextLine().trim());
                if (x < 0) {
                    System.out.println("❌ X must be zero or positive.");
                    x = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        Integer y = null;
        while (y == null) {
            try {
                System.out.print("Enter Y coordinate: ");
                y = Integer.parseInt(scanner.nextLine().trim());
                if (y < 0) {
                    System.out.println("❌ Y must be zero or positive.");
                    y = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        try {
            Position pos = new Position(x, y);
            Industry industry = controller.addIndustry(selectedData, pos);

            System.out.println("\n✅ Industry added successfully!");
            System.out.println(industry);

            System.out.println("\nCurrent industries in the system:");
            List<Industry> allIndustries = controller.getAllIndustries();
            if (allIndustries.isEmpty()) {
                System.out.println("No industries have been added yet.");
            } else {
                for (Industry i : allIndustries) {
                    System.out.println("- " + i);
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

}
