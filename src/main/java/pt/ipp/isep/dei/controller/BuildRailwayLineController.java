package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.List;

public class BuildRailwayLineController {
    private final MapRepository mapRepo;
    private final PlayerRepository playerRepo;
    private final StationRepository stationRepo;
    private final RailwayLineRepository railwayLineRepo;

    public BuildRailwayLineController() {
        Repositories repositories = Repositories.getInstance();
        this.mapRepo = repositories.getMapRepository();
        this.playerRepo = repositories.getPlayerRepository();
        this.stationRepo = repositories.getStationRepository();
        this.railwayLineRepo = repositories.getRailwayLineRepository();
    }

    public BuildRailwayLineController(MapRepository mapRepo, PlayerRepository playerRepo,
                                      StationRepository stationRepo, RailwayLineRepository railwayLineRepo) {
        this.mapRepo = mapRepo;
        this.playerRepo = playerRepo;
        this.stationRepo = stationRepo;
        this.railwayLineRepo = railwayLineRepo;
    }

    public List<Station> getAllStations() {
        return stationRepo.getAll();
    }

    public List<LineType> getAllLineTypes() {
        return List.of(LineType.values());
    }

    public boolean createRailwayLine(Station station1, Station station2, LineType lineType) {
        if (station1 == null || station2 == null || lineType == null) {
            return false;
        }

        if (station1.equals(station2)) {
            return false; // Cannot connect a station to itself
        }

        Map currentMap = mapRepo.getActiveMap();
        Player currentPlayer = playerRepo.getCurrentPlayer();

        if (currentMap == null || currentPlayer == null) {
            return false;
        }

        // Check if line already exists
        if (currentMap.getLineBetweenStations(station1, station2) != null) {
            return false;
        }

        // Calculate length based on stations' positions
        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) +
                Math.pow(pos1.getY() - pos2.getY(), 2));

        // Create the railway line
        RailwayLine newLine = new RailwayLine(currentMap.getScenarios().get(0), // Assuming first scenario
                station1, station2, lineType, length);

        // Check if player can afford it
        int cost = calculateLineCost(newLine);
        if (!currentPlayer.canAfford(cost)) {
            return false;
        }

        // Deduct funds and add the line
        //fix the error
        currentPlayer.deductFunds(cost);
        //currentMap.addRailwayLine(newLine);
        railwayLineRepo.addRailwayLine(newLine);

        return true;
    }

    private int calculateLineCost(RailwayLine line) {
        // Cost is based on line type and length
        switch (line.getLineType()) {
            case SINGLE_ELECTRIFIED:
                return line.getLength() * 11; // 11 per cell
            case DOUBLE_ELECTRIFIED:
                return line.getLength() * 14; // 14 per cell
            case SINGLE_NOT_ELECTRICITY:
                return line.getLength() * 6; // 6 per cell
            case DOUBLE_NOT_ELECTRICITY:
                return line.getLength() * 9; // 9 per cell
            default:
                return 0;
        }
    }

    public int estimateCost(Station station1, Station station2, LineType lineType) {
        if (station1 == null || station2 == null || lineType == null) {
            return 0;
        }

        Position pos1 = station1.getLocation();
        Position pos2 = station2.getLocation();
        int length = (int) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) +
                Math.pow(pos1.getY() - pos2.getY(), 2));

        switch (lineType) {
            case SINGLE_ELECTRIFIED:
                return length * 11;
            case DOUBLE_ELECTRIFIED:
                return length * 14;
            case SINGLE_NOT_ELECTRICITY:
                return length * 6;
            case DOUBLE_NOT_ELECTRICITY:
                return length * 9;
            default:
                return 0;
        }
    }
}