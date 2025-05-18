package pt.ipp.isep.dei.domain;

/**
 * Represents a building that can be added to a station.
 */
public class Building {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Building)) return false;
        Building other = (Building) obj;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return "Building: " + type.name();
    }
}