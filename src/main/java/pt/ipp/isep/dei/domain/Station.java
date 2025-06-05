package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a station in the railway network.
 * A station is defined by a name, its geographical location (coordinates),
 * a type, and any buildings it contains.
 *
 * <p>The {@code Station} class provides methods to manage a station's details,
 * such as its name, location, type, buildings, and available upgrades.
 * It also provides functionality for adding or removing buildings.</p>
 */
public class Station {
    private String name;
    private Position location;
    private StationType type;
    private List<Building> buildings;

    private final List<Cargo> supplyCargo = new ArrayList<>();
    private final List<Cargo> demandCargo = new ArrayList<>();


    /**
     * Constructs a {@code Station} object.
     *
     * @param name The name of the station. Cannot be null or empty.
     * @param location The geographical location of the station.
     * @param type The type of the station.
     * @throws IllegalArgumentException If the name is null or empty.
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

    /**
     * Retrieves the name of the station.
     *
     * @return The name of the station.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the geographical location of the station.
     *
     * @return The {@code Position} representing the location of the station.
     */
    public Position getLocation() {
        return location;
    }

    /**
     * Retrieves the type of the station.
     *
     * @return The {@code StationType} of the station.
     */
    public StationType getType() {
        return type;
    }

    /**
     * Retrieves the list of buildings currently present in the station.
     *
     * @return A list of {@code Building} objects in the station.
     */
    public List<Building> getBuildings() {
        return new ArrayList<>(buildings);
    }

    /**
     * Determines the available upgrades for the station's buildings.
     *
     * <p>This method checks the list of all potential building types and removes:
     * <ul>
     *   <li>Types that are already present in the station.</li>
     *   <li>Types that require other types to exist and those types are not present.</li>
     * </ul>
     * </p>
     *
     * @param allTypes A list of all possible {@code BuildingType} objects.
     * @return A list of {@code BuildingType} objects representing possible upgrades.
     */
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


    /**
     * Updates the name of the station.
     *
     * @param name The new name of the station.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the geographical location of the station.
     *
     * @param location The new {@code Position} of the station.
     */
    public void setLocation(Position location) {
        this.location = location;
    }

    /**
     * Updates the type of the station.
     *
     * @param type The new {@code StationType}.
     */
    public void setType(StationType type) {
        this.type = type;
    }


    /**
     * Adds a building to the station.
     *
     * <p>If the specified building type replaces another building type,
     * the old building is removed before the new building is added.</p>
     *
     * @param type The {@code BuildingType} of the building to be added.
     * @return {@code true} if the building was successfully added;
     * {@code false} otherwise.
     */
    public boolean addBuilding(BuildingType type) {
        // Check if this building type replaces another
        if (type.getReplaces() != null) {
            // Remove the building it replaces
            buildings.removeIf(building -> building.getType() == type.getReplaces());
        }

        // Add the new building
        return buildings.add(new Building(type));
    }

    /**
     * Removes a building of the specified type from the station.
     *
     * @param type The {@code BuildingType} of the building to be removed.
     * @return {@code true} if the building was successfully removed;
     * {@code false} otherwise.
     */
    public boolean removeBuilding(BuildingType type) {
        return buildings.removeIf(building -> building.getType() == type);
    }

    /**
     * Retrieves the list of cargo available for collection (supply cargo).
     *
     * @return A list of {@code Cargo} representing the supply cargo.
     */
    public List<Cargo> getSupplyCargo() {
        return supplyCargo;
    }

    /**
     * Retrieves the list of cargo that needs to be delivered (demand cargo).
     *
     * @return A list of {@code Cargo} representing the demand cargo.
     */
    public List<Cargo> getDemandCargo() {
        return demandCargo;
    }

    /**
     * Adds a cargo item to the supply cargo list.
     *
     * @param cargo The {@code Cargo} to be added to the supply cargo list.
     */
    public void addSupplyCargo(Cargo cargo) {
        this.supplyCargo.add(cargo);
    }

    /**
     * Adds a cargo item to the demand cargo list.
     *
     * @param cargo The {@code Cargo} to be added to the demand cargo list.
     */
    public void addDemandCargo(Cargo cargo) {
        this.demandCargo.add(cargo);
    }

    /**
     * Removes cargo of a specific type from the supply cargo list.
     *
     * @param type The {@code CargoType} to be removed from the supply cargo list.
     */
    public void removeSupplyCargo(CargoType type) {
        this.supplyCargo.removeIf(cargo -> cargo.getType() == type);
    }

    /**
     * Removes cargo of a specific type from the demand cargo list.
     *
     * @param type The {@code CargoType} to be removed from the demand cargo list.
     */
    public void removeDemandCargo(CargoType type) {
        this.demandCargo.removeIf(cargo -> cargo.getType() == type);
    }

    /**
     * Provides a string representation of the station, including its name,
     * location, and type.
     *
     * @return A string representation of the station.
     */
    @Override
    public String toString() {
        return "Station{" +
                "-Name='" + name + '\'' +
                "-Location=" + location +
                "-Type=" + type +
                '}';
    }


    /**
     * Compares this station with another object to determine equality.
     *
     * <p>Stations are considered equal if their names match.</p>
     *
     * @param o The object to compare with.
     * @return {@code true} if the stations are equal; {@code false} otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;

        return (name != null ? !name.equals(station.name) : station.name != null);
    }

    public String getFullDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Station Details:\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Type: ").append(type.getName()).append("\n");
        details.append("Position: ").append(location).append("\n\n");

        details.append("Buildings:\n");
        if (buildings.isEmpty()) {
            details.append(" - None\n");
        } else {
            for (Building building : buildings) {
                details.append(" - ").append(building.getType()).append("\n");
            }
        }

        details.append("\nSupply Cargo:\n");
        if (supplyCargo.isEmpty()) {
            details.append(" - None\n");
        } else {
            for (Cargo cargo : supplyCargo) {
                details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
            }
        }

        details.append("\nDemand Cargo:\n");
        if (demandCargo.isEmpty()) {
            details.append(" - None\n");
        } else {
            for (Cargo cargo : demandCargo) {
                details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
            }
        }

        return details.toString();
    }


}



