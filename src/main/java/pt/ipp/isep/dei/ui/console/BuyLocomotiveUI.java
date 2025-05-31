package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuyLocomotiveController;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;

import java.util.Scanner;

public class BuyLocomotiveUI implements Runnable {

    private final Scanner scanner = new Scanner(System.in);
    private final BuyLocomotiveController controller = new BuyLocomotiveController();

    @Override
    public void run() {
        System.out.println("\n=== Buy a Locomotive ===");

        try {
            System.out.print("Enter a unique locomotive ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            LocomotiveType[] types = LocomotiveType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.printf("%d. %s (%s) - Speed: %.0f km/h, Price: %.0f$\n",
                        i + 1, types[i].getName(), types[i].getFuelType(), types[i].getTopSpeed(), types[i].getPrice());
            }

            System.out.print("Select a locomotive type: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice < 1 || choice > types.length) {
                System.out.println("Invalid choice.");
                return;
            }

            LocomotiveType selected = types[choice - 1];
            Locomotive loco = controller.buyLocomotive(id, selected);

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
