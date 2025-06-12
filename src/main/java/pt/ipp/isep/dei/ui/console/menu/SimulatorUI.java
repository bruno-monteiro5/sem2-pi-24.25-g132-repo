package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.controller.SimulatorController;
import pt.ipp.isep.dei.repository.SimulatorRepository;

import java.util.Scanner;

public class SimulatorUI implements Runnable {

    private final SimulatorController controller;

    public SimulatorUI() {
        controller = new SimulatorController();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Simulator Menu ===");
            System.out.println("1. Start Simulation");
            System.out.println("2. Pause Simulation");
            System.out.println("3. Show Current Year");
            System.out.println("0. Exit");

            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    controller.startSimulation();
                    break;
                case "2":
                    controller.pauseSimulation();
                    break;
                case "3":
                    System.out.println("Current simulation year: " + controller.getCurrentYear());
                    break;
                case "0":
                    controller.pauseSimulation();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
