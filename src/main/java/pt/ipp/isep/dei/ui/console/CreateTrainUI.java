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
        System.out.println("\n=== Create Train with Multiple Locomotives ===");

        List<Locomotive> availableLocos = locomotiveRepository.getLocomotives();
        if (availableLocos.isEmpty()) {
            System.out.println("No locomotives available. Buy one first.");
            return;
        }

        Train train = controller.createEmptyTrain();

        boolean done = false;
        while (!done) {
            System.out.println("\nAvailable Locomotives:");
            for (int i = 0; i < availableLocos.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, availableLocos.get(i));
            }

            System.out.print("Select a locomotive to add (or 0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 0) {
                done = true;
            } else if (choice < 1 || choice > availableLocos.size()) {
                System.out.println("Invalid choice.");
            } else {
                Locomotive selected = availableLocos.get(choice - 1);
                train.addLocomotive(selected);
                availableLocos.remove(selected);
                System.out.println("Locomotive added to train.");
            }
        }

        if (train.getLocomotives().isEmpty()) {
            System.out.println("No locomotives added. Train not created.");
            return;
        }

        controller.saveTrain(train);
        System.out.println("\nTrain created successfully:");
        System.out.println(train);
    }
}
