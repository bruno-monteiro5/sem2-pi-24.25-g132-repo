package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.BuildingTypeRepository;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.PlayerRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

/**
 * Controller for upgrading stations with buildings.
 */
public class UpgradeStationController {

    private PlayerRepository playerRepo;
    private MapRepository mapRepo;
    private BuildingTypeRepository buildingTypeRepo;

    /**
     * Default constructor initializes repositories.
     */
    public UpgradeStationController() {
        initializeRepositories();
    }

    /**
     * Constructor for dependency injection of repositories.
     *
     * @param playerRepo        The player repository.
     * @param mapRepo           The map repository.
     * @param buildingTypeRepo  The building type repository.
     */
    public UpgradeStationController(PlayerRepository playerRepo, MapRepository mapRepo, BuildingTypeRepository buildingTypeRepo) {
        this.playerRepo = playerRepo;
        this.mapRepo = mapRepo;
        this.buildingTypeRepo = buildingTypeRepo;
    }

    private void initializeRepositories() {
        this.playerRepo = Repositories.getInstance().getPlayerRepository();
        this.mapRepo = Repositories.getInstance().getMapRepository();
        this.buildingTypeRepo = Repositories.getInstance().BuildingTypeRepository();
    }

    /**
     * Verifies if an active map exists in the system.
     *
     * @return true if an active map exists, false otherwise.
     */
    public boolean hasActiveMap() {
        try {
            mapRepo.getActiveMap();
            return true;
        } catch (IllegalStateException e) {
            // No active map available
            return false;
        }
    }

    /**
     * Loads the current map and its related stations.
     *
     * @return Object array containing the active map and its associated stations, or null if no active map exists.
     */
    public Object[] loadMapAndStations() {
        Map activeMap;

        try {
            activeMap = mapRepo.getActiveMap();
        } catch (IllegalStateException e) {
            // Handle missing active map
            return null;
        }

        if (activeMap == null) {
            return null;
        }

        List<Station> stations = activeMap.getStations();
        return new Object[]{activeMap, stations};
    }

    /**
     * Retrieves a list of available upgrades for a station.
     *
     * @param station The station to check for possible upgrades.
     * @return A list of available building types for upgrade.
     */
    public List<BuildingType> getAvailableUpgrades(Station station) {
        List<BuildingType> allBuildingTypes = buildingTypeRepo.getAllBuildingTypes();
        return station.getAvailableUpgrades(allBuildingTypes);
    }

    /**
     * Retrieves full details for a specific building type.
     *
     * @param buildingType The building type for which details are required.
     * @return The BuildingType object with complete details.
     */
    public BuildingType getUpgradeDetails(BuildingType buildingType) {
        return buildingTypeRepo.getBuildingTypeDetails(buildingType);
    }

    /**
     * Executes an upgrade for a given station.
     *
     * @param station      The station to upgrade.
     * @param buildingType The building type to add.
     * @return true if the upgrade was successful, false otherwise.
     */
    public boolean executeUpgrade(Station station, BuildingType buildingType) {
        Player currentPlayer = playerRepo.getCurrentPlayer();

        // Verify if the player has sufficient funds
        if (!currentPlayer.canAfford(buildingType.getCost())) {
            return false;
        }

        // Retrieve the detailed information about the building type
        BuildingType buildingDetails = buildingTypeRepo.getBuildingTypeDetails(buildingType);

        // If the building type replaces an old one, it is removed
        if (buildingDetails.getReplaces() != null) {
            station.removeBuilding(buildingDetails.getReplaces());
        }

        // Add the new building to the station
        boolean upgradeSuccess = station.addBuilding(buildingType);

        if (upgradeSuccess) {
            // Deduct funds from the player
            currentPlayer.deductFunds(buildingType.getCost());
        }

        return upgradeSuccess;
    }
}
