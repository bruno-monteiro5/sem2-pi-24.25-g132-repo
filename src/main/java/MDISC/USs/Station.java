package MDISC.USs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Station {
    private String name;
    private StationType type;
    private List<Connection> connections;

    public Station(String name, StationType type) {
        this.name = name;
        this.type = type;
        this.connections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public StationType getType() {
        return type;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public int getDegree(boolean onlyElectrified) {
        int degree = 0;
        for (Connection conn : connections) {
            if (!onlyElectrified || conn.isElectrified()) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
