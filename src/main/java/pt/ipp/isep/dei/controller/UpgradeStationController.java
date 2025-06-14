package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.List;

/**
 * Controller for upgrading stations with buildings.
 */
public class UpgradeStationController {

    private PlayerRepository playerRepo;
    private BuildingTypeRepository buildingTypeRepo;
    private ScenarioRepository scenarioRepo;

    /**
     * Default constructor initializes repositories.
     */
    public UpgradeStationController() {
        initializeRepositories();
    }

    /**
     * Constructor for dependency injection of repositories.
     */
    public UpgradeStationController(PlayerRepository playerRepo, BuildingTypeRepository buildingTypeRepo, ScenarioRepository scenarioRepo) {
        this.playerRepo = playerRepo;
        this.buildingTypeRepo = buildingTypeRepo;
        this.scenarioRepo = scenarioRepo;
    }

    private void initializeRepositories() {
        this.playerRepo = Repositories.getInstance().getPlayerRepository();
        this.buildingTypeRepo = Repositories.getInstance().getBuildingTypeRepository();
        this.scenarioRepo = Repositories.getInstance().getScenarioRepository();
    }

    public boolean hasActiveScenario() {
        try {
            scenarioRepo.getActiveScenario();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public Object[] loadScenarioAndStations() {
        Scenario scenario;

        try {
            scenario = scenarioRepo.getActiveScenario();
        } catch (IllegalStateException e) {
            return null;
        }

        if (scenario == null) {
            return null;
        }

        List<Station> stations = scenario.getStations();
        return new Object[]{scenario, stations};
    }

    public List<BuildingType> getAvailableUpgrades(Station station) {
        List<BuildingType> allBuildingTypes = buildingTypeRepo.getAllBuildingTypes();
        int currentYear = getCurrentYear();
        return station.getAvailableUpgrades(allBuildingTypes, currentYear);
    }

    public BuildingType getUpgradeDetails(BuildingType buildingType) {
        return buildingTypeRepo.getBuildingTypeDetails(buildingType);
    }

    public boolean executeUpgrade(Station station, BuildingType buildingType) {
        Player currentPlayer = playerRepo.getCurrentPlayer();

        if (!currentPlayer.canAfford(buildingType.getCost())) {
            return false;
        }

        BuildingType buildingDetails = buildingTypeRepo.getBuildingTypeDetails(buildingType);

        if (buildingDetails.getReplaces() != null) {
            station.removeBuilding(buildingDetails.getReplaces());
        }

        boolean upgradeSuccess = station.addBuilding(buildingType);

        if (upgradeSuccess) {
            currentPlayer.deductFunds(buildingType.getCost());
        }

        return upgradeSuccess;
    }

    private int getCurrentYear() {
        Scenario scenario = scenarioRepo.getActiveScenario();
        if (scenario == null || scenario.getPeriodTime() == null) {
            throw new IllegalStateException("No active scenario is defined.");
        }
        return scenario.getPeriodTime().getStartYear();
    }
}
