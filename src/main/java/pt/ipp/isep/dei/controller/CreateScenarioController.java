package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;

public class CreateScenarioController {

    private final ScenarioRepository scenarioRepository;

    public CreateScenarioController() {
        this.scenarioRepository = Repositories.getInstance().getScenarioRepository();
    }

    public Scenario createScenario(String scenarioName, Map map) {
        return new Scenario(scenarioName, map);
    }

    public void addPort(Scenario scenario, Port port) {
        scenario.addPort(port);
    }

    public void addLocomotiveType(Scenario scenario, LocomotiveType type) {
        scenario.addLocomotiveType(type);
    }

    public void setIndustryGenerationFactor(Scenario scenario, double factor) {
        scenario.setIndustryGenerationFactor(factor);
    }

    public boolean saveScenario(Scenario scenario) {
        try {
            scenarioRepository.add(scenario);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao guardar o cenário: " + e.getMessage());
            return false;
        }
    }
}