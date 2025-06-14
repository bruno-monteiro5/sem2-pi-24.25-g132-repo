package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for building railway lines between stations.
 */
public class BuildRailwayLineController {
    private final PlayerRepository playerRepo;
    private final StationRepository stationRepo;
    private final RailwayLineRepository railwayLineRepo;
    private final ScenarioRepository scenarioRepo;

    public BuildRailwayLineController() {
        Repositories repositories = Repositories.getInstance();
        this.playerRepo = repositories.getPlayerRepository();
        this.stationRepo = repositories.getStationRepository();
        this.railwayLineRepo = repositories.getRailwayLineRepository();
        this.scenarioRepo = repositories.getScenarioRepository();
    }

    public BuildRailwayLineController(PlayerRepository playerRepo, StationRepository stationRepo,
                                      RailwayLineRepository railwayLineRepo, ScenarioRepository scenarioRepo) {
        this.playerRepo = playerRepo;
        this.stationRepo = stationRepo;
        this.railwayLineRepo = railwayLineRepo;
        this.scenarioRepo = scenarioRepo;
    }

    public List<Station> getAllStations() {
        Scenario scenario = scenarioRepo.getActiveScenario();
        return (scenario == null) ? new ArrayList<>() : scenario.getStations();
    }

    public List<LineType> getAllLineTypes() {
        return List.of(LineType.values());
    }

    public boolean createRailwayLine(Scenario scenario, Station station1, Station station2, LineType lineType) {

        if (station1 == null || station2 == null || lineType == null || station1.equals(station2)) {
            return false;
        }

        Player currentPlayer = playerRepo.getCurrentPlayer();
        if (scenario == null || currentPlayer == null) {
            return false;
        }

        // Check if line already exists
        if (scenario.getLineBetweenStations(station1, station2) != null) {
            return false;
        }

        // Calculate length
        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));

        RailwayLine newLine = new RailwayLine(scenario, station1, station2, lineType, length);

        int cost = calculateLineCost(newLine);
        if (!currentPlayer.canAfford(cost)) {
            return false;
        }

        // Deduct funds and add the line to scenario and repo
        currentPlayer.deductFunds(cost);
        scenario.addRailwayLine(newLine);
        railwayLineRepo.addRailwayLine(newLine);

        return true;
    }

    private int calculateLineCost(RailwayLine line) {
        switch (line.getLineType()) {
            case SINGLE_ELECTRIFIED: return line.getLength() * 11;
            case DOUBLE_ELECTRIFIED: return line.getLength() * 14;
            case SINGLE_NOT_ELECTRICITY: return line.getLength() * 6;
            case DOUBLE_NOT_ELECTRICITY: return line.getLength() * 9;
            default: return 0;
        }
    }

    public int estimateCost(Station station1, Station station2, LineType lineType) {
        if (station1 == null || station2 == null || lineType == null) {
            return 0;
        }

        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));

        switch (lineType) {
            case SINGLE_ELECTRIFIED: return length * 11;
            case DOUBLE_ELECTRIFIED: return length * 14;
            case SINGLE_NOT_ELECTRICITY: return length * 6;
            case DOUBLE_NOT_ELECTRICITY: return length * 9;
            default: return 0;
        }
    }
}
