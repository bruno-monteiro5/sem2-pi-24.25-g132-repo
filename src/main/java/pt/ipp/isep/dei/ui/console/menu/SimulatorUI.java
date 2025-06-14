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
        // Construtor vazio — compatível com menu principal
    }

    public void run() {
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();

        if (scenario == null || player == null) {
            System.out.println("Cenário ou jogador não estão carregados. Crie ou carregue um primeiro.");
            return;
        }

        engine = new SimulatorEngine(scenario, player);

        int option;
        do {
            printMenu();
            option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> startSimulation();
                case 2 -> runNextEvent();
                case 3 -> pauseSimulation();
                case 4 -> listFutureEvents();
                case 5 -> listPastEvents();
                case 0 -> System.out.println("A sair do menu da simulação...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n====== Menu da Simulação ======");
        System.out.println("1. Iniciar simulação");
        System.out.println("2. Avançar um evento");
        System.out.println("3. Pausar simulação");
        System.out.println("4. Ver eventos futuros");
        System.out.println("5. Ver eventos passados");
        System.out.println("0. Sair");
    }

    private void startSimulation() {
        boolean started = engine.start();
        if (started) {
            System.out.println("Simulação iniciada.");
        } else {
            System.out.println("Simulação já está a correr.");
        }
    }

    private void runNextEvent() {
        if (engine.hasMoreEvents()) {
            engine.runNextEvent();
        } else {
            System.out.println("Não há mais eventos agendados.");
        }
    }

    private void pauseSimulation() {
        engine.pause();
        System.out.println("Simulação pausada.");
    }

    private void listFutureEvents() {
        System.out.println("\n→ Eventos futuros:");
        for (SimulationEvent event : engine.getFutureEvents()) {
            System.out.printf("Tick %d: %s%n", event.getScheduledTick(), event.getClass().getSimpleName());
        }
    }

    private void listPastEvents() {
        System.out.println("\n→ Eventos passados:");
        for (SimulationEvent event : engine.getPastEvents()) {
            System.out.printf("Tick %d: %s%n", event.getScheduledTick(), event.getClass().getSimpleName());
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. Tente de novo: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}