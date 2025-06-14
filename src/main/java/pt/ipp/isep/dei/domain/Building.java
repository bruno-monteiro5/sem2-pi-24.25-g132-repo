package pt.ipp.isep.dei.domain;

import java.io.Serializable;

/**
 * Represents a building that can be added to a station.
 * <p>
 * Each building has a specific {@link BuildingType}, which defines its purpose and effect
 * (e.g., increasing revenue, improving storage, facilitating operations).
 */
public class Building implements Serializable {

    /**
     * The type of the building.
     */
    private BuildingType type;

    /**
     * Creates a new building of the specified type.
     *
     * @param type The type of the building
     */
    public Building(BuildingType type) {
        this.type = type;
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
     * Compares this building with another object for equality.
     * Two buildings are equal if they have the same type.
     *
     * @param obj the object to compare
     * @return true if the objects represent the same building type, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Building)) return false;
        Building other = (Building) obj;
        return this.type == other.type;
    }

    /**
     * Returns a string representation of the building.
     *
     * @return the building's type as a string
     */
    @Override
    public String toString() {
        return "Building: " + type.name();
    }
}