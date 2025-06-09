package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.MainFX;
import pt.ipp.isep.dei.ui.console.DevTeamUI;
import pt.ipp.isep.dei.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.ui.console.utils.Utils;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;

import java.util.ArrayList;
import java.util.List;

public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Launch JavaFX", this::launchJavaFX));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    private void launchJavaFX() {
        Map map = Repositories.getInstance().getMapRepository().getLastCreated();
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getLastCreated();

        if (map == null || scenario == null) {
            System.out.println("You must create a map and scenario before launching JavaFX.");
            return;
        }

        ApplicationSession.getInstance().setCurrentMap(map);
        ApplicationSession.getInstance().setCurrentScenario(scenario);

        try {
            MainFX.launch(MainFX.class);
        } catch (Exception e) {
            System.out.println("Error launching JavaFX: " + e.getMessage());
        }
    }
}
