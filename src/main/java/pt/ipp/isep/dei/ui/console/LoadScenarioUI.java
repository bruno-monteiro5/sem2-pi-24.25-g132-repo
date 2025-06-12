package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateScenarioController;
import pt.ipp.isep.dei.controller.ScenarioFileHandler;
import pt.ipp.isep.dei.domain.Scenario;

import java.io.IOException;
import java.util.Scanner;

public class LoadScenarioUI implements Runnable {
    private final CreateScenarioController controller = new CreateScenarioController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do ficheiro a carregar (ex: cenario1.dat): ");
        String filename = sc.nextLine();

        try {
            Scenario scenario = ScenarioFileHandler.loadScenarioFromFile(filename);
            controller.setCurrentScenario(scenario);
            controller.getScenarioRepo().add(scenario);
            System.out.println("Cenário carregado: " + scenario.getName());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar cenário: " + e.getMessage());
        }
    }
}
