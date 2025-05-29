package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateScenarioController;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateScenarioUI implements Runnable {

    private final CreateScenarioController controller = new CreateScenarioController();

    public void run(Map map) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do cenário: ");
        String name = sc.nextLine();

        System.out.print("Ano de início: ");
        int startYear = sc.nextInt();
        System.out.print("Ano de fim: ");
        int endYear = sc.nextInt();
        sc.nextLine(); // limpar buffer

        PeriodTime periodTime = new PeriodTime(startYear, endYear);
        Scenario scenario = controller.createScenario(name, map, periodTime);

        // Tipos de locomotiva
        ArrayList<String> tiposEscolhidos = new ArrayList<>();


        System.out.println("Adicionar tipos de locomotiva (STEAM, DIESEL, ELECTRIC). Digite 'fim' para terminar:");
        String input = "";
        while (!input.equalsIgnoreCase("fim")) {
            System.out.print("Tipo: ");
            input = sc.nextLine().toUpperCase();

            if (!input.equalsIgnoreCase("FIM")) {
                if (input.equalsIgnoreCase("STEAM") || input.equalsIgnoreCase("DIESEL") || input.equalsIgnoreCase("ELECTRIC")) {
                    if (!tiposEscolhidos.contains(input.toUpperCase())) {
                        tiposEscolhidos.add(input.toUpperCase());
                    }
                } else {
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

            for (String tipo : new String[]{"importa", "exporta", "transforma"}) {
                System.out.println("Cargas que o porto " + tipo + " (digite 'fim' para terminar):");
                String carga = "";
                while (!carga.equalsIgnoreCase("fim")) {
                    System.out.print("> ");
                    carga = sc.nextLine();
                    if (!carga.equalsIgnoreCase("fim")) {
                        if (tipo.equals("importa")) port.addImport(carga);
                        if (tipo.equals("exporta")) port.addExport(carga);
                        if (tipo.equals("transforma")) port.addTransform(carga);
                    }
                }
            }

            controller.addPort(scenario, port);

            System.out.print("Deseja adicionar outro porto? (s/n): ");
            opcao = sc.nextLine();
        }

        // Adicionar apenas locomotivas permitidas
        for (LocomotiveType tipo : LocomotiveType.values()) {
            String fuel = tipo.getFuelType().toUpperCase();
            if (tiposEscolhidos.contains(fuel)) {
                controller.addLocomotiveType(scenario, tipo);
            }
        }

        // Guardar cenário
        if (controller.saveScenario(scenario)) {
            System.out.println("Cenário guardado com sucesso.");
        } else {
            System.out.println("Erro ao guardar o cenário.");
        }
    }

    @Override
    public void run() {
        MapRepository repo = Repositories.getInstance().getMapRepository();
        List<Map> maps = repo.getMaps();

        if (maps.isEmpty()) {
            System.out.println("Não existem mapas disponíveis. Crie um mapa primeiro.");
            return;
        }

        System.out.println("Escolha o mapa para associar ao cenário:");
        for (int i = 0; i < maps.size(); i++) {
            System.out.println((i + 1) + " - " + maps.get(i).getName());
        }

        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > maps.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Map mapaEscolhido = maps.get(escolha - 1);
        run(mapaEscolhido); // chama o método principal
    }
}