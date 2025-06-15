package pt.ipp.isep.dei.domain;

import pt.ipp.isep.dei.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class    Train implements Serializable {

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

    public void clearCargos() {
        cargos.clear();
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


    public int getTravelDuration(Station from, Station to) {
        if (from == null || to == null) return 1;

        // Obter linha entre as estações
        RailwayLine line = assignedRoute.getRailwayLine(from, to);

        // Velocidade base da locomotiva
        double baseSpeed = this.locomotive.getType().getTopSpeed();

        // Ajuste por tipo de linha
        boolean isDoubleTrack = (line != null && line.isDouble());
        double speed = isDoubleTrack ? baseSpeed : baseSpeed * 0.8;

        // Ajuste por número de carruagens (máx 30%)
        int numCarriages = this.cargos.size();
        double speedReduction = Math.min(0.05 * numCarriages, 0.30);
        speed *= (1.0 - speedReduction);

        // Distância em unidades do mapa
        double dx = to.getLocation().getX() - from.getLocation().getX();
        double dy = to.getLocation().getY() - from.getLocation().getY();
        double distanceInMapUnits = Math.sqrt(dx * dx + dy * dy);

        // Conversão para km
        double mapScaleKm = Repositories.getInstance().getMapRepository().getActiveMap().getScale();
        double distanceInKm = distanceInMapUnits * mapScaleKm;

        // Tempo de viagem (em horas → 1 tick = 1 hora)
        double travelTimeHours = distanceInKm / speed;
        double travelTimeTicks = travelTimeHours;

        return Math.max(1, (int) Math.ceil(travelTimeTicks));
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
