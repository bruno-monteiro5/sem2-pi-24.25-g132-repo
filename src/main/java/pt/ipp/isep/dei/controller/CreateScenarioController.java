package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;

public class CreateScenarioController {

    private ScenarioRepository scenarioRepository;

    public CreateScenarioController() {
        this.scenarioRepository = Repositories.getInstance().getScenarioRepository();
    }

    public Scenario createScenario(String scenarioName, Map map, PeriodTime periodTime) {
        return new Scenario(scenarioName, map, periodTime);
    }

    public boolean createScenario(Map map, String scenarioName, int startYear, int endYear) {

        PeriodTime periodTime = new PeriodTime(startYear, endYear);
        if (!periodTime.isValid()) {
            return false;
        }


        return new Scenario(scenarioName, map, periodTime).isValid();
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

    public Scenario getActiveScenario(){
        return scenarioRepository.getActiveScenario();
    }

    public void setCurrentScenario(Scenario scenario){
        scenarioRepository.setActiveScenario(scenario);
    }

    public ScenarioRepository getScenarioRepo(){
        if (scenarioRepository  == null){
            Repositories repositories = Repositories.getInstance();
            scenarioRepository = repositories.getScenarioRepository();
        }
        return scenarioRepository;
    }
}