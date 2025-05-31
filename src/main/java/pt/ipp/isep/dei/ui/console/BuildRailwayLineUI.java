/*
package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.Extras;
import pt.ipp.isep.dei.controller.BuildRailwayLineController;
import pt.ipp.isep.dei.domain.LineType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;

public class BuildRailwayLineUI implements Runnable {
    @Override
    public void run() {
        Scenario scenario = Repositories.getInstance().getScenarioRepository().getCurrentScenario();

        if (scenario != null) {
            Station station1 = (Station) Utils.showAndSelectOne(Repositories.getInstance().getStationRepository().getStations(scenario), "Choose the first station.");

            if (station1 != null) {
                Station station2 = (Station) Utils.showAndSelectOne(Repositories.getInstance().getStationRepository().getStations(scenario), "Choose another station to connect it to.");

                if (station2 != null) {
                    int lineTypeInt;
                    ArrayList<LineType> availableTypes = new ArrayList<>();

                    availableTypes.add(LineType.SINGLE_NOT_ELECTRICITY);
                    availableTypes.add(LineType.DOUBLE_NOT_ELECTRICITY);

                    if (scenario.getCurrentDate().isAfter(Extras.CONTEMPORARY_ERA_START)) {
                        availableTypes.add(LineType.SINGLE_ELECTRIFIED);
                        availableTypes.add(LineType.DOUBLE_ELECTRIFIED);
                    }

                    lineTypeInt = Utils.showAndSelectIndex(availableTypes, "Choose the line type from the following available types (current date: " + scenario.getCurrentDate() + ").");

                    if (lineTypeInt != -1) {
                        LineType lineType = LineType.values()[lineTypeInt];
                        BuildRailwayLineController buildRailwayLineController = new BuildRailwayLineController();

                        buildRailwayLineController.buildRailwayLine(scenario, station1, station2, lineType);
                    }
                }
            }
        } else {
            System.out.println("Error: no scenario is currently selected.");
        }
    }
}

 */