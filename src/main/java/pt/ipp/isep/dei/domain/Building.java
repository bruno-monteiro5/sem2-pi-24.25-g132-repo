package pt.ipp.isep.dei.domain;

import java.util.Objects;

/**
 * Represents an instance of a building that can be added or upgraded to a station.
 */
public class Building {

    private final BuildingType type;
    private final int constructionYear;
    private boolean replaced;

    /**
     * Creates a new building instance of the specified type.
     *
     * @param type             The type of the building
     * @param constructionYear The year the building was constructed
     * @throws IllegalArgumentException if the building type is not available in the construction year
     */
    public Building(BuildingType type, int constructionYear) {
        if (!type.isAvailableIn(constructionYear)) {
            throw new IllegalArgumentException(
                    "Building type " + type.name() + " is not available in the year " + constructionYear);
        }
        this.type = type;
        this.constructionYear = constructionYear;
        this.replaced = false;
    }


    /**
     * Gets the type of the building.
     *
     * @return The building type
     */
    public BuildingType getType() {
        return type;
    }

    /**
     * Gets the year the building was constructed.
     *
     * @return The construction year
     */
    public int getConstructionYear() {
        return constructionYear;
    }

    /**
     * Checks if this building has been replaced.
     *
     * @return true if the building has been replaced, false otherwise
     */
    public boolean isReplaced() {
        return replaced;
    }


    /**
     * Marks this building as replaced by another (usually during an upgrade).
     */
    public void markAsReplaced() {
        this.replaced = true;
    }

    /**
     * Checks if this type of building can replace another.
     *
     * @param other The building to check against
     * @return true if this building can replace the other, false otherwise
     */
    public boolean canReplace(Building other) {
        return this.type.equals(other.getType().getReplaces());
    }


    /**
     * Checks if two buildings are equal. Buildings are considered equal if they
     * have the same type and the same construction year.
     *
     * @param obj The object to compare to
     * @return true if the buildings are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Building)) return false;
        Building other = (Building) obj;
        return this.type == other.type && this.constructionYear == other.constructionYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, constructionYear);
    }


    /**
     * Returns a string representation of the building.
     *
     * @return A string describing the building
     */
    @Override
    public String toString() {
        return String.format(
                "Building: %s, Year: %d, Cost: %.2f, Replaced: %b",
                type.name(),
                constructionYear,
                type.getCost(),
                replaced
        );
    }


}