package pt.ipp.isep.dei;

import pt.ipp.isep.dei.ui.MainFX;
import pt.ipp.isep.dei.ui.console.menu.MainMenuUI;

import java.util.Scanner;

/**
 * Entry point for the Railroad Simulator.
 * Asks the user whether to run in Console or JavaFX mode.
 */
public class Main {

    public static void main(String[] args) {
        boolean useConsole = askUserInterfaceMode();

        if (useConsole) {
            runConsoleMode();
        } else {
            runGuiMode(args);
        }
    }

    private static boolean askUserInterfaceMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the interface:");
        System.out.println("1 - Console");
        System.out.println("2 - JavaFX");
        System.out.print("Option: ");

        String input = scanner.nextLine().trim();
        return input.equals("1");
    }

    private static void runConsoleMode() {
        new Bootstrap().run();

        try {
            new MainMenuUI().run();
        } catch (Exception e) {
            System.out.println("An error occurred while running the application.");
            e.printStackTrace();
        }
    }

    private static void runGuiMode(String[] args) {
        MainFX.main(args);
    }
}
