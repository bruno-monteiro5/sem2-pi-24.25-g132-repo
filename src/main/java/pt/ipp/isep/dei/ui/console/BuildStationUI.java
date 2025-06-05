package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.BuildStationController;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.StationType;
import java.util.List;
import java.util.Scanner;

/**
 * User Interface for building a station.
 *
 * <p>This class provides a console interface for the user to create a new station.
 * It interacts with the user to collect the necessary information (name, position,
 * and station type) and uses the {@code BuildStationController} to process the
 * input and build the station.</p>
 *
 * <p>The {@code BuildStationUI} class implements {@code Runnable}, meaning it can
 * be executed in its own thread if needed.</p>
 */
public class BuildStationUI implements Runnable {
    private final BuildStationController controller;
    private Position position;
    private StationType type;
    private String name;
    private String center;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs the BuildStationUI and initializes necessary dependencies.
     */
    public BuildStationUI() {
        controller = new BuildStationController();
    }

    /**
     * Entry point for executing the user interface logic.
     *
     * <p>This method interacts with the user to gather input and initiate the
     * station building process. It uses the controller to process the input.</p>
     */
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

            switch (choice) {
                case 1:
                    center = "NE";
                    break;
                case 2:
                    center = "NW";
                    break;
                case 3:
                    center = "SE";
                    break;
                case 4:
                    center = "SW";
                    break;
            }
        }else{
            center = null;
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

    /**
     * Finalizes the station-building process by submitting the collected data.
     *
     * <p>This method validates the user input and submits the station details,
     * delegating the creation process to the controller.</p>
     */
    private void submit (){
        System.out.println("Submitting station creation...");

        boolean success=controller.createStation(position,type,name, center);

        if (success){
            System.out.println("Station created successfully!");
        }else {
            System.out.println("Error: Station could not be created");
        }
    }
}
