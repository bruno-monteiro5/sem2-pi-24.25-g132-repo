package pt.ipp.isep.dei.domain;

import java.util.List;

/**
 * Represents a station in the railway network.
 * A station is defined by a name and its (x, y) coordinates in the map.
 */
public class Station {
    private String name;
    private Position location;
    private StationType type;
    private List<Building> buildings;


    /**
     * Constructs a Station object.
     *
     * @param name The name of the station.
     * @throws IllegalArgumentException If name is invalid or coordinates are negative.
     */
    public Station(String name, Position location, StationType type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Station name cannot be null or empty.");
        }
        this.name = name;
        this.location = location;
        this.type = type;

    }


    public String getName() {
        return name;
    }

    public Position getLocation() {
        return location;
    }

    public StationType getType() {
        return type;
    }

    public List<Building> getBuildings() {
        return buildings;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(Position location) {
        this.location = location;
    }
    public void setType(StationType type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the station.
     * The representation includes detailed attributes of the station, such as:
     * name, position (coordinates), type of station, associated cost, and its operational radius.
     * <p>
     * @return A formatted string with the station's details.
     */
    @Override
    public String toString() {
        return "Station{" +
                "-Name='" + name + '\'' +
                "-Location=" + location +
                "-Type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;

        return (name != null ? !name.equals(station.name) : station.name != null);
    }
}