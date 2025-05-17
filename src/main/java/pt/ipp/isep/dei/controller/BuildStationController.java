package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.StationType;
import pt.ipp.isep.dei.repository.*;

import java.util.List;

public class BuildStationController {

    private MapRepository mapRepo;
    private StationTypeRepository stationTypeRepository;
    private PlayerRepository playerRepo;

    public BuildStationController() {
        this.mapRepo = new MapRepository();
        this.stationTypeRepository = new StationTypeRepository();
        this.playerRepo = new PlayerRepository();
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

        if (currentMap == null || stationType == null || currentPlayer == null || position == null || name == null || !currentPlayer.canAfford(stationType.getCost())) {
           return false;
        }

        if (currentMap.buildStation(stationType, position, name)) {
            return currentPlayer.deductFunds(stationType.getCost());
        }

        return false;
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

}
