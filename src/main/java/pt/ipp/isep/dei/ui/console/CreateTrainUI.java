package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateTrainController;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.LocomotiveRepository;

import java.util.List;
import java.util.Scanner;

public class CreateTrainUI implements Runnable {

    private final CreateTrainController controller = new CreateTrainController();
    private final LocomotiveRepository locomotiveRepository = Repositories.getInstance().getLocomotiveRepository();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("\n=== Create Train ===");

        List<Locomotive> locomotives = Repositories.getInstance().getScenarioRepository().getActiveScenario().getLocomotives();
        if (locomotives.isEmpty()) {
            System.out.println("No locomotives available. Buy one first.");
            return;
        }

        System.out.println("Available Locomotives:");
        for (int i = 0; i < locomotives.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, locomotives.get(i));
        }

        System.out.print("Select a locomotive to use in a train: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        if (choice < 1 || choice > locomotives.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Locomotive selectedLoco = locomotives.get(choice - 1);
        Train train = controller.createTrainFromLocomotive(selectedLoco);

        System.out.println("\nTrain created successfully:");
        System.out.println(train);
    }
}