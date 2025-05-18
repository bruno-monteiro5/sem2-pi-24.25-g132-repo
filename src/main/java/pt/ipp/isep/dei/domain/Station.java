package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
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
        this.buildings = new ArrayList<>();

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
        return new ArrayList<>(buildings);
    }

    public List<BuildingType> getAvailableUpgrades(List<BuildingType> allTypes) {
        List<BuildingType> availableUpgrades = new ArrayList<>(allTypes);
        List<BuildingType> existingTypes = new ArrayList<>();

        // Get all existing building types
        for (Building building : buildings) {
            existingTypes.add(building.getType());
        }

        // Remove types that are already built and don't have upgrades
        availableUpgrades.removeIf(type -> {
            // If this type replaces another type
            if (type.getReplaces() != null) {
                // Check if the type it replaces exists
                return !existingTypes.contains(type.getReplaces());
            } else {
                // If it doesn't replace anything, check if it already exists
                return existingTypes.contains(type);
            }
        });

        return availableUpgrades;
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


    public boolean addBuilding(BuildingType type) {
        // Check if this building type replaces another
        if (type.getReplaces() != null) {
            // Remove the building it replaces
            buildings.removeIf(building -> building.getType() == type.getReplaces());
        }

        // Add the new building
        return buildings.add(new Building(type));
    }

    public boolean removeBuilding(BuildingType type) {
        return buildings.removeIf(building -> building.getType() == type);
    }


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



