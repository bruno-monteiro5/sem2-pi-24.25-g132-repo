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

        //type
        System.out.println("Select a station type:");
        System.out.printf("1-Depot (Cost: 50000, Radius:3) %n 2-Station (Cost: 100000, Radius:4) %n 3-Terminal (Cost:200000, Radius:5) %n");
        int answer = -1;
        while (answer != 1 && answer != 2 && answer != 3) {
            answer = scanner.nextInt();
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
        }

        if (!controller.validatePosition(position)) {
            System.out.println("Invalid position");
            return;
        }

        //name

        String suggested = controller.getSuggestedName(position, type);
        System.out.printf("Suggested name: %s", suggested);
        System.out.println("Use the suggested name(Y/N):");
        String decision = scanner.nextLine().trim().toUpperCase();

        while (!decision.equals("Y") && !decision.equals("N")) {
            System.out.println("Invalid answer");
        }

        if (decision.equals("Y")) {
            name = suggested;
        } else {
            String custom = "";
            while (custom.isEmpty()) {
                System.out.println("Type a custom name:");
                custom = scanner.nextLine().trim();
                if (custom.isEmpty()) {
                    System.out.println("Invalid name");
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
