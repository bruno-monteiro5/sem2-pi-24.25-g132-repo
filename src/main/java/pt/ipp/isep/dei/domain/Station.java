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

    public String upgradeWithBuilding(Building building, int currentYear) {
        BuildingType newType = building.getType();

        if (!newType.isAvailableIn(currentYear)) {
            return "Error: " + newType.name() + " is not available in year " + currentYear + ".";
        }

        for (Building existingBuilding : buildings) {
            if (newType.isMutuallyExclusiveWith(existingBuilding.getType())) {
                return "Error: " + newType.name() + " is mutually exclusive with " + existingBuilding.getType().name() + ".";
            }
        }

        for (int i = 0; i < buildings.size(); i++) {
            Building existingBuilding = buildings.get(i);

            if (newType.canReplace(existingBuilding.getType())) {
                buildings.set(i, building); // Replace the old building with the new one
                return newType.name() + " has replaced " + existingBuilding.getType().name() + ".";
            }
        }

        buildings.add(building);
        return newType.name() + " has been added to the station.";
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


    public boolean addBuilding(BuildingType type) {
        if (type.getReplaces() != null) {
            buildings.removeIf(building -> building.getType() == type.getReplaces());
        }

        // Add the new building
        return buildings.add(new Building(type,0));
    }

    public List<BuildingType> getAvailableUpgrades(List<BuildingType> allBuildingTypes) {
        List<BuildingType> availableUpgrades = new ArrayList<>();

        // Loop through each building type to validate compatibility
        for (BuildingType buildingType : allBuildingTypes) {
            boolean canUpgrade = true;

            // Check mutual exclusivity with existing buildings
            for (Building existingBuilding : this.buildings) {
                if (buildingType.isMutuallyExclusiveWith(existingBuilding.getType())) {
                    canUpgrade = false;
                    break;
                }
            }

            // Add to the list if no restrictions were found
            if (canUpgrade) {
                availableUpgrades.add(buildingType);
            }
        }

        return availableUpgrades;
    }

    public boolean removeBuilding(BuildingType buildingType) {
        return buildings.removeIf(building -> building.getType().equals(buildingType));
    }
}



