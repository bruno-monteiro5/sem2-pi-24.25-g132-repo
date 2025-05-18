package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;
import java.util.List;

/**
 * Controller responsible for managing the creation of stations,
 * map selection, and related functionality in the system.
 * <p>
 * This class ensures that station creation and position validation
 * are governed by the active map and the current player.
 */

public class BuildStationController {

    private MapRepository mapRepo;
    private StationTypeRepository stationTypeRepository;
    private PlayerRepository playerRepo;

    /**
     * Default constructor that initializes this controller,
     * ensuring all repositories are loaded.
     */
    public BuildStationController() {
        getMapRepo();
        getStationTypeRepository();
        getPlayerRepo();
    }

    /**
     * Constructor that accepts specific repository instances.
     *
     * @param mapRepo              The {@link MapRepository} instance to be used.
     * @param stationTypeRepository The {@link StationTypeRepository} instance to be used.
     * @param playerRepo           The {@link PlayerRepository} instance to be used.
     */
    public BuildStationController (MapRepository mapRepo, StationTypeRepository stationTypeRepository, PlayerRepository playerRepo) {
        this.mapRepo = mapRepo;
        this.stationTypeRepository = stationTypeRepository;
        this.playerRepo = playerRepo;
    }

    /**
     * Retrieves the map repository, initializing it lazily if it is null.
     *
     * @return The {@link MapRepository} instance.
     */
    public MapRepository getMapRepo() {
        if (mapRepo == null){
            Repositories repositories = Repositories.getInstance();
            mapRepo = repositories.getMapRepository();
        }

        return mapRepo;
    }

    /**
     * Retrieves the station type repository, initializing it lazily if it is null.
     *
     * @return The {@link StationTypeRepository} instance.
     */
    public StationTypeRepository getStationTypeRepository() {
        if (stationTypeRepository == null){
            Repositories repositories = Repositories.getInstance();
            stationTypeRepository = repositories.getStationTypeRepository();
        }
        return stationTypeRepository;
    }

    /**
     * Retrieves the player repository, initializing it lazily if it is null.
     *
     * @return The {@link PlayerRepository} instance.
     */
    public PlayerRepository getPlayerRepo() {
        if (playerRepo == null){
            Repositories repositories = Repositories.getInstance();
            playerRepo = repositories.getPlayerRepository();
        }
        return playerRepo;
    }

    /**
     * Validates if the provided position is valid within the active map.
     *
     * @param position The {@link Position} to validate.
     * @return {@code true} if the position is valid; {@code false} otherwise.
     */
    public boolean validatePosition(Position position) {
        Map map = mapRepo.getActiveMap();

        return map.validatePosition(position);
    }

    /**
     * Creates a new station at the specified position with a given station type and name.
     * Ensures multiple constraints are met, such as map availability, player affordability,
     * and non-null input values.
     *
     * @param position    The position where the station will be placed.
     * @param stationType The type of station to create.
     * @param name        The name of the station.
     * @return {@code true} if the station is successfully created and added to the map; {@code false} otherwise.
     */
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

    /**
     * Suggests a name for a station based on its position and station type
     * within the active map.
     *
     * @param position    The position of the potential new station.
     * @param stationType The type of the station.
     * @return The suggested name for the station.
     */
    public String getSuggestedName (Position position, StationType stationType) {
        Map currentMap = mapRepo.getActiveMap();

        return currentMap.getSuggestedName(position, stationType);
    }

    /**
     * Sets the location center for a given position.
     *
     * @param position The position to update.
     * @param x        The new X-coordinate for the position.
     * @param y        The new Y-coordinate for the position.
     * @return {@code true} if the position was successfully updated; {@code false} if the position is null.
     */
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

    /**
     * Checks if there is an active map in the system.
     *
     * @return {@code true} if an active map exists; {@code false} otherwise.
     */
    public boolean hasActiveMap() {
        try {
            mapRepo.getActiveMap();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

}
