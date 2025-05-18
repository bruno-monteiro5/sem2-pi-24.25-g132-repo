package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents detailed information about a station, including cargo supplies
 * and demands, position, type, and associated buildings.
 */
public class StationDetails {

    private String stationName;
    private Position position;
    private StationType stationType;
    private final List<Cargo> supplyCargo; // Cargo available for collection
    private final List<Cargo> demandCargo; // Cargo that needs to be delivered
    private final List<Building> buildings; // Buildings associated with the station

    /**
     * Constructor for creating StationDetails.
     *
     * @param stationName The name of the station.
     * @param position    The position of the station.
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

    // === Getters and Setters ===

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public StationType getStationType() {
        return stationType;
    }

    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }

    public List<Cargo> getSupplyCargo() {
        return supplyCargo;
    }

    public List<Cargo> getDemandCargo() {
        return demandCargo;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    // === Cargo Management ===

    public void addSupplyCargo(Cargo cargo) {
        this.supplyCargo.add(cargo);
    }

    public void addDemandCargo(Cargo cargo) {
        this.demandCargo.add(cargo);
    }

    public void removeSupplyCargo(CargoType type) {
        this.supplyCargo.removeIf(cargo -> cargo.getType() == type);
    }

    public void removeDemandCargo(CargoType type) {
        this.demandCargo.removeIf(cargo -> cargo.getType() == type);
    }

    // === Building Management ===

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void removeBuilding(BuildingType buildingType) {
        this.buildings.removeIf(building -> building.getType() == buildingType);
    }

    // === Utility: toString() ===

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