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

        System.out.print("Scenario name: ");
        String name = sc.nextLine();

        System.out.print("Initial year: ");
        int startYear = sc.nextInt();
        System.out.print("Final year: ");
        int endYear = sc.nextInt();
        sc.nextLine();

        if (startYear > endYear) {
            System.out.println("Error: Initial year must be less than or equal to final year.");
            return;
        }

        PeriodTime periodTime = new PeriodTime(startYear, endYear);
        Scenario scenario = controller.createScenario(name, map, periodTime);
        map.getScenarios().add(scenario);

        ArrayList<String> chosenTypes = new ArrayList<>();

        System.out.println("Add types of locomotives (STEAM, DIESEL, ELECTRIC). Write 'end' to end:");
        String input = "";
        while (!input.equalsIgnoreCase("end")) {
            System.out.print("Type: ");
            input = sc.nextLine().toUpperCase();

            if (!input.equalsIgnoreCase("END")) {
                if (input.equals("STEAM") || input.equals("DIESEL") || input.equals("ELECTRIC")) {
                    if (!chosenTypes.contains(input)) {
                        chosenTypes.add(input);
                    }
                } else {
                    System.out.println("Invalid type.");
                }
            }
        }

        System.out.print("Factor of generating industries (eg: 1.0): ");
        double factor = sc.nextDouble();
        controller.setIndustryGenerationFactor(scenario, factor);
        sc.nextLine();

        System.out.print("Do you like to add ports? (y/n): ");
        String opcao = sc.nextLine();

        while (opcao.equalsIgnoreCase("y")) {
            System.out.print("Name of port: ");
            String portName = sc.nextLine();
            Port port = new Port(portName);

            for (String tipo : new String[]{"import", "export", "transform"}) {
                System.out.println("Cargoes that the port " + tipo + " (write 'end' to end):");
                String carga = "";
                while (!carga.equalsIgnoreCase("end")) {
                    System.out.print("> ");
                    carga = sc.nextLine();
                    if (!carga.equalsIgnoreCase("end")) {
                        if (tipo.equals("import")) port.addImport(carga);
                        if (tipo.equals("export")) port.addExport(carga);
                        if (tipo.equals("transform")) port.addTransform(carga);
                    }
                }
            }

            controller.addPort(scenario, port);

            System.out.print("Do you like to add annother port? (y/n): ");
            opcao = sc.nextLine();
        }

        for (LocomotiveType type : LocomotiveType.values()) {
            FuelType fuel = type.getFuelType();
            for (String choose : chosenTypes) {
                if ((choose.equals("STEAM") && fuel instanceof Steam) ||
                        (choose.equals("DIESEL") && fuel instanceof Diesel) ||
                        (choose.equals("ELECTRIC") && fuel instanceof Electric)) {
                    controller.addLocomotiveType(scenario, type);
                    break;
                }
            }
        }

        if (controller.saveScenario(scenario)) {
            Repositories.getInstance().getScenarioRepository().setActiveScenario(scenario);
            System.out.println("Scenario saved successfully.");
        } else {
            System.out.println("Error while saving scenario.");
        }
    }

    @Override
    public void run() {
        MapRepository repo = Repositories.getInstance().getMapRepository();
        List<Map> maps = repo.getMaps();

        if (maps.isEmpty()) {
            System.out.println("There are no maps available. Create one firt");
            return;
        }

        System.out.println("Choose the map to assign the scenario:");
        for (int i = 0; i < maps.size(); i++) {
            System.out.println((i + 1) + " - " + maps.get(i).getName());
        }

        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > maps.size()) {
            System.out.println("Invalid option.");
            return;
        }

        Map mapaEscolhido = maps.get(escolha - 1);
        Repositories.getInstance().getMapRepository().setActiveMap(mapaEscolhido);
        run(mapaEscolhido);
    }
}
