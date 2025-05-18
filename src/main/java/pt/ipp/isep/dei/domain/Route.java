package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private String id; // Unique identifier for the route
    private List<Station> stations; // List of stations in the route
    private List<Pair> connections; // List of station connections with distances

    /**
     * Constructor to create a route.
     *
     * @param id The unique identifier for the route
     * @throws IllegalArgumentException If the ID is null or empty
     */
    public Route(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Route ID cannot be null or empty.");
        }
        this.id = id;
        this.stations = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    /**
     * Adds a new station to the route.
     *
     * @param station The station to be added
     * @throws IllegalArgumentException If the station is null
     */
    public void addStation(Station station) {
        if (station == null) {
            throw new IllegalArgumentException("Station cannot be null.");
        }
        stations.add(station);
    }

    /**
     * Adds a connection between two stations with a specific distance.
     *
     * @param stationA The first station
     * @param stationB The second station
     * @param distance The distance between the stations (must be positive)
     * @throws IllegalArgumentException If stations are null or distance is invalid
     */
    public void addConnection(Station stationA, Station stationB, int distance) {
        if (stationA == null || stationB == null) {
            throw new IllegalArgumentException("Stations cannot be null.");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be a positive value.");
        }

        // Add the connection as a Pair object
        connections.add(new Pair(stationA, stationB, distance));
    }

    /**
     * Checks if all consecutive stations in the route are connected.
     *
     * @return true if all stations are connected; false otherwise
     */
    public boolean isConnected() {
        for (int i = 0; i < stations.size() - 1; i++) {
            Station current = stations.get(i);
            Station next = stations.get(i + 1);

            // Check if the two stations have a connection
            if (!isConnectionExist(current, next)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the total distance of all connections in the route.
     *
     * @return The total distance as an integer
     */
    public int getTotalDistance() {
        int total = 0;
        for (Pair pair : connections) {
            total += pair.getDistance();
        }
        return total;
    }

    /**
     * Checks if there is a connection between two stations.
     *
     * @param stationA The first station
     * @param stationB The second station
     * @return true if a connection exists; false otherwise
     */
    private boolean isConnectionExist(Station stationA, Station stationB) {
        for (Pair connection : connections) {
            if (connection.connects(stationA, stationB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the route ID.
     *
     * @return The unique route ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the list of stations in the route.
     *
     * @return A copy of the stations list
     */
    public List<Station> getStations() {
        return new ArrayList<>(stations); // Return a defensive copy
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Route ID: " + id + "\nStations:\n");
        for (Station station : stations) {
            sb.append("- ").append(station.getName()).append(" (Type: ")
                    .append(station.getType()).append(", Position: ")
                    .append(station.getLocation()).append(")\n");
        }
        sb.append("Connections:\n");
        for (Pair connection : connections) {
            sb.append("- ").append(connection).append("\n");
        }
        return sb.toString();
    }

    /**
     * Inner class to represent a connection (pair) between two stations.
     */
    private static class Pair {
        private Station stationA;
        private Station stationB;
        private int distance;

        public Pair(Station stationA, Station stationB, int distance) {
            this.stationA = stationA;
            this.stationB = stationB;
            this.distance = distance;
        }

        public boolean connects(Station a, Station b) {
            // Check if the pair connects the two stations (in any order)
            return (stationA.equals(a) && stationB.equals(b)) || (stationA.equals(b) && stationB.equals(a));
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return stationA.getName() + " <--> " + stationB.getName() + " (" + distance + " units)";
        }
    }
}