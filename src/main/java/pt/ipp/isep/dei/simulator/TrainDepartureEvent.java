
package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.PointOfRoute;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.Station;

import java.util.List;

public class TrainDepartureEvent extends SimulationEvent {
    private final Train train;
    private final Station from;

    public TrainDepartureEvent(int tick, Train train, Station from) {
        super(tick);
        this.train = train;
        this.from = from;
    }

    @Override
    public void handle(SimulatorEngine engine) {
        List<PointOfRoute> points = train.getAssignedRoute().getPointsOfRoute();
        Station next = null;

        for (int i = 0; i < points.size() - 1; i++) {
            if (points.get(i).getStation().equals(from)) {
                next = points.get(i + 1).getStation();
                break;
            }
        }

        if (next != null) {
            train.setCurrentStation(next);
            System.out.printf("Tick %d: Train %s departed from %s to %s%n",
                    engine.getCurrentTick(), train.getId(), from.getName(), next.getName());

            int arrivalTick = engine.getCurrentTick() + train.getTravelDuration();
            engine.scheduleEvent(new TrainArrivalEvent(arrivalTick, train, next));
        } else {
            System.out.printf("Tick %d: Train at %s has no further stations in route.%n",
                    engine.getCurrentTick(), from.getName());
        }
    }

}

