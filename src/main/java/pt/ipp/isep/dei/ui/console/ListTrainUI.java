package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ListTrainController;
import pt.ipp.isep.dei.domain.Train;

public class ListTrainUI implements Runnable {
    private final ListTrainController controller = new ListTrainController();

    @Override
    public void run() {
        System.out.println("\n=== List of Trains ===");
        System.out.println("ID | Locomotive Type | Cargo");
        System.out.println("---------------------------");

        for (Train train : controller.getAllTrainsSorted()) {
            System.out.printf(
                    "%d | %-12s | %s\n",
                    train.getId(),
                    train.getLocomotive().getType().getFuelType(),
                    train.getCargos()
            );
        }
    }
}