package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.controller.SimulatorController;
import pt.ipp.isep.dei.repository.SimulatorRepository;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.PeriodTime;

import java.util.ArrayList;
import java.util.Scanner;

public class SimulatorUI implements Runnable {

    private final SimulatorController controller;

    public SimulatorUI() {
        ArrayList<Train> trains = new ArrayList<>();
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Locomotive> locomotives = new ArrayList<>();

        Map map = new Map("MapaTeste", 10, 10); // scale como String
        PeriodTime period = new PeriodTime(2024, 2030);
        Scenario scenario = new Scenario("CenárioTeste", map, period);
        int startYear = 2024;

        SimulatorRepository repository = new SimulatorRepository(trains, stations, locomotives, scenario, startYear);
        this.controller = new SimulatorController(repository);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== SIMULATOR MENU ===");
            System.out.println("1. Start Simulation");
            System.out.println("2. Pause Simulation");
            System.out.println("3. Advance One Year");
            System.out.println("4. Show Current Year");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = readInt(scanner);

            switch (option) {
                case 1:
                    controller.startSimulation();
                    System.out.println("Simulation started.");
                    break;
                case 2:
                    controller.pauseSimulation();
                    System.out.println("Simulation paused.");
                    break;
                case 3:

                    if (controller.isRunning()) {
                        controller.nextYear();
                        System.out.println("Advanced one year.");
                    } else {
                        System.out.println("Simulation is paused. Start it first.");
                    }
                    break;
                case 4:
                    System.out.println("Current year: " + controller.getCurrentYear());
                    break;
                case 0:
                    System.out.println("Exiting simulator menu.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
