package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.controller.MapFileHandler;
import pt.ipp.isep.dei.domain.Map;

import java.io.IOException;
import java.util.Scanner;

public class LoadMapUI implements Runnable {

    private final CreateMapController controller = new CreateMapController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do ficheiro a carregar (ex: mapa1.dat): ");
        String filename = sc.nextLine();

        try {
            Map loadedMap = MapFileHandler.loadMapFromFile(filename);
            controller.getMapRepository().setActiveMap(loadedMap);
            System.out.println("Mapa carregado com sucesso: " + loadedMap.getName());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o mapa: " + e.getMessage());
        }
    }
}
