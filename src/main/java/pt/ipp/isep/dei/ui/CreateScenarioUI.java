package pt.ipp.isep.dei.ui;

import pt.ipp.isep.dei.controller.CreateScenarioController;
import pt.ipp.isep.dei.domain.*;

import java.util.Scanner;

public class CreateScenarioUI {

    private final CreateScenarioController controller = new CreateScenarioController();

    public void run(Map map) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do cenário: ");
        String name = sc.nextLine();

        Scenario scenario = controller.createScenario(name, map);

        // Locomotivas
        System.out.println("Adicionar tipos de locomotiva disponíveis (STEAM, DIESEL, ELECTRIC). Digite 'fim' para terminar:");
        String input = "";
        while (!input.equals("fim")) {
            System.out.print("Tipo: ");
            input = sc.nextLine().toUpperCase();

            if (!input.equals("FIM")) {
                try {
                    LocomotiveType type = LocomotiveType.valueOf(input);
                    controller.addLocomotiveType(scenario, type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo inválido.");
                }
            }
        }

        // Fator de geração
        System.out.print("Fator de geração das indústrias (ex: 1.0): ");
        double factor = sc.nextDouble();
        controller.setIndustryGenerationFactor(scenario, factor);
        sc.nextLine(); // limpar buffer

        // Portos
        System.out.print("Deseja adicionar portos? (s/n): ");
        String opcao = sc.nextLine();

        while (opcao.equalsIgnoreCase("s")) {
            System.out.print("Nome do porto: ");
            String portName = sc.nextLine();
            Port port = new Port(portName);

            // Importações
            System.out.println("Cargas que o porto importa (digite 'fim' para terminar):");
            String carga = "";
            while (!carga.equalsIgnoreCase("fim")) {
                System.out.print("> ");
                carga = sc.nextLine();
                if (!carga.equalsIgnoreCase("fim")) {
                    port.addImport(carga);
                }
            }

            // Exportações
            System.out.println("Cargas que o porto exporta (digite 'fim' para terminar):");
            carga = "";
            while (!carga.equalsIgnoreCase("fim")) {
                System.out.print("> ");
                carga = sc.nextLine();
                if (!carga.equalsIgnoreCase("fim")) {
                    port.addExport(carga);
                }
            }

            // Transformações
            System.out.println("Cargas que o porto transforma (digite 'fim' para terminar):");
            carga = "";
            while (!carga.equalsIgnoreCase("fim")) {
                System.out.print("> ");
                carga = sc.nextLine();
                if (!carga.equalsIgnoreCase("fim")) {
                    port.addTransform(carga);
                }
            }

            controller.addPort(scenario, port);

            System.out.print("Deseja adicionar outro porto? (s/n): ");
            opcao = sc.nextLine();
        }

        // Guardar
        if (controller.saveScenario(scenario)) {
            System.out.println("Cenário guardado com sucesso.");
        } else {
            System.out.println("Erro ao guardar o cenário.");
        }
    }
}