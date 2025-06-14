package pt.ipp.isep.dei.domain;

import pt.ipp.isep.dei.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {

    private final int id;
    private final Locomotive locomotive;
    private final List<Cargo> cargos;
    private Route assignedRoute;
    private Station currentStation;
    private Station lastStationVisited;

    private double distanceTraveled;

    public Train(int id, Locomotive locomotive) {
        this.id = id;
        this.locomotive = locomotive;
        this.cargos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    public List<Cargo> unloadCargo() {
        List<Cargo> delivered = new ArrayList<>(cargos);
        cargos.clear();
        return delivered;
    }

    public void assignRoute(Route route) {
        this.assignedRoute = route;
    }

    public Route getAssignedRoute() {
        return assignedRoute;
    }

    public boolean hasRoute() {
        return assignedRoute != null;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public Station getLastStationVisited() {
        return lastStationVisited;
    }

    public void setLastStationVisited(Station lastStationVisited) {
        this.lastStationVisited = lastStationVisited;
    }

    public void addDistance(double distance) {this.distanceTraveled += distance;}

    public double getDistanceTraveled() {return distanceTraveled;}

    public void resetAnnualDistance() {this.distanceTraveled = 0;}

    public int getTravelDuration() {
        Station from = this.currentStation;
        Station to = assignedRoute.getNextStation(from);
        if (from == null || to == null) return 1;

        double travelTimeHours = getTravelTimeHours(to, from);
        double TICKS_PER_HOUR = 1.0;
        double travelTimeTicks = travelTimeHours * TICKS_PER_HOUR;

        return Math.max(1, (int) Math.ceil(travelTimeTicks)); // nunca inferior a 1
    }

    private double getTravelTimeHours(Station to, Station from) {
        final double MAP_SCALE_KM = Repositories.getInstance().getMapRepository().getActiveMap().getScale();

        double dx = to.getLocation().getX() - from.getLocation().getX();
        double dy = to.getLocation().getY() - from.getLocation().getY();
        double distanceInMapUnits = Math.sqrt(dx * dx + dy * dy);

        double distanceInKm = distanceInMapUnits * MAP_SCALE_KM;
        double speedKmPerHour = this.locomotive.getType().getTopSpeed();
        return distanceInKm / speedKmPerHour;
    }

    @Override
    public String toString() {
        return "Train{" +
                ", locomotive=" + locomotive +
                ", cargos=" + cargos +
                ", assignedRoute=" + assignedRoute +
                ", currentStation=" + currentStation +
                '}';
    }
}
