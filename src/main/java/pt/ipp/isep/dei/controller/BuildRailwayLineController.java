/*
package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.LineType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.repository.Repositories;

public class BuildRailwayLineController {
    public void buildRailwayLine(Scenario scenario, Station station1, Station station2, LineType type) {
        boolean success = true;
        int lineCost;

        switch (type) {
            case DOUBLE_NOT_ELECTRICITY:
            case SINGLE_ELECTRIFIED:
                lineCost = 9;
                break;
            case DOUBLE_ELECTRIFIED:
                lineCost = 12;
                break;
            case SINGLE_NOT_ELECTRICITY: default:
                lineCost = 6;
                break;
        }

        if (scenario.getPlayersMoney() - lineCost < 0) {
            success = false;
            System.out.println("Error: Insufficient money for the requested operation.");
        }

        if (station1 == station2) {
            success = false;
            System.out.println("Error: Two different stations are needed in order to build a railway line.");
        }

        if (Repositories.getInstance().getRailwayLineRepository().connexionExists(scenario, station1, station2)) {
            success = false;
            System.out.println("Error: The stations are already connected by another line.");
        }

        if (success) {
            scenario.spendPlayersMoney(lineCost);
            Repositories.getInstance().getRailwayLineRepository().addRailwayLine(scenario, station1, station2, type);
            System.out.println("Railway line built successfully.");
        }
    }
}

*/