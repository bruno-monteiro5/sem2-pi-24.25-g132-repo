package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {

    private final int id;
    private final Locomotive locomotive;
    private final List<Cargo> cargos;
    private Route assignedRoute;
    private Station currentStation;
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

    public void addDistance(double distance) {this.distanceTraveled += distance;}

    public double getDistanceTraveled() {return distanceTraveled;}

    public void resetAnnualDistance() {this.distanceTraveled = 0;}

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
