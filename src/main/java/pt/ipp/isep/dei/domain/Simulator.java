package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.domain.LineType;

public class Simulator implements Serializable {

    private final Scenario scenario;
    private int currentYear;
    private boolean running;
    private ArrayList<Train> trains;
    private ArrayList<Station> stations;
    private ArrayList<Locomotive> locomotives;

    public Simulator(ArrayList<Train> trains, ArrayList<Station> stations, ArrayList<Locomotive> locomotives, Scenario scenario, int startYear) {
        this.trains = trains;
        this.stations = stations;
        this.locomotives = locomotives;
        this.scenario = scenario;
        this.currentYear = startYear;
        this.running = false;
    }


    public void start() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void nextYear() {
        if (!running) return;

        for (Station station : scenario.getStations()) {
            station.generateAnnualCargoes();
        }

        for (Train train : scenario.getTrains()) {
            if (!train.hasRoute()) continue;

            Station current = train.getCurrentStation();
            Route route = train.getAssignedRoute();
            Station next = route.getNextStation(current);

            if (next == null) continue;

            RailwayLine line = scenario.getMap().getLineBetweenStations(current, next);
            if (line == null) continue;

            double distance = line.getLength();
            boolean isDoubleTrack = line.getLineType() == LineType.DOUBLE_NOT_ELECTRICITY;

            double speed = calculateEffectiveSpeed(train, isDoubleTrack);

            train.setCurrentStation(next);

            List<Cargo> delivered = train.unloadCargo();
            for (Cargo cargo : delivered) {
                int demand = next.getDemandFor(cargo.getType().name());
                double revenue = calculateRevenue(cargo.getBaseValue(), demand, distance);
                next.addRevenue(revenue);
                next.registerDelivery(cargo.getType());

            }
        }

        for (Station station : scenario.getStations()) {
            station.updateDemand();
        }

        for (Locomotive loco : scenario.getLocomotives()) {
            if (loco.getStartYear() == currentYear) {
                System.out.println("New locomotive available: " + loco.getType().name());
            }
        }

        currentYear++;
    }

    private double calculateEffectiveSpeed(Train train, boolean isDoubleTrack) {
        double baseSpeed = train.getLocomotive().getType().getTopSpeed();
        if (!isDoubleTrack) baseSpeed *= 0.8;

        int cargoCount = train.getCargos().size();
        double penalty = Math.min(cargoCount * 0.05, 0.30);

        return baseSpeed * (1.0 - penalty);
    }

    private double calculateRevenue(double baseValue, int demand, double distance) {
        int diff = demand - 5;
        double multiplier = 1 + (0.2 * diff);
        return baseValue * multiplier * distance;
    }
}
