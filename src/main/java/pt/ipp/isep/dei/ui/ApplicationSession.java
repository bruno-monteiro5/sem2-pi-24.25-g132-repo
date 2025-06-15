package pt.ipp.isep.dei.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.simulator.SimulationEvent;
import pt.ipp.isep.dei.simulator.SimulatorEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ApplicationSession {

    private static final ApplicationSession instance = new ApplicationSession();

    private Map currentMap;
    private Scenario currentScenario;
    private SimulatorEngine simulatorEngine;
    private Consumer<SimulationEvent> sharedEventListener = null;
    private final ObservableList<String> simulatorEventMessages = FXCollections.observableArrayList();

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

    //Simulator
    public SimulatorEngine getSimulatorEngine() {
        return simulatorEngine;
    }

    public void setSimulatorEngine(SimulatorEngine simulatorEngine) {
        this.simulatorEngine = simulatorEngine;
    }

    public boolean hasSharedEventListener() {
        return sharedEventListener != null;
    }

    public Consumer<SimulationEvent> getSharedEventListener() {
        return sharedEventListener;
    }

    public void registerSharedEventListener(Consumer<SimulationEvent> listener) {
        if (this.sharedEventListener == null) {
            this.sharedEventListener = listener;
        }
    }

    public ObservableList<String> getSimulatorEventMessages() {
        return simulatorEventMessages;
    }

}
