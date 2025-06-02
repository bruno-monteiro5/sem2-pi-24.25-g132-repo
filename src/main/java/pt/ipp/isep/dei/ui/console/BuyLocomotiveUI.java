package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuyLocomotiveController;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class BuyLocomotiveUI implements Runnable {

    private final Scanner scanner = new Scanner(System.in);
    private final BuyLocomotiveController controller = new BuyLocomotiveController();

    @Override
    public void run() {
        System.out.println("\n=== Buy a Locomotive ===");

        ScenarioRepository scenarioRepository = Repositories.getInstance().getScenarioRepository();
        Scenario scenario = scenarioRepository.getActiveScenario();

        if (scenario == null) {
            System.out.println("Nenhum cenário ativo. Por favor, crie ou ative um cenário.");
            return;
        }

        ArrayList<LocomotiveType> tiposDisponiveis = scenario.getAvailableLocomotiveTypes();

        if (tiposDisponiveis.isEmpty()) {
            System.out.println("Este cenário não tem locomotivas disponíveis.");
            return;
        }

        ArrayList<LocomotiveType> locomotivasPermitidas = new ArrayList<>();
        for (LocomotiveType tipo : LocomotiveType.values()) {
            if (tiposDisponiveis.contains(tipo)) {
                locomotivasPermitidas.add(tipo);
            }
        }

        for (int i = 0; i < locomotivasPermitidas.size(); i++) {
            LocomotiveType tipo = locomotivasPermitidas.get(i);
            System.out.printf("%d. %s (%s) - Speed: %.0f km/h, Price: %.0f$\n",
                    i + 1, tipo.getName(), tipo.getFuelType(), tipo.getTopSpeed(), tipo.getPrice());
        }

        try {
            System.out.print("Enter a unique locomotive ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Select a locomotive type: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice < 1 || choice > locomotivasPermitidas.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            LocomotiveType selected = locomotivasPermitidas.get(choice - 1);
            Locomotive loco = controller.buyLocomotive(id, selected);

            System.out.println("\nLocomotive bought successfully!");
            System.out.println(loco);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}