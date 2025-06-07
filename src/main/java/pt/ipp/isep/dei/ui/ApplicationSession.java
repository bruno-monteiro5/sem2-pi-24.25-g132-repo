package pt.ipp.isep.dei.ui;

import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Scenario;

public class ApplicationSession {

    private static final ApplicationSession instance = new ApplicationSession();

    private Map currentMap;
    private Scenario currentScenario;

    private ApplicationSession() {}

    public static ApplicationSession getInstance() {
        return instance;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map map) {
        this.currentMap = map;
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }

    public void setCurrentScenario(Scenario scenario) {
        this.currentScenario = scenario;
    }
}

