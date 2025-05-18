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
     * Creates a new UpgradeStationController.
     */
    public UpgradeStationController() {
        getPlayerRepository();
        getMapRepository();
        getBuildingTypeRepository();
    }

    /**
     * Creates a new UpgradeStationController with the specified repositories.
     *
     * @param playerRepo The player repository
     * @param mapRepo The map repository
     * @param buildingTypeRepo The building type repository
     */
    public UpgradeStationController(PlayerRepository playerRepo, MapRepository mapRepo, BuildingTypeRepository buildingTypeRepo) {
        this.playerRepo = playerRepo;
        this.mapRepo = mapRepo;
        this.buildingTypeRepo = buildingTypeRepo;
    }

    private PlayerRepository getPlayerRepository() {
        if (playerRepo == null) {
            Repositories repositories = Repositories.getInstance();
            playerRepo = repositories.getPlayerRepository();
        }
        return playerRepo;
    }

    private MapRepository getMapRepository() {
        if (mapRepo == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepo = repositories.getMapRepository();
        }
        return mapRepo;
    }

    private BuildingTypeRepository getBuildingTypeRepository() {
        if (buildingTypeRepo == null) {
            Repositories repositories = Repositories.getInstance();
            buildingTypeRepo = repositories.getBuildingTypeRepository();
        }
        return buildingTypeRepo;
    }

    /**
     * Loads the map and stations.
     *
     * @return A pair containing the current map and list of stations, or null if no active map exists
     */
    public Object[] loadMapAndStations() {
        Map currentMap;
        try {
            currentMap = mapRepo.getActiveMap();
        } catch (IllegalStateException e) {
            // Handle case when no active map is set
            return null;
        }

        if (currentMap == null) {
            return null;
        }

        List<Station> stations = currentMap.getStations();
        return new Object[]{currentMap, stations};
    }

    /**
     * Checks if an active map exists.
     *
     * @return true if an active map exists, false otherwise
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
     * Gets the available upgrades for a station.
     *
     * @param station The station to get upgrades for
     * @return A list of available building types for upgrade
     */
    public List<BuildingType> getAvailableUpgrades(Station station) {
        List<BuildingType> allBuildingTypes = buildingTypeRepo.getAllBuildingTypes();
        return station.getAvailableUpgrades(allBuildingTypes);
    }

    /**
     * Gets the details of a building type.
     *
     * @param buildingType The building type to get details for
     * @return The building type details
     */
    public BuildingType getUpgradeDetails(BuildingType buildingType) {
        return buildingTypeRepo.getBuildingTypeDetails(buildingType);
    }

    /**
     * Executes an upgrade on a station.
     *
     * @param station The station to upgrade
     * @param buildingType The building type to add
     * @return true if the upgrade was successful, false otherwise
     */
    public boolean executeUpgrade(Station station, BuildingType buildingType) {
        Player currentPlayer = playerRepo.getCurrentPlayer();

        // Check if player can afford the upgrade
        if (!currentPlayer.canAfford(buildingType.getCost())) {
            return false;
        }

        // Get the details of the building type
        BuildingType details = buildingTypeRepo.getBuildingTypeDetails(buildingType);

        // If this building replaces another, remove the old one
        if (details.getReplaces() != null) {
            station.removeBuilding(details.getReplaces());
        }

        // Add the new building
        boolean success = station.addBuilding(buildingType);

        // If successful, deduct funds from player
        if (success) {
            currentPlayer.deductFunds(buildingType.getCost());
        }

        return success;
    }
}