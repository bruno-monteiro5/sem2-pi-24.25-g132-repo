package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.MapRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;
import pt.ipp.isep.dei.repository.SimulatorRepository;
import pt.ipp.isep.dei.domain.Simulator;

import java.util.ArrayList;
import java.util.Optional;

public class SimulatorController {
    private MapRepository mapRepository;
    private ScenarioRepository scenarioRepository;
    private SimulatorRepository simulatorRepository;

    public SimulatorController() {
        getMapRepository();
        getScenarioRepository();
        getSimulatorRepository();
    }

    public SimulatorController(ScenarioRepository scenarioRepository, MapRepository mapRepository, SimulatorRepository simulatorRepository) {
        this.scenarioRepository = scenarioRepository;
        this.mapRepository = mapRepository;
        this.simulatorRepository = simulatorRepository;
    }

    public Optional<Simulator> createSimulator(ArrayList<Train> trains, ArrayList<Station> stations, ArrayList<Locomotive> locomotives, Scenario scenario) {
        return getSimulatorRepository().createSimulator(trains, stations, locomotives, scenario);
    }

    public void startSimulation() {
        getSimulatorRepository().getActiveSimulator().start();
    }

    public void pauseSimulation() {
        getSimulatorRepository().getActiveSimulator().pause();
    }

    public boolean isSimulationRunning() {
        return getSimulatorRepository().getActiveSimulator().isRunning();
    }

    public int getCurrentYear() {
        return getSimulatorRepository().getActiveSimulator().getCurrentYear();
    }

    public boolean hasSimulator() {
        return getSimulatorRepository().hasSimulator();
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

    private SimulatorRepository getSimulatorRepository() {
        if (simulatorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            simulatorRepository = repositories.getSimulatorRepository();
        }
        return simulatorRepository;
    }
}
