package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Simulator;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.Scanner;

public class SimulatorUI implements Runnable {

    private Simulator simulator;

    @Override
    public void run() {
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();

        if (scenario == null) {
            System.out.println("No active scenario. Please create or activate a scenario first.");
            return;
        }

        simulator = new Simulator(scenario);

        Scanner sc = new Scanner(System.in);
        String option;

        do {
            System.out.println("\n=== Simulator ===");
            System.out.println("Current year: " + simulator.getCurrentYear());
            System.out.println("1. Simulate next event (year)");
            System.out.println("0. Exit simulator");
            System.out.print("Choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1":
                    simulator.simulateNextEvent();
                    break;
                case "0":
                    System.out.println("Exiting simulator...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (!option.equals("0"));
    }
}
