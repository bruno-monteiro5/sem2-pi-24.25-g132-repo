package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.CreateMapController;
import pt.ipp.isep.dei.controller.MapFileHandler;
import pt.ipp.isep.dei.domain.Map;

import java.io.IOException;
import java.util.Scanner;

public class SaveMapUI implements Runnable {

    private final CreateMapController controller = new CreateMapController();

    @Override
    public void run() {
        Map currentMap = controller.getMapRepository().getActiveMap();
        if (currentMap == null) {
            System.out.println("Nenhum mapa carregado/criado.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do ficheiro para guardar (ex: mapa1.dat): ");
        String filename = sc.nextLine();

        try {
            MapFileHandler.saveMapToFile(currentMap, filename);
            System.out.println("Mapa guardado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao guardar o mapa: " + e.getMessage());
        }
    }
}
