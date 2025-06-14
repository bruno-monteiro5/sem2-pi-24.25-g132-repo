package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.Scenario;

public class MonthProgressionEvent extends SimulationEvent {
    private final int month;
    private final int year;
    private final Scenario scenario;

    public MonthProgressionEvent(int tick, int month, int year, Scenario scenario) {
        super(tick);
        this.month = month;
        this.year = year;
        this.scenario = scenario;
    }

    @Override
    public void handle(SimulatorEngine engine) {
        // Atualiza o tempo simulado no motor da simulação
        engine.setSimulationStart(year, month);

        System.out.println("Tick " + engine.getCurrentTick() + ": Month changed to " + month + ", Year " + year);

        // Agendar o próximo mês
        int nextMonth = (month % 12) + 1;
        int nextYear = year + (month == 12 ? 1 : 0);

        if (nextYear <= scenario.getPeriodTime().getEndYear()) {
            engine.scheduleEvent(new MonthProgressionEvent(
                    engine.getCurrentTick() + 5, // 5 ticks por mês
                    nextMonth,
                    nextYear,
                    scenario
            ));
        }
    }
}
