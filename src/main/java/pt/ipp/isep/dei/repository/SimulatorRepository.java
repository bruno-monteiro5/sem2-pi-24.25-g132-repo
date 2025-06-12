package pt.ipp.isep.dei.repository;

import java.util.ArrayList;
import java.util.Optional;

import pt.ipp.isep.dei.domain.*;

public class SimulatorRepository {
    private final ArrayList<Simulator> simulators;
    private Simulator lastSimulator;

    public SimulatorRepository() {
        simulators = new ArrayList<>();
    }

    public Optional<Simulator> createSimulator(ArrayList<Train> trains, ArrayList<Station> stations, ArrayList<Locomotive> locomotives, Scenario scenario) {
        Simulator simulator = new Simulator(trains, stations, locomotives, scenario);
        if (addSimulator(simulator)) {
            lastSimulator = simulator;
            return Optional.of(simulator);
        }
        return Optional.empty();
    }

    private boolean addSimulator(Simulator simulator) {
        return simulators.add(simulator);
    }

    public Optional<Simulator> getLastSimulator() {
        return Optional.ofNullable(lastSimulator);
    }

    public boolean hasSimulator() {
        return lastSimulator != null;
    }

    public Simulator getActiveSimulator() {
        if (lastSimulator == null) {
            throw new IllegalStateException("No active simulator found.");
        }
        return lastSimulator;
    }
}

