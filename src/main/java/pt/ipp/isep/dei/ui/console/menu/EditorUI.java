package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EditorUI implements Runnable {

    public EditorUI() {}

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Map", new CreateMapUI()));
        options.add(new MenuItem("Add Industry", new AddIndustryUI()));
        options.add(new MenuItem("Create City", new CreateCityUI()));
        options.add(new MenuItem("Create Scenario", new CreateScenarioUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}