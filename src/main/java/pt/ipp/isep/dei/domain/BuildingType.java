package pt.ipp.isep.dei.domain;

/**
 * Represents the types of buildings that can be added to a station.
 * Each building type has an associated cost, an optional type it replaces,
 * the year when it becomes available, and performance effects.
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

    private final double cost;
    private final BuildingType replaces;
    private final int availableYear;
    private final double efficiencyBonus;
    private final double revenueMultiplier;
    private final int extraStorageCapacity;

    /**
     * Creates a new building type.
     *
     * @param cost                 The cost of the building
     * @param replaces             The building type this one replaces (if any)
     * @param availableYear        The year when this building type becomes available
     * @param efficiencyBonus      Bonus to station efficiency (e.g. 0.10 = +10%)
     * @param revenueMultiplier    Multiplier for revenue (e.g. 1.2 = +20%)
     * @param extraStorageCapacity Additional storage capacity granted by the building
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

    public double getCost() {
        return cost;
    }

    public BuildingType getReplaces() {
        return replaces;
    }

    public int getAvailableYear() {
        return availableYear;
    }

    public boolean isAvailableIn(int year) {
        return year >= availableYear;
    }

    public BuildingType getBuildingTypeDetails() {
        return this;
    }

    public double getEfficiencyBonus() {
        return efficiencyBonus;
    }

    public double getRevenueMultiplier() {
        return revenueMultiplier;
    }

    public int getExtraStorageCapacity() {
        return extraStorageCapacity;
    }

    @Override
    public String toString() {
        return name() + " (Cost: " + cost + ", Available from: " + availableYear + ")";
    }
}
