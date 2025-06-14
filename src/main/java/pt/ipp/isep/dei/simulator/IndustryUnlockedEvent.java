package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.Industry;

/**
 * Evento que sinaliza o desbloqueio de uma nova indústria, normalmente no início de um novo ano.
 */
public class IndustryUnlockedEvent extends SimulationEvent {
    private final Industry industry;

    public IndustryUnlockedEvent(int tick, Industry industry) {
        super(tick);
        this.industry = industry;
    }

    @Override
    public void handle(SimulatorEngine engine) {
        System.out.printf("[Tick %d] IndustryUnlockedEvent: New industry unlocked → %s%n",
                getScheduledTick(), industry.getName());

        // (opcional) Mostrar alerta ao utilizador, notificar GUI, atualizar estatísticas...
    }

    public Industry getIndustry() {
        return industry;
    }
}
