package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.controller.MapFileHandler;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class SaveMapUI implements Runnable {

    private final CreateMapController controller = new CreateMapController();

    @Override
    public void run() {
        Map currentMap = controller.getMapRepository().getActiveMap();
        if (currentMap == null) {
            System.out.println("No map loaded");
            return;
        }

        String filename = Utils.readLineFromConsole("Name of the file to save the map:");

        try {
            MapFileHandler.saveMapToFile(currentMap, filename);
            System.out.println("Map saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving the map: " + e.getMessage());
        }
    }
}
