package pt.ipp.isep.dei.simulator;

import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;

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

        // Update the simulated time
        engine.setSimulationStart(year, month);

        System.out.printf("Tick %d: Month changed to %d, Year %d%n",
                engine.getCurrentTick(), month, year);


        //A message alerting when a new locomotive becomes available, accordingly to its start year of service.
        for (LocomotiveType type : LocomotiveType.values()) {
            if (type.getStartYear() == year &&
                    !engine.getAnnouncedLocomotives().contains(type)) {

                System.out.printf("🚨 New Locomotive Available: %s (introduced in %d)%n",
                        type.getName(), year);

                engine.getAnnouncedLocomotives().add(type);
            }
        }

        if (month == 1) {
            for (Station station : scenario.getStations()) {
                station.updateDemand();
            }
        }

        // Schedule the next month
        int nextMonth = (month % 12) + 1;
        int nextYear = year + (month == 12 ? 1 : 0);

        if (nextYear <= scenario.getPeriodTime().getEndYear()) {
            engine.scheduleEvent(new MonthProgressionEvent(
                    engine.getCurrentTick() + 5,
                    nextMonth,
                    nextYear,
                    scenario
            ));
        }
    }

    @Override
    public String getDescription() {
        return String.format("Tick %d: Month changed to %d, Year %d",
                getScheduledTick(), month, year);
    }


}
