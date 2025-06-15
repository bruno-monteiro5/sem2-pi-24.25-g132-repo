package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.Route;
import pt.ipp.isep.dei.domain.Train;

/**
 * Evento que sinaliza a conclusão de um percurso completo por parte de um comboio
 * (ida ou volta) e muda a sua direção.
 */
public class RouteCompletionEvent extends SimulationEvent {
    private final Train train;
    private final Route route;
    private final boolean forwardJourney;

    public RouteCompletionEvent(int tick, Train train, Route route, boolean forwardJourney) {
        super(tick);
        this.train = train;
        this.route = route;
        this.forwardJourney = forwardJourney;
    }

    @Override
    public void handle(SimulatorEngine engine) {
        engine.notifyEvent(this);
        String direction = forwardJourney ? "FORWARD" : "BACKWARD";
        System.out.printf("[Tick %d] RouteCompletionEvent: Train %s completed %s journey on route %s.%n",
                getScheduledTick(), train.getId(), direction, route.getName());

        engine.notifyEventHandled(this);

        // Aqui podes adicionar efeitos futuros, como estatísticas ou registo no FinanceManager
    }

    public Train getTrain() {
        return train;
    }

    public Route getRoute() {
        return route;
    }

    public boolean isForwardJourney() {
        return forwardJourney;
    }
}
