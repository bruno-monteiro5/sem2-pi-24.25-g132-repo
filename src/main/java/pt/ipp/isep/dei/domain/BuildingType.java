package pt.ipp.isep.dei.domain;

/**
 /**
 * Represents the types of buildings that can be added to a station.
 * <p>
 * Each building type has an associated cost, the year when it becomes available,
 * performance effects (such as efficiency and revenue bonuses), and optionally replaces
 * another building type (e.g., TELEPHONE replaces TELEGRAPH).
 */
public enum BuildingType {

    TELEGRAPH(5000, null, 1850, 0.05, 1.0, 0),
    TELEPHONE(10000, TELEGRAPH, 1900, 0.10, 1.1, 0),
    CAFE_SMALL(8000, null, 1850, 0.0, 1.1, 0),
    CAFE_LARGE(16000, CAFE_SMALL, 1900, 0.0, 1.2, 0),
    POST_OFFICE(12000, null, 1850, 0.0, 1.0, 0),
    CUSTOMS(25000, null, 1850, 0.0, 1.0, 0),
    HOTEL_SMALL(20000, null, 1850, 0.0, 1.2, 0),
    HOTEL_LARGE(40000, HOTEL_SMALL, 1900, 0.0, 1.3, 0),
    SILO(15000, null, 1850, 0.0, 1.0, 20),
    LIQUID_STORAGE(15000, null, 1850, 0.0, 1.0, 20);

    /**
     * The cost of the building.
     */
    private final double cost;

    /**
     * The building type that this one replaces (if any).
     */
    private final BuildingType replaces;

    /**
     * The year when the building type becomes available.
     */
    private final int availableYear;

    /**
     * The bonus applied to station efficiency (e.g., 0.10 = +10%).
     */
    private final double efficiencyBonus;

    /**
     * The multiplier applied to station revenue (e.g., 1.2 = +20%).
     */
    private final double revenueMultiplier;

    /**
     * Additional storage capacity provided by the building (if any).
     */
    private final int extraStorageCapacity;

    /**
     * Creates a new building type with the given parameters.
     *
     * @param cost                 the cost of the building
     * @param replaces             the building type this one replaces (nullable)
     * @param availableYear        the year when this building becomes available
     * @param efficiencyBonus      efficiency bonus granted to the station
     * @param revenueMultiplier    multiplier applied to revenue
     * @param extraStorageCapacity extra cargo storage capacity
     */
    BuildingType(double cost, BuildingType replaces, int availableYear,
                 double efficiencyBonus, double revenueMultiplier, int extraStorageCapacity) {
        this.cost = cost;
        this.replaces = replaces;
        this.availableYear = availableYear;
        this.efficiencyBonus = efficiencyBonus;
        this.revenueMultiplier = revenueMultiplier;
        this.extraStorageCapacity = extraStorageCapacity;
    }

    /**
     * Gets the cost of the building.
     *
     * @return the cost in game currency
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the building type that this one replaces.
     *
     * @return the replaced building type, or null if it doesn't replace any
     */
    public BuildingType getReplaces() {
        return replaces;
    }

    /**
     * Gets the year when this building becomes available.
     *
     * @return the availability year
     */
    public int getAvailableYear() {
        return availableYear;
    }

    /**
     * Checks if this building is available in a given year.
     *
     * @param year the year to check
     * @return true if available; false otherwise
     */
    public boolean isAvailableIn(int year) {
        return year >= availableYear;
    }

    /**
     * Returns this building type instance.
     * <p>Useful in certain contexts requiring explicit self-reference.</p>
     *
     * @return this building type
     */
    public BuildingType getBuildingTypeDetails() {
        return this;
    }

    /**
     * Gets the efficiency bonus granted by this building.
     *
     * @return the efficiency bonus (e.g., 0.1 for +10%)
     */
    public double getEfficiencyBonus() {
        return efficiencyBonus;
    }

    /**
     * Gets the revenue multiplier applied by this building.
     *
     * @return the revenue multiplier (e.g., 1.2 for +20%)
     */
    public double getRevenueMultiplier() {
        return revenueMultiplier;
    }

    /**
     * Gets the extra storage capacity granted by this building.
     *
     * @return extra capacity in cargo units
     */
    public int getExtraStorageCapacity() {
        return extraStorageCapacity;
    }

    /**
     * Returns a detailed string representation of the building type.
     * <p>
     * Includes the name, cost, replaced building (if any), available year,
     * efficiency bonus, revenue multiplier, and extra storage capacity.
     *
     * @return formatted string with all building type attributes
     */
    @Override
    public String toString() {
        return String.format(
                "BuildingType{name='%s', cost=%.2f, replaces=%s, availableYear=%d, efficiencyBonus=%.2f, revenueMultiplier=%.2f, extraStorageCapacity=%d}",
                name(),
                cost,
                replaces != null ? replaces.name() : "none",
                availableYear,
                efficiencyBonus,
                revenueMultiplier,
                extraStorageCapacity
        );
    }
}
