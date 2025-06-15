package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.simulator.SimulatorEngine;
import pt.ipp.isep.dei.simulator.SimulationEvent;
import pt.ipp.isep.dei.ui.ApplicationSession;

import java.util.Scanner;

public class SimulatorUI implements Runnable {

    private SimulatorEngine engine;
    private final Scanner scanner = new Scanner(System.in);

    public SimulatorUI() {
    }

    public void run() {
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();

        if (scenario == null || player == null) {
            System.out.println("Scenario or player not loaded. Create or load one first.");
            return;
        }

        engine = new SimulatorEngine(scenario, player);

        int option;
        do {
            printMenu();
            option = readInt("Choose option: ");

            switch (option) {
                case 1 -> startSimulation();
                case 2 -> runNextEvent();
                case 3 -> pauseSimulation();
                case 4 -> listFutureEvents();
                case 5 -> listPastEvents();
                case 0 -> System.out.println("Leaving simulation menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n====== Simulation Menu ======");
        System.out.println("1. Start simulation");
        System.out.println("2. Advance an event");
        System.out.println("3. Pause simulation");
        System.out.println("4. See future events");
        System.out.println("5. See past events");
        System.out.println("0. Exit");
    }

    private void startSimulation() {
        boolean started = engine.start();
        if (started) {
            System.out.println("Simulation initiated.");
        } else {
            System.out.println("Simulation already running.");
        }
    }

    private void runNextEvent() {
        if (engine.hasMoreEvents()) {
            engine.runNextEvent();
        } else {
            System.out.println("There are no more events scheduled.");
        }
    }

    private void pauseSimulation() {
        engine.pause();
        System.out.println("Simulation paused.");
    }

    private void listFutureEvents() {
        System.out.println("\n→ Future events:");
        for (SimulationEvent event : engine.getFutureEvents()) {
            System.out.printf("Tick %d: %s%n", event.getScheduledTick(), event.getClass().getSimpleName());
        }
    }

    private void listPastEvents() {
        System.out.println("\n→ Past events:");
        for (SimulationEvent event : engine.getPastEvents()) {
            System.out.printf("Tick %d: %s%n", event.getScheduledTick(), event.getClass().getSimpleName());
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid value. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}