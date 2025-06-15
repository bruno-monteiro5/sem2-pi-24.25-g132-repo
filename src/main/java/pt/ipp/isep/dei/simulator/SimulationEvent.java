package pt.ipp.isep.dei.simulator;


public abstract class SimulationEvent implements Comparable<SimulationEvent> {
    private final int scheduledTick;

    public SimulationEvent(int scheduledTick) {
        this.scheduledTick = scheduledTick;
    }

    public int getScheduledTick() {
        return scheduledTick;
    }

    public abstract void handle(SimulatorEngine engine);

    @Override
    public int compareTo(SimulationEvent other) {
        return Integer.compare(this.scheduledTick, other.scheduledTick);
    }

    public abstract String getDescription();

}