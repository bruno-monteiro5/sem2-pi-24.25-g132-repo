package pt.ipp.isep.dei.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the types of buildings that can be added to a station.
 */
public enum BuildingType {
    TELEGRAPH(100.0, null, 1850),
    TELEPHONE(200.0, TELEGRAPH, 1900, Set.of(TELEGRAPH)),
    CAFE_SMALL(150.0, null, 1850),
    CAFE_LARGE(300.0, CAFE_SMALL, 1900, Set.of(CAFE_SMALL)),
    POST_OFFICE(250.0, null, 1850),
    HOTEL_SMALL(150.0, null, 1850),
    HOTEL_GRAND(300.0, HOTEL_SMALL, 1900, Set.of(HOTEL_SMALL)),
    CUSTOMS(400.0, null, 1850),
    SILO(500.0, null, 1850),
    LIQUID_STORAGE(600.0, null, 1850);


    private final double cost; // The cost of constructing this building
    private final BuildingType replaces; // The building type that this one replaces (if any)
    private final int availableYear;// The year when this building type becomes available
    private Set<BuildingType> mutuallyExclusiveBuildings = new HashSet<>();

    /**
     * Creates a new building type.
     *
     * @param cost          The cost of the building
     * @param replaces      The building type this one replaces (if any)
     * @param availableYear The year when this building type becomes available
     */
    BuildingType(double cost, BuildingType replaces, int availableYear) {
        this.cost = cost;
        this.replaces = replaces;
        this.availableYear = availableYear;
    }

    BuildingType(double cost, BuildingType replaces, int availableYear, Set<BuildingType> mutuallyExclusiveBuildings) {
        this.cost = cost;
        this.replaces = replaces;
        this.availableYear = availableYear;
        this.mutuallyExclusiveBuildings = mutuallyExclusiveBuildings;
    }




    // === Getters ===

    /**
     * Gets the cost of constructing this building type.
     *
     * @return The cost of the building
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the building type that this one replaces, if any.
     *
     * @return The replaced building type, or null if this type does not replace another
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
     * Gets the year when this building type becomes available.
     *
     * @return The year the building type becomes available
     */
    public int getAvailableYear() {
        return availableYear;
    }

    public boolean isMutuallyExclusiveWith(BuildingType other) {
        return mutuallyExclusiveBuildings.contains(other);
    }

    /**
     * Checks if this building type can replace another type, based on the `replaces` attribute.
     *
     * @param other The building type to check against
     * @return true if this building type can replace the other, false otherwise
     */
    public boolean canReplace(BuildingType other) {
        return this.replaces == other;
    }

    /**
     * Provides detailed information about this building type as a string.
     *
     * @return A string representation of this building type
     */
    @Override
    public String toString() {
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.00");

        return String.format(
                "BuildingType: %s, Cost: %s, Replaces: %s, Available Year: %d",
                this.name(),
                decimalFormat.format(this.cost), // Formata o número com 2 casas decimais
                (this.replaces != null ? this.replaces.name() : "None"),
                this.availableYear
        );
    }

}

