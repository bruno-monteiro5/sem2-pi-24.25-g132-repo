package pt.ipp.isep.dei.domain;

/**
 * Represents the types of buildings that can be added to a station.
 */
public enum BuildingType {
    TELEGRAPH(100, null, 1850),
    TELEPHONE(200, TELEGRAPH, 1900),
    CAFE_SMALL(150, null, 1850),
    CAFE_LARGE(300, CAFE_SMALL, 1900),
    POST_OFFICE(250, null, 1850);

    private final double cost;
    private final BuildingType replaces;
    private final int availableYear;

    /**
     * Creates a new building type.
     *
     * @param cost The cost of the building
     * @param replaces The building type this one replaces (if any)
     * @param availableYear The year when this building type becomes available
     */
    BuildingType(double cost, BuildingType replaces, int availableYear) {
        this.cost = cost;
        this.replaces = replaces;
        this.availableYear = availableYear;
    }

    /**
     * Gets the cost of the building.
     *
     * @return The cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the building type that this one replaces (if any).
     *
     * @return The replaced building type, or null if none
     */
    public BuildingType getReplaces() {
        return replaces;
    }

    /**
     * Checks if this building type is available in the specified year.
     *
     * @param year The year to check
     * @return true if the building type is available, false otherwise
     */
    public boolean isAvailableIn(int year) {
        return year >= availableYear;
    }

    /**
     * Gets the details of this building type.
     *
     * @return This building type
     */
    public BuildingType getBuildingTypeDetails() {
        return this;
    }
}