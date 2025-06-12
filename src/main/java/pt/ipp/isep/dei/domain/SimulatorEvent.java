package pt.ipp.isep.dei.domain;

import java.io.Serializable;

public class SimulatorEvent implements Serializable {

    private final SimulatorEventType type;
    private final Train train;

    public SimulatorEvent(SimulatorEventType type, Train train) {
        this.type = type;
        this.train = train;
    }

    public SimulatorEventType getType() {
        return type;
    }

    public Train getTrain() {
        return train;
    }
}
