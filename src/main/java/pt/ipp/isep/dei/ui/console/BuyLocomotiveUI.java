package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuyLocomotiveController;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class BuyLocomotiveUI implements Runnable {

    private final Scanner scanner = new Scanner(System.in);
    private final BuyLocomotiveController controller = new BuyLocomotiveController();

    @Override
    public void run() {
        System.out.println("\n=== Buy a Locomotive ===");

        ScenarioRepository scenarioRepository = Repositories.getInstance().getScenarioRepository();
        Scenario scenario = scenarioRepository.getActiveScenario();

        if (scenario == null) {
            System.out.println("No scenario loaded. Please create one first");
            return;
        }

        ArrayList<LocomotiveType> availableTypes = scenario.getAvailableLocomotiveTypes();

        if (availableTypes.isEmpty()) {
            System.out.println("This scenario does not have any locomotives available.");
            return;
        }

        ArrayList<LocomotiveType> permitedLocomotives = new ArrayList<>();
        for (LocomotiveType tipo : LocomotiveType.values()) {
            if (availableTypes.contains(tipo)) {
                permitedLocomotives.add(tipo);
            }
        }

        for (int i = 0; i < permitedLocomotives.size(); i++) {
            LocomotiveType type = permitedLocomotives.get(i);
            System.out.printf("%d. %s (%s) - Speed: %.0f km/h, Price: %.0f$\n",
                    i + 1, type.getName(), type.getFuelType(), type.getTopSpeed(), type.getPrice());
        }

        try {
            System.out.print("Select a locomotive: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice < 1 || choice > permitedLocomotives.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            LocomotiveType selected = permitedLocomotives.get(choice - 1);
            Locomotive loco = controller.buyLocomotive(selected);

            System.out.println("\nLocomotive bought successfully!");
            System.out.println(loco);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}