package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateScenarioController;
import pt.ipp.isep.dei.controller.ScenarioFileHandler;
import pt.ipp.isep.dei.domain.Scenario;

import java.io.IOException;
import java.util.Scanner;

public class SaveScenarioUI implements Runnable {


    private final CreateScenarioController controller = new CreateScenarioController();

    @Override
    public void run() {
        Scenario currentScenario = controller.getActiveScenario();
        if (currentScenario == null) {
            System.out.println("No scenario loaded.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Name of the file to save: ");
        String filename = sc.nextLine();

        try {
            ScenarioFileHandler.saveScenarioToFile(currentScenario, filename);
            System.out.println("Scenario saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }
}
