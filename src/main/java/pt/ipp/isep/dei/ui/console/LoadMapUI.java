package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.controller.MapFileHandler;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class LoadMapUI implements Runnable {

    private final CreateMapController controller = new CreateMapController();

    @Override
    public void run() {
        String filename = Utils.readLineFromConsole("Name of the file to load:");

        try {
            Map loadedMap = MapFileHandler.loadMapFromFile(filename);
            controller.getMapRepository().setActiveMap(loadedMap);
            System.out.println("Map loaded successfully: " + loadedMap.getName());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading the map: " + e.getMessage());
        }
    }
}
