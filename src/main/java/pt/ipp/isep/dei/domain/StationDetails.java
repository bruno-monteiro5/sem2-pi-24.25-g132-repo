package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents detailed information about a station, including its cargo supplies
 * and demands, geographical position, type, and associated buildings.
 *
 * <p>The {@code StationDetails} class supports operations for managing the
 * station's cargo (supply and demand) and buildings, as well as modification
 * of basic station properties such as its name, type, and position.</p>
 */
public class StationDetails {

    private String stationName;
    private Position position;
    private StationType stationType;
    private final List<Cargo> supplyCargo; // Cargo available for collection
    private final List<Cargo> demandCargo; // Cargo that needs to be delivered
    private final List<Building> buildings; // Buildings associated with the station

    /**
     * Constructs a {@code StationDetails} instance with specified station information.
     *
     * @param stationName The name of the station.
     * @param position    The geographical position of the station.
     * @param stationType The type of the station.
     */
    public StationDetails(String stationName, Position position, StationType stationType) {
        this.stationName = stationName;
        this.position = position;
        this.stationType = stationType;
        this.supplyCargo = new ArrayList<>();
        this.demandCargo = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }


    /**
     * Retrieves the name of the station.
     *
     * @return The name of the station.
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Updates the name of the station.
     *
     * @param stationName The new name of the station.
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * Retrieves the geographical position of the station.
     *
     * @return The {@code Position} of the station.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Updates the geographical position of the station.
     *
     * @param position The new {@code Position} of the station.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the type of the station.
     *
     * @return The {@code StationType} of the station.
     */
    public StationType getStationType() {
        return stationType;
    }

    /**
     * Updates the type of the station.
     *
     * @param stationType The new {@code StationType}.
     */
    public void setStationType(StationType stationType) {
        this.stationType = stationType;
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
     * Retrieves the list of buildings associated with the station.
     *
     * @return A list of {@code Building} objects.
     */
    public List<Building> getBuildings() {
        return buildings;
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
     * Adds a building to the list of buildings associated with the station.
     *
     * @param building The {@code Building} to be added.
     */
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    /**
     * Removes a building of a specific type from the list of buildings.
     *
     * @param buildingType The {@code BuildingType} of the building to be removed.
     */
    public void removeBuilding(BuildingType buildingType) {
        this.buildings.removeIf(building -> building.getType() == buildingType);
    }


    /**
     * Provides a string representation of the station details, including its name,
     * type, position, buildings, and cargo (supply and demand).
     *
     * @return A string representation of the station details.
     */
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Station Details:\n");
        details.append("Name: ").append(stationName).append("\n");
        details.append("Type: ").append(stationType).append("\n");
        details.append("Position: ").append(position).append("\n\n");
        details.append("Buildings:\n");
        for (Building building : buildings) {
            details.append(" - ").append(building.getType()).append("\n");
        }
        details.append("\nSupply Cargo:\n");
        for (Cargo cargo : supplyCargo) {
            details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
        }
        details.append("\nDemand Cargo:\n");
        for (Cargo cargo : demandCargo) {
            details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
        }
        return details.toString();
    }
}