package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Simulator implements Runnable, Serializable {

    private final Scenario scenario;
    private int currentYear;
    private final int endYear;
    private boolean running;
    private Thread simulationThread;


    private final ArrayList<Train> trains;
    private final ArrayList<Station> stations;
    private final ArrayList<Locomotive> locomotives;

    public Simulator(ArrayList<Train> trains, ArrayList<Station> stations, ArrayList<Locomotive> locomotives, Scenario scenario) {
        this.trains = trains;
        this.stations = stations;
        this.locomotives = locomotives;
        this.scenario = scenario;
        this.currentYear = scenario.getPeriodTime().getStartYear();
        this.endYear = scenario.getPeriodTime().getEndYear();
        this.running = false;
    }

    public void start() {
        if (simulationThread == null || !simulationThread.isAlive()) {
            running = true;
            simulationThread = new Thread(this);
            simulationThread.start();
        }
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

    @Override
    public void run() {
        System.out.println("Simulation started.");

        while (running && currentYear <= endYear) {
            simulateStep();
            currentYear++;
            try {
                Thread.sleep(3000); // espera 3 segundos entre anos (ou mais se quiseres)
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted.");
                break;
            }
        }

        running = false;
        System.out.println("Simulation ended at year " + (currentYear-1) + ".");
    }

    private void simulateStep() {
        // Generate cargoes
        for (Station station : scenario.getStations()) {
            station.generateAnnualCargoes();
        }

        // Move trains and deliver cargo
        for (Train train : scenario.getTrains()) {
            if (!train.hasRoute()) continue;

            Station current = train.getCurrentStation();
            Route route = train.getAssignedRoute();
            Station next = route.getNextStation(current);

            if (next == null) continue;

            RailwayLine line = scenario.getLineBetweenStations(current, next);
            if (line == null) continue;

            double distance = line.getLength();
            LineType type = line.getLineType();
            boolean isDoubleTrack = type == LineType.DOUBLE_ELECTRIFIED || type == LineType.DOUBLE_NOT_ELECTRICITY;

            double speed = calculateEffectiveSpeed(train, isDoubleTrack);
            double travelTime = distance / speed; // not used in timing here, only logic

            train.setCurrentStation(next);

            List<Cargo> delivered = train.unloadCargo();
            for (Cargo cargo : delivered) {
                int demand = next.getDemandFor(cargo.getType().name());
                double revenue = calculateRevenue(cargo.getBaseValue(), demand, distance);
                next.addRevenue(revenue);
                next.registerDelivery(cargo.getType());
            }
        }

        // Update station demand
        for (Station station : scenario.getStations()) {
            station.updateDemand();
        }

        // Notify about new locomotives
        for (Locomotive loco : scenario.getLocomotives()) {
            if (loco.getStartYear() == currentYear) {
                System.out.println("New locomotive available: " + loco.getType().name());
            }
        }

        System.out.println("Year " + currentYear + " simulated.");
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
