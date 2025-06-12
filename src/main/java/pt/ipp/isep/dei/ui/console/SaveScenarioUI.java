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
            System.out.println("Nenhum cenário carregado.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do ficheiro para guardar (ex: cenario1.dat): ");
        String filename = sc.nextLine();

        try {
            ScenarioFileHandler.saveScenarioToFile(currentScenario, filename);
            System.out.println("Cenário guardado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao guardar o cenário: " + e.getMessage());
        }
    }
}
