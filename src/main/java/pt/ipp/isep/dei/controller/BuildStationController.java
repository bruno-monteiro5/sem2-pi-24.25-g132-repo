package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.List;

public class BuildStationController {

    private MapRepository mapRepo;
    private StationTypeRepository stationTypeRepository;
    private PlayerRepository playerRepo;

    public BuildStationController() {
        getMapRepo();
        getStationTypeRepository();
        getPlayerRepo();
    }

    public BuildStationController (MapRepository mapRepo, StationTypeRepository stationTypeRepository, PlayerRepository playerRepo) {
        this.mapRepo = mapRepo;
        this.stationTypeRepository = stationTypeRepository;
        this.playerRepo = playerRepo;
    }

    public MapRepository getMapRepo() {
        if (mapRepo == null){
            Repositories repositories = Repositories.getInstance();
            mapRepo = repositories.getMapRepository();
        }

        return mapRepo;
    }

    public StationTypeRepository getStationTypeRepository() {
        if (stationTypeRepository == null){
            Repositories repositories = Repositories.getInstance();
            stationTypeRepository = repositories.getStationTypeRepository();
        }
        return stationTypeRepository;
    }

    public PlayerRepository getPlayerRepo() {
        if (playerRepo == null){
            Repositories repositories = Repositories.getInstance();
            playerRepo = repositories.getPlayerRepository();
        }
        return playerRepo;
    }

    public boolean validatePosition(Position position) {
        Map map = mapRepo.getActiveMap();

        return map.validatePosition(position);
    }

    public boolean createStation (Position position, StationType stationType, String name) {
        Map currentMap = mapRepo.getActiveMap();
        Player currentPlayer = playerRepo.getCurrentPlayer();

        if (currentMap == null) {
            System.out.println("DEBUG: No active map.");
            return false;
        }
        if (stationType == null) {
            System.out.println("DEBUG: Station type is null.");
            return false;
        }
        if (currentPlayer == null) {
            System.out.println("DEBUG: No player in session.");
            return false;
        }
        if (position == null) {
            System.out.println("DEBUG: Position is null.");
            return false;
        }
        if (name == null) {
            System.out.println("DEBUG: Name is null.");
            return false;
        }
        if (!currentPlayer.canAfford(stationType.getCost())) {
            System.out.println("DEBUG: Player can't afford the station.");
            return false;
        }
        Station newStation = new Station(name, position, stationType);
        boolean addedToMap = currentMap.addStation(newStation);

        if (!addedToMap) {
            System.out.println("Failed to add station to the map.");
            return false;
        }

        StationDetailsRepository.getInstance().addStationFromStation(newStation);

        return true;

    }

    public String getSuggestedName (Position position, StationType stationType) {
        Map currentMap = mapRepo.getActiveMap();

        return currentMap.getSuggestedName(position, stationType);
    }

    public boolean setLocationCenter(Position position, int x, int y) {
        if (position == null)
            return false;
        position = new Position(x, y);
        return true;
    }

    public Position createPositon (int x, int y) {
        return mapRepo.getActiveMap().createPosition(x, y);
    }

    public List<StationType> getStationTypes (){
        return stationTypeRepository.getAllStationTypes();
    }

    public void selectedMap (Map map) {
        mapRepo.setActiveMap(map);
    }

    public boolean hasActiveMap() {
        try {
            mapRepo.getActiveMap();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }

    }

}
