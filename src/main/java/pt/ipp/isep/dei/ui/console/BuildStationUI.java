package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildStationController;
import pt.ipp.isep.dei.domain.Position;

import pt.ipp.isep.dei.domain.StationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildStationUI implements Runnable {
    private final BuildStationController controller;
    private Position position;
    private StationType type;
    private String name;
    Scanner scanner = new Scanner(System.in);

    public BuildStationUI() {
        controller = new BuildStationController();
    }

    public void run() {
        System.out.println("=== Build Station ===");

        //check map
        if(!controller.hasActiveMap()){
            System.out.println("No active map");
            return;
        }


        //type
        // Escolher tipo
        System.out.println("Select a station type:");
        System.out.println("1 - Depot (Cost: 50000, Radius: 3)");
        System.out.println("2 - Station (Cost: 100000, Radius: 4)");
        System.out.println("3 - Terminal (Cost: 200000, Radius: 5)");

        int answer = -1;
        while (answer < 1 || answer > 3) {
            System.out.print("Enter choice (1-3): ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine(); // limpar o \n restante
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // descartar entrada inválida
            }
        }

        List<StationType> types = controller.getStationTypes();
        type = types.get(answer - 1);


        //Position

        System.out.println("Enter X coordinate:");
        int x = scanner.nextInt();
        System.out.println("Enter Y coordinate:");
        int y = scanner.nextInt();

        position = controller.createPositon(x,y);

        if(type.needsCenter()){
            System.out.println("Choose the center:");
            System.out.println("1 - NE");
            System.out.println("2 - NW");
            System.out.println("3 - SE");
            System.out.println("4 - SW");

            int choice = -1;
            while (choice < 1 || choice > 4) {
                System.out.print("Enter choice (1-4): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                }else
                    scanner.next();
            }
            int centerX = x;
            int centerY = y;
            switch (choice) {
                case 1:
                    centerX = position.getX() + 1;
                    centerY = position.getY() + 1;
                    break;
                case 2:
                    centerX = position.getX() - 1;
                    centerY = position.getY() + 1;
                    break;
                case 3:
                    centerX = position.getX() + 1;
                    centerY = position.getY() - 1;
                    break;
                case 4:
                    centerX = position.getX() - 1;
                    centerY = position.getY() - 1;
                    break;
            }

            controller.setLocationCenter(position, centerX, centerY);

        }

        if (!controller.validatePosition(position)) {
            System.out.println("Invalid position");
            return;
        }

        //name

        // Gerar nome sugerido com base na posição e tipo
        String suggested = controller.getSuggestedName(position, type);
        System.out.printf("Suggested name: %s%n", suggested);

        String decision;
        do {
            System.out.print("Use the suggested name (Y/N): ");
            decision = scanner.next().trim().toUpperCase();

            if (!decision.equals("Y") && !decision.equals("N")) {
                System.out.println("Invalid answer. Please type Y or N.");
            }
        } while (!decision.equals("Y") && !decision.equals("N"));




        if (decision.equals("Y")) {
            name = suggested;
        } else {
            String custom = "";
            while (custom.isEmpty()) {
                System.out.print("Type a custom name: ");
                custom = scanner.next().trim();
                if (custom.isEmpty()) {
                    System.out.println("Invalid name. Please try again.");
                }
            }
            name = custom;
        }

        submit();
    }


    private void submit (){
        boolean success=controller.createStation(position,type,name);

        if (success){
            System.out.println("Station created successfully!");
        }else {
            System.out.println("Error: Station could not be created");
        }
    }
}
