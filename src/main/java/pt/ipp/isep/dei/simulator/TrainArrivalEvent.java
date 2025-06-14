package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.Station;

public class TrainArrivalEvent extends SimulationEvent {
    private final Train train;
    private final Station station;

    public TrainArrivalEvent(int tick, Train train, Station station) {
        super(tick);
        this.train = train;
        this.station = station;
    }

    @Override
    public void handle(SimulatorEngine engine) {
        train.setCurrentStation(station);
        train.setLastStationVisited(station);

        System.out.printf("Tick %d: Train %s arrived at %s%n",
                engine.getCurrentTick(), train.getId(), station.getName());

        // Verificar se chegou ao fim da rota
        var route = train.getAssignedRoute();
        var points = route.getPointsOfRoute();
        int index = -1;

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getStation().equals(station)) {
                index = i;
                break;
            }
        }

        boolean endOfRoute = (index == points.size() - 1);
        if (endOfRoute) {
            System.out.printf("Tick %d: Train %s completed its route.%n",
                    engine.getCurrentTick(), train.getId());

            // Enviar evento de conclusão
            engine.scheduleEvent(new RouteCompletionEvent(
                    engine.getCurrentTick(), train, route, true // (ou false se quiseres direção reversa)
            ));
        }

        // Agendar próxima partida (mesmo que fim de rota — podes ajustar lógica depois)
        int departureTick = engine.getCurrentTick() + 1;
        engine.scheduleEvent(new TrainDepartureEvent(departureTick, train, station));
    }

}