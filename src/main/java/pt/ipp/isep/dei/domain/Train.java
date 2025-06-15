package pt.ipp.isep.dei.domain;

import pt.ipp.isep.dei.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a train in the railway system.
 * <p>
 * A train is composed of a locomotive, a list of cargo items, and may be assigned to a route.
 * It tracks its location and distance traveled throughout the simulation.
 */
public class Train implements Serializable {

    /**
     * Unique identifier of the train.
     */
    private final int id;

    /**
     * The locomotive that powers this train.
     */
    private final Locomotive locomotive;

    /**
     * The list of cargo currently being transported.
     */
    private final List<Cargo> cargos;

    /**
     * The route currently assigned to the train.
     */
    private Route assignedRoute;

    /**
     * The station where the train is currently located.
     */
    private Station currentStation;

    /**
     * The last station visited by the train.
     */
    private Station lastStationVisited;

    /**
     * Total distance traveled by the train during the current year.
     */
    private double distanceTraveled;

    /**
     * Constructs a train with the given ID and locomotive.
     *
     * @param id         the unique train identifier
     * @param locomotive the locomotive assigned to this train
     */
    public Train(int id, Locomotive locomotive) {
        this.id = id;
        this.locomotive = locomotive;
        this.cargos = new ArrayList<>();
    }

    /**
     * Gets the train's unique ID.
     *
     * @return the train ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the locomotive assigned to the train.
     *
     * @return the locomotive
     */
    public Locomotive getLocomotive() {
        return locomotive;
    }

    /**
     * Gets the current list of cargo.
     *
     * @return the list of cargo
     */
    public List<Cargo> getCargos() {
        return cargos;
    }

    /**
     * Clears all cargo from the train.
     */
    public void clearCargos() {
        cargos.clear();
    }

    /**
     * Adds a cargo item to the train.
     *
     * @param cargo the cargo to add
     */
    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    /**
     * Unloads and returns all cargo from the train.
     *
     * @return a list of cargo that was unloaded
     */
    public List<Cargo> unloadCargo() {
        List<Cargo> delivered = new ArrayList<>(cargos);
        cargos.clear();
        return delivered;
    }

    /**
     * Assigns a route to the train.
     *
     * @param route the route to assign
     */
    public void assignRoute(Route route) {
        this.assignedRoute = route;
    }

    /**
     * Gets the route currently assigned to the train.
     *
     * @return the assigned route, or null if none
     */
    public Route getAssignedRoute() {
        return assignedRoute;
    }

    /**
     * Checks if the train has an assigned route.
     *
     * @return true if a route is assigned; false otherwise
     */
    public boolean hasRoute() {
        return assignedRoute != null;
    }

    /**
     * Gets the train's current station.
     *
     * @return the current station
     */
    public Station getCurrentStation() {
        return currentStation;
    }

    /**
     * Sets the train's current station.
     *
     * @param currentStation the station to set
     */
    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    /**
     * Gets the last station visited by the train.
     *
     * @return the last visited station
     */
    public Station getLastStationVisited() {
        return lastStationVisited;
    }

    /**
     * Sets the last station visited by the train.
     *
     * @param lastStationVisited the last visited station
     */
    public void setLastStationVisited(Station lastStationVisited) {
        this.lastStationVisited = lastStationVisited;
    }

    /**
     * Adds to the total distance traveled by the train.
     *
     * @param distance the distance to add
     */
    public void addDistance(double distance) {
        this.distanceTraveled += distance;
    }

    /**
     * Gets the total distance traveled by the train.
     *
     * @return distance in kilometers
     */
    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Resets the annual distance traveled to zero.
     */
    public void resetAnnualDistance() {
        this.distanceTraveled = 0;
    }

    /**
     * Estimates the travel duration between two stations.
     *
     * @param from the origin station
     * @param to   the destination station
     * @return estimated travel time in simulation ticks (minimum 1)
     */
    public int getTravelDuration(Station from, Station to) {
        if (from == null || to == null) return 1;

        RailwayLine line = assignedRoute.getRailwayLine(from, to);
        double baseSpeed = this.locomotive.getType().getTopSpeed();

        boolean isDoubleTrack = (line != null && line.isDouble());
        double speed = isDoubleTrack ? baseSpeed : baseSpeed * 0.8;

        int numCarriages = this.cargos.size();
        double speedReduction = Math.min(0.05 * numCarriages, 0.30);
        speed *= (1.0 - speedReduction);

        double dx = to.getLocation().getX() - from.getLocation().getX();
        double dy = to.getLocation().getY() - from.getLocation().getY();
        double distanceInMapUnits = Math.sqrt(dx * dx + dy * dy);

        double mapScaleKm = Repositories.getInstance().getMapRepository().getActiveMap().getScale();
        double distanceInKm = distanceInMapUnits * mapScaleKm;

        double travelTimeHours = distanceInKm / speed;
        double travelTimeTicks = travelTimeHours;

        return Math.max(1, (int) Math.ceil(travelTimeTicks));
    }

    /**
     * Returns a string representation of the train.
     *
     * @return formatted string with the train's details
     */
    @Override
    public String toString() {
        return String.format("Train{id=%d, locomotive=%s, cargos=%s, assignedRoute=%s, currentStation=%s}",
                id, locomotive, cargos, assignedRoute, currentStation);
    }
}
