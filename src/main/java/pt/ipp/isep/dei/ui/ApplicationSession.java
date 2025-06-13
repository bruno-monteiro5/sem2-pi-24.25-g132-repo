package pt.ipp.isep.dei.ui;

import pt.ipp.isep.dei.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSession {

    private static final ApplicationSession instance = new ApplicationSession();

    private Map currentMap;
    private Scenario currentScenario;

    private final List<Locomotive> ownedLocomotives = new ArrayList<>();
    private final List<Train> createdTrains = new ArrayList<>();

    private ApplicationSession() {}

    public static ApplicationSession getInstance() {
        return instance;
    }

    // MAP & SCENARIO
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

    // LOCOMOTIVES
    public void addOwnedLocomotive(Locomotive loco) {
        ownedLocomotives.add(loco);
    }

    public List<Locomotive> getOwnedLocomotives() {
        return new ArrayList<>(ownedLocomotives);
    }

    // TRAINS
    public void registerTrain(Train train) {
        createdTrains.add(train);
    }

    public List<Train> getCreatedTrains() {
        return new ArrayList<>(createdTrains);
    }
}
