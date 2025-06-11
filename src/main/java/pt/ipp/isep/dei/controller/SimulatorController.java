/**
package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.repository.SimulatorRepository;
import pt.ipp.isep.dei.domain.Simulator;

public class SimulatorController {

    private final Simulator simulator;

    public SimulatorController(SimulatorRepository repository) {
        this.simulator = new Simulator(
                repository.getTrains(),
                repository.getStations(),
                repository.getAllLocomotives(),
                repository.getScenario()
        );
    }

    public void startSimulation() {
        simulator.start();
    }

    public void pauseSimulation() {
        simulator.pause();
    }

    public boolean isSimulationRunning() {
        return simulator.isRunning();
    }

    public int getCurrentYear() {
        return simulator.getCurrentYear();
    }
}
*/