package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for building railway lines between two stations.
 * <p>
 * Handles validation, cost calculation, and connection logic.
 */
public class BuildRailwayLineController {

    /**
     * Repository that manages the current player.
     */
    private final PlayerRepository playerRepo;

    /**
     * Repository of stations used in the map.
     */
    private final StationRepository stationRepo;

    /**
     * Repository to store and retrieve railway lines.
     */
    private final RailwayLineRepository railwayLineRepo;

    /**
     * Repository for accessing the current scenario.
     */
    private final ScenarioRepository scenarioRepo;

    /**
     * Constructs the controller using the default repositories.
     */
    public BuildRailwayLineController() {
        Repositories repositories = Repositories.getInstance();
        this.playerRepo = repositories.getPlayerRepository();
        this.stationRepo = repositories.getStationRepository();
        this.railwayLineRepo = repositories.getRailwayLineRepository();
        this.scenarioRepo = repositories.getScenarioRepository();
    }

    /**
     * Constructs the controller with custom repositories (for testing or override).
     */
    public BuildRailwayLineController(PlayerRepository playerRepo, StationRepository stationRepo,
                                      RailwayLineRepository railwayLineRepo, ScenarioRepository scenarioRepo) {
        this.playerRepo = playerRepo;
        this.stationRepo = stationRepo;
        this.railwayLineRepo = railwayLineRepo;
        this.scenarioRepo = scenarioRepo;
    }

    /**
     * Returns all stations available in the active scenario.
     *
     * @return list of stations or empty list if no active scenario
     */
    public List<Station> getAllStations() {
        Scenario scenario = scenarioRepo.getActiveScenario();
        return (scenario == null) ? new ArrayList<>() : scenario.getStations();
    }

    /**
     * Returns all available railway line types.
     *
     * @return list of line types
     */
    public List<LineType> getAllLineTypes() {
        return List.of(LineType.values());
    }

    /**
     * Creates a railway line between two stations if valid and affordable.
     *
     * @param scenario  the current scenario
     * @param station1  the first station
     * @param station2  the second station
     * @param lineType  the type of line to build
     * @return true if the line was successfully built; false otherwise
     */
    public boolean createRailwayLine(Scenario scenario, Station station1, Station station2, LineType lineType) {
        if (station1 == null || station2 == null || lineType == null || station1.equals(station2)) {
            return false;
        }

        Player currentPlayer = playerRepo.getCurrentPlayer();
        if (scenario == null || currentPlayer == null) {
            return false;
        }

        if (scenario.getLineBetweenStations(station1, station2) != null) {
            return false;
        }

        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));

        RailwayLine newLine = new RailwayLine(scenario, station1, station2, lineType, length);
        int cost = calculateLineCost(newLine);

        if (!currentPlayer.canAfford(cost)) {
            return false;
        }

        currentPlayer.deductFunds(cost);
        scenario.addRailwayLine(newLine);
        railwayLineRepo.addRailwayLine(newLine);

        return true;
    }

    /**
     * Calculates the cost of a railway line based on its type and length.
     *
     * @param line the railway line
     * @return total cost in game currency
     */
    private int calculateLineCost(RailwayLine line) {
        return switch (line.getLineType()) {
            case SINGLE_ELECTRIFIED -> line.getLength() * 11;
            case DOUBLE_ELECTRIFIED -> line.getLength() * 14;
            case SINGLE_NOT_ELECTRICITY -> line.getLength() * 6;
            case DOUBLE_NOT_ELECTRICITY -> line.getLength() * 9;
        };
    }

    /**
     * Estimates the cost of a line between two stations with a given type.
     *
     * @param station1 the first station
     * @param station2 the second station
     * @param lineType the type of the line
     * @return estimated cost, or 0 if invalid input
     */
    public int estimateCost(Station station1, Station station2, LineType lineType) {
        if (station1 == null || station2 == null || lineType == null) {
            return 0;
        }

        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));

        return switch (lineType) {
            case SINGLE_ELECTRIFIED -> length * 11;
            case DOUBLE_ELECTRIFIED -> length * 14;
            case SINGLE_NOT_ELECTRICITY -> length * 6;
            case DOUBLE_NOT_ELECTRICITY -> length * 9;
        };
    }
}

