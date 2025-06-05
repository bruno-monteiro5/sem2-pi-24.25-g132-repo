package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for managing the creation of stations,
 * map selection, and related functionality in the system.
 *
 * <p>This class provides functionalities to:</p>
 * <ul>
 *     <li>Access and manage map, station type, and player repositories.</li>
 *     <li>Validate the position and area before creating a station.</li>
 *     <li>Create stations if funds and spatial rules allow it.</li>
 *     <li>Suggest a station name based on proximity to cities.</li>
 *     <li>Check for overlapping areas with existing stations.</li>
 *     <li>List existing stations or fetch one by name.</li>
 *     <li>Support map selection and position creation.</li>
 *     <li>Set the geometric center for certain station types.</li>
 * </ul>
 */
public class BuildStationController {

    /**
     * Repository for accessing and managing maps.
     */
    private MapRepository mapRepo;

    /**
     * Repository for retrieving station types.
     */
    private StationTypeRepository stationTypeRepository;

    /**
     * Repository for accessing player information and state.
     */
    private PlayerRepository playerRepo;

    /**
     * Default constructor that initializes repositories from the singleton.
     */
    public BuildStationController() {
        getMapRepo();
        getStationTypeRepository();
        getPlayerRepo();
    }

    /**
     * Constructs a controller with the provided repositories.
     */
    public BuildStationController(MapRepository mapRepo, StationTypeRepository stationTypeRepository, PlayerRepository playerRepo) {
        this.mapRepo = mapRepo;
        this.stationTypeRepository = stationTypeRepository;
        this.playerRepo = playerRepo;
    }

    /**
     * Retrieves the map repository, initializing it if necessary.
     */
    public MapRepository getMapRepo() {
        if (mapRepo == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepo = repositories.getMapRepository();
        }
        return mapRepo;
    }

    /**
     * Retrieves the station type repository, initializing it if necessary.
     */
    public StationTypeRepository getStationTypeRepository() {
        if (stationTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            stationTypeRepository = repositories.getStationTypeRepository();
        }
        return stationTypeRepository;
    }

    /**
     * Retrieves the player repository, initializing it if necessary.
     */
    public PlayerRepository getPlayerRepo() {
        if (playerRepo == null) {
            Repositories repositories = Repositories.getInstance();
            playerRepo = repositories.getPlayerRepository();
        }
        return playerRepo;
    }

    /**
     * Validates whether the given position is valid on the active map.
     */
    public boolean validatePosition(Position position) {
        Map map = mapRepo.getActiveMap();
        return map.validatePosition(position);
    }

    /**
     * Attempts to create a new station on the active map for the current player.
     *
     * @param position the intended station position
     * @param stationType the type of station to create
     * @param name the name to assign to the station
     * @param center the direction for center alignment (only relevant for Station type)
     * @return true if the station was successfully created; false otherwise
     */
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

    /**
     * Suggests a name for a station based on the closest city to a given position.
     */
    public String getSuggestedName(Position position, StationType stationType) {
        Map currentMap = mapRepo.getActiveMap();
        return currentMap.getSuggestedName(position, stationType);
    }

    /**
     * Updates a position by creating a new one with the given coordinates.
     */
    public boolean setLocationCenter(Position position, int x, int y) {
        if (position == null) return false;
        position = new Position(x, y);
        return true;
    }

    /**
     * Creates a new position from coordinates on the active map.
     */
    public Position createPositon(int x, int y) {
        return mapRepo.getActiveMap().createPosition(x, y);
    }

    /**
     * Retrieves all available station types from the repository.
     */
    public List<StationType> getStationTypes() {
        return stationTypeRepository.getAllStationTypes();
    }

    /**
     * Sets the provided map as the active map.
     */
    public void selectedMap(Map map) {
        mapRepo.setActiveMap(map);
    }

    /**
     * Checks if there is an active map selected.
     */
    public boolean hasActiveMap() {
        try {
            mapRepo.getActiveMap();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }


    /**
     * Checks if a new station would overlap with existing station areas.
     */
    private boolean isInsideOtherStationRadius(Position newPos, StationType newType, String newDirection) {
        List<Position> areaNew;

        if (newType == StationType.STATION) {
            areaNew = getCornerSquare(newPos, newDirection);
        } else {
            int size = newType.getRadius() * 2 + 1;
            areaNew = getCenteredSquare(newPos, size);
        }

        for (Station existing : StationRepository.getInstance().getAll()) {
            Position existingPos = existing.getLocation();
            StationType existingType = existing.getType();
            List<Position> areaExisting;

            if (existingType == StationType.STATION) {
                areaExisting = getCornerSquare(existingPos, "NW");
            } else {
                int size = existingType.getRadius() * 2 + 1;
                areaExisting = getCenteredSquare(existingPos, size);
            }

            for (Position p1 : areaNew) {
                for (Position p2 : areaExisting) {
                    if (p1.equals(p2)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Generates a square of positions centered around a given position.
     */
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

    /**
     * Generates a square of positions starting from a corner, based on direction.
     */
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

    /**
     * Retrieves a list of all stations currently registered.
     */
    public List<Station> listAllStations() {
        return StationRepository.getInstance().getAll();
    }

    /**
     * Retrieves a station by name from the repository.
     */
    public Station getStationByName(String name) {
        return StationRepository.getInstance().getStationByName(name);
    }

}