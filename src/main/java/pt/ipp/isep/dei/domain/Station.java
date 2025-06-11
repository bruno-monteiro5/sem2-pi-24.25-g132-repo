package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.domain.Demand;
import pt.ipp.isep.dei.domain.Delivery;


/**
 * Represents a station in the railway network.
 *
 * <p>A station is defined by its name, position on the map, type (e.g., depot, station, terminal),
 * and the infrastructure built around it. It also manages the cargo it supplies and demands.</p>
 *
 * <p>This class provides functionalities to:</p>
 * <ul>
 *     <li>Create a station with validated name, location, and type.</li>
 *     <li>Access and modify the station's basic attributes.</li>
 *     <li>Add or remove buildings, including handling replacements.</li>
 *     <li>Query available upgrades based on existing buildings.</li>
 *     <li>Manage cargo supply and demand lists.</li>
 * </ul>
 *
 * <p>The {@code Station} constructor enforces name validation to ensure data consistency at creation time.</p>
 */

public class Station implements Serializable {

    /**
     * The name of the station. Cannot be null or empty.
     */
    private String name;

    /**
     * The geographical location of the station represented as a {@link Position}.
     */
    private Position location;

    /**
     * The type of the station, defined as a {@link StationType}.
     */
    private StationType type;

    /**
     * The list of buildings present in the station.
     */
    private List<Building> buildings;
    private List<Cargo> cargoes = new ArrayList<>();
    private City city;
    private List<Industry> industries = new ArrayList<>();
    private ArrayList<Demand> demandList = new ArrayList<>();
    private double totalRevenue = 0;
    private ArrayList<Delivery> deliveries = new ArrayList<>();




    /**
     * The list of cargo that this station can supply.
     */
    private final List<Cargo> supplyCargo = new ArrayList<>();

    /**
     * The list of cargo that this station demands.
     */
    private final List<Cargo> demandCargo = new ArrayList<>();

    /**
     * Constructs a new {@code Station} with the specified name, location, and type.
     *
     * @param name the station name, which must not be null or empty
     * @param location the location of the station (non-null)
     * @param type the station type (non-null)
     * @throws IllegalArgumentException if {@code name} is null or empty
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
     * Returns the name of the station.
     *
     * @return the station's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the station.
     *
     * @return the {@link Position} of the station
     */
    public Position getLocation() {
        return location;
    }

    /**
     * Returns the type of the station.
     *
     * @return the {@link StationType} of the station
     */
    public StationType getType() {
        return type;
    }

    /**
     * Returns a list of the buildings in the station.
     *
     * @return a defensive copy of the buildings list
     */
    public List<Building> getBuildings() {
        return new ArrayList<>(buildings);
    }

    /**
     * Determines which building types can be added to the station for a given year.
     *
     * <p>Removes types that already exist, types that replace other buildings not yet present,
     * or types not yet available in the specified year.</p>
     *
     * @param allTypes list of all available {@link BuildingType}s
     * @param currentYear current year of the scenario
     * @return list of available {@link BuildingType}s to be added
     */
    public List<BuildingType> getAvailableUpgrades(List<BuildingType> allTypes, int currentYear) {
        List<BuildingType> availableUpgrades = new ArrayList<>();
        List<BuildingType> existingTypes = new ArrayList<>();

        for (Building b : buildings) {
            existingTypes.add(b.getType());
        }

        for (BuildingType type : allTypes) {
            if (!type.isAvailableIn(currentYear)) continue;

            if (type.getReplaces() != null) {

                if (existingTypes.contains(type.getReplaces())) {
                    availableUpgrades.add(type);
                }
            } else {

                if (!existingTypes.contains(type)) {
                    availableUpgrades.add(type);
                }
            }
        }

        return availableUpgrades;
    }


    /**
     * Updates the name of the station.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addRevenue(double amount) {
        totalRevenue += amount;
    }

    public void registerDelivery(CargoType type) {
        for (Delivery d : deliveries) {
            if (d.getType() == type) {
                d.increment();
                return;
            }
        }
        deliveries.add(new Delivery(type));
    }



    public void updateDemand() {
        for (Delivery delivery : deliveries) {
            CargoType cargoType = delivery.getType();
            int count = delivery.getCount();
            int value = getDemandFor(cargoType.name());

            for (Demand demand : demandList) {
                if (demand.getType() == cargoType) {
                    if (count >= 30 && value > 0) {
                        demand.setValue(value - 1);
                    } else if (count <= 10 && value < 9) {
                        demand.setValue(value + 1);
                    }
                }
            }
        }

        deliveries.clear();
    }



    /**
     * Updates the location of the station.
     *
     * @param location the new {@link Position}
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
     * Adds a new building to the station.
     * <p>
     * This method prevents duplicate buildings from being added. If the building type already
     * exists and is not a replacement, it will not be added again. If the new building type
     * replaces an existing one, the old building will be removed before adding the new one.
     * </p>
     *
     * @param type the {@link BuildingType} to be added
     * @return true if the building was successfully added, false otherwise
     */
    public boolean addBuilding(BuildingType type) {
        // Prevent adding duplicate building if it already exists
        if (buildings.stream().anyMatch(b -> b.getType() == type)) {
            return false;
        }

        // If this building type replaces another, remove the old one
        if (type.getReplaces() != null) {
            buildings.removeIf(building -> building.getType() == type.getReplaces());
        }

        // Add the new building
        return buildings.add(new Building(type));
    }

    /**
     * Removes a building of the specified type from the station.
     *
     * @param type the {@link BuildingType} to remove
     * @return true if removed successfully, false if not found
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

    public class Demand {
        private CargoType type;
        private int value;

        public Demand(CargoType type, int value) {
            this.type = type;
            this.value = value;
        }

        public CargoType getType() {
            return type;
        }

        public int getValue() {
            return value;
        }

        public void increase() {
            if (value < 9) value++;
        }

        public void decrease() {
            if (value > 0) value--;
        }

        public void setValue(int i) {
        }
    }


    public void generateAnnualCargoes() {
        if (servesCity()) {
            addCargo(new Cargo(CargoType.PASSENGERS, 10, true,100));
            addCargo(new Cargo(CargoType.MAIL, 5, true,60));

        }

        for (Industry industry : industries) {
            CargoType type = industry.getCargoType();
            int amount = industry.getYearlyOutput();


            double fixedValue = 0;

            switch (type) {
                case COAL:
                    fixedValue = 40;
                    break;
                case IRON_ORE:
                    fixedValue = 50;
                    break;
                case STEEL:
                    fixedValue = 70;
                    break;
                default:
                    fixedValue = 30;
            }
            addCargo(new Cargo(type, industry.getYearlyOutput(), false, fixedValue));

            addCargo(new Cargo(industry.getCargoType(), industry.getYearlyOutput(), false, fixedValue));

        }
    }



    public int getDemandFor(String cargoName) {
        for (Demand d : demandList) {
            if (d.getType().name().equalsIgnoreCase(cargoName)) {
                return d.getValue();
            }
        }
        return 5;
    }



    public boolean servesCity() {
        return this.city != null;
    }

    public void addCargo(Cargo cargo) {
        cargoes.add(cargo);
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
     * Compares this station to another object for equality based on name.
     *
     * @param o the object to compare
     * @return true if same name, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;

        return (name != null ? !name.equals(station.name) : station.name != null);
    }

    /**
     * Returns full details of the station in a formatted string.
     *
     * @return string with buildings, supply and demand cargo
     */
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


    /**
     * Calculates the total efficiency bonus from all buildings in this station.
     *
     * @return total efficiency bonus (e.g., 0.25 = +25%)
     */
    public double getTotalEfficiencyBonus() {
        double totalBonus = 0.0;

        for (Building b : buildings) {
            totalBonus += b.getType().getEfficiencyBonus();
        }

        return totalBonus;
    }
    /**
     * Calculates the total revenue multiplier from all buildings.
     *
     * @return multiplier to apply to revenue (e.g., 1.3 = +30%)
     */
    public double getTotalRevenueMultiplier() {
        double totalMultiplier = 1.0;

        for (Building b : buildings) {
            totalMultiplier *= b.getType().getRevenueMultiplier();
        }

        return totalMultiplier;
    }
    /**
     * Calculates the total extra storage capacity granted by all buildings.
     *
     * @return total additional capacity
     */
    public int getExtraStorageCapacity() {
        int totalExtraCapacity = 0;

        for (Building b : buildings) {
            totalExtraCapacity += b.getType().getExtraStorageCapacity();
        }

        return totalExtraCapacity;
    }

}



