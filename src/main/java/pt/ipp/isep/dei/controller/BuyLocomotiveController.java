/*package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.*;

import java.util.List;

public class BuyLocomotiveController {
    private LocomotiveRepository locomotiveRepository;
    private LocomotiveTypeRepository locomotiveTypeRepository;
    private ScenarioRepository scenarioRepository;
    private MapRepository mapRepository;
    private PlayerRepository playerRepository;


    public BuyLocomotiveController(PlayerRepository playerRepo, MapRepository mapRepo, ScenarioRepository scenarioRepo, LocomotiveRepository locomotiveRepository) {
        this.playerRepository = playerRepo;
        this.mapRepository = mapRepo;
        this.scenarioRepository = scenarioRepo;
        this.locomotiveRepository = locomotiveRepository;
    }

    private MapRepository getMapRepository() {
        if (mapRepository == null) {
            Repositories repositories = Repositories.getInstance();
            mapRepository = repositories.getMapRepository();
        }
        return mapRepository;
    }

    private ScenarioRepository getScenarioRepository() {
        if (scenarioRepository == null) {
            Repositories repositories = Repositories.getInstance();
            scenarioRepository = repositories.getScenarioRepository();
        }
        return scenarioRepository;
    }

    private LocomotiveTypeRepository getTrainTypeRepository() {
        if (locomotiveTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            locomotiveTypeRepository = repositories.getLocomotiveTypeRepository();
        }
        return locomotiveTypeRepository;
    }

    private LocomotiveRepository getTrainRepository() {
        if (locomotiveRepository == null) {
            Repositories repositories = Repositories.getInstance();
            locomotiveRepository = repositories.getLocomotiveRepository();
        }
        return locomotiveRepository;
    }

    public List<LocomotiveType> getAvailableTrainTypesByTechnology(List<fuelType> typeOfFuel) {
        Map currentMap = mapRepository.getActiveMap();
        List<Scenario> allScenarios = currentMap.getScenarios();
        Scenario scenario = allScenarios.get(allScenarios.size() - 1);
        scenarioRepository.setActiveScenario(scenario);
        PeriodTime time = scenario.getPeriodTime();

        return getAvailableTrainTypesByTechnology(time, typeOfFuel);
}

*/
