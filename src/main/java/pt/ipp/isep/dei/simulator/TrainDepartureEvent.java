
package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.PointOfRoute;
import pt.ipp.isep.dei.domain.RailwayLine;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.Station;

import java.util.List;

public class TrainDepartureEvent extends SimulationEvent {
    private final Train train;
    private final Station from;
    private Station to;

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

        if (next == null) {
            System.out.printf("Tick %d: Train at %s has no further stations in route.%n",
                    engine.getCurrentTick(), from.getName());
            return;
        }

        // Verificar se existe linha entre as estações
        RailwayLine line = train.getAssignedRoute().getRailwayLine(from, next);
        if (line == null) {
            System.out.printf("⚠️ Tick %d: No railway line between %s and %s. Departure cancelled.%n",
                    engine.getCurrentTick(), from.getName(), next.getName());
            return;
        }

        // Continuar com a viagem
        train.setCurrentStation(next);
        this.to = next;

        System.out.printf("Tick %d: Train %s departed from %s to %s%n",
                engine.getCurrentTick(), train.getId(), from.getName(), next.getName());


        int arrivalTick = engine.getCurrentTick() + train.getTravelDuration(from, next);
        engine.scheduleEvent(new TrainArrivalEvent(arrivalTick, train, next));
    }

    @Override
    public String getDescription() {
        if (to == null) return "Train departure not completed yet.";
        return String.format("Tick %d: Train %s departed from %s to %s",
                getScheduledTick(), train.getId(), from.getName(), to.getName());
    }

}

