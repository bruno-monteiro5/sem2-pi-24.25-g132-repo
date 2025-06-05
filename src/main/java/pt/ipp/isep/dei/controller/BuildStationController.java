package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for managing the creation of stations,
 * map selection, and related functionality in the system.
 */
public class BuildStationController {

    private MapRepository mapRepo;
    private StationTypeRepository stationTypeRepository;
    private PlayerRepository playerRepo;

    public BuildStationController() {
        getMapRepo();
        getStationTypeRepository();
        getPlayerRepo();
    }

    public BuildStationController(MapRepository mapRepo, StationTypeRepository stationTypeRepository, PlayerRepository playerRepo) {
        this.mapRepo = mapRepo;
        this.stationTypeRepository = stationTypeRepository;
        this.playerRepo = playerRepo;
    }

    public MapRepository getMapRepo() {
        if (mapRepo == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepo = repositories.getMapRepository();
        }
        return mapRepo;
    }

    public StationTypeRepository getStationTypeRepository() {
        if (stationTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            stationTypeRepository = repositories.getStationTypeRepository();
        }
        return stationTypeRepository;
    }

    public PlayerRepository getPlayerRepo() {
        if (playerRepo == null) {
            Repositories repositories = Repositories.getInstance();
            playerRepo = repositories.getPlayerRepository();
        }
        return playerRepo;
    }

    public boolean validatePosition(Position position) {
        Map map = mapRepo.getActiveMap();
        return map.validatePosition(position);
    }

    public boolean createStation(Position position, StationType stationType, String name, String center) {
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
        if (isInsideOtherStationRadius(position, stationType, center)) {
            System.out.println("Error: you can't build a station inside another station radius.");
            return false;
        }


        Station newStation = new Station(name, position, stationType);
        boolean addedToMap = currentMap.addStation(newStation);
        currentPlayer.deductFunds(newStation.getType().getCost());

        if (!addedToMap) {
            System.out.println("Failed to add station to the map.");
            return false;
        }

        Repositories.getInstance().getStationRepository().addStation(newStation);

        return true;
    }

    public String getSuggestedName(Position position, StationType stationType) {
        Map currentMap = mapRepo.getActiveMap();
        return currentMap.getSuggestedName(position, stationType);
    }

    public boolean setLocationCenter(Position position, int x, int y) {
        if (position == null) return false;
        position = new Position(x, y);
        return true;
    }

    public Position createPositon(int x, int y) {
        return mapRepo.getActiveMap().createPosition(x, y);
    }

    public List<StationType> getStationTypes() {
        return stationTypeRepository.getAllStationTypes();
    }

    public void selectedMap(Map map) {
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


    // Verify if the station is inside the radius of another

    private boolean isInsideOtherStationRadius(Position newPos, StationType newType, String newDirection) {
        List<Position> areaNew;

        if (newType == StationType.STATION) {
            areaNew = getCornerSquare(newPos, newDirection);
        } else {
            int size = newType.getRadius() * 2 + 1; // ex: 3 → 7 (3x3), 5 → 11 (5x5)
            areaNew = getCenteredSquare(newPos, size);
        }

        for (Station existing : StationRepository.getInstance().getAll()) {
            Position existingPos = existing.getLocation();
            StationType existingType = existing.getType();
            List<Position> areaExisting;

            if (existingType == StationType.STATION) {
                // Se tiveres guardada a direção da station, usa-a aqui; se não tiveres, assume uma (ex: NW)
                areaExisting = getCornerSquare(existingPos, "NW");
            } else {
                int size = existingType.getRadius() * 2 + 1;
                areaExisting = getCenteredSquare(existingPos, size);
            }

            // Comparar áreas
            for (Position p1 : areaNew) {
                for (Position p2 : areaExisting) {
                    if (p1.equals(p2)) {
                        return true; // Conflito encontrado
                    }
                }
            }
        }

        return false; // Nenhuma sobreposição
    }




    private List<Position> getCenteredSquare(Position center, int size) {
        List<Position> positions = new ArrayList<>();

        int half = size / 2;
        int startX = center.getX() - half;
        int startY = center.getY() - half;

        for (int dx = 0; dx < size; dx++) {
            for (int dy = 0; dy < size; dy++) {
                positions.add(new Position(startX + dx, startY + dy));
            }
        }

        return positions;
    }


    private List<Position> getCornerSquare(Position corner, String direction) {
        List<Position> positions = new ArrayList<>();
        int size = 4;

        int startX = corner.getX();
        int startY = corner.getY();

        // Ajustar origem consoante o canto escolhido
        switch (direction.toUpperCase()) {
            case "NW":
                // já está no canto superior esquerdo
                break;
            case "NE":
                startX = startX - (size - 1);
                break;
            case "SW":
                startY = startY - (size - 1);
                break;
            case "SE":
                startX = startX - (size - 1);
                startY = startY - (size - 1);
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direction);
        }

        for (int dx = 0; dx < size; dx++) {
            for (int dy = 0; dy < size; dy++) {
                positions.add(new Position(startX + dx, startY + dy));
            }
        }

        return positions;
    }

    public List<Station> listAllStations() {
        return StationRepository.getInstance().getAll();
    }

    public Station getStationByName(String name) {
        return StationRepository.getInstance().getStationByName(name);
    }

}