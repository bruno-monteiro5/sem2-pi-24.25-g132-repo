package pt.ipp.isep.dei.domain;

/**
 * Enum representing the different types of cargo in the railway system.
 */
public enum CargoType {
    PASSENGERS,
    MAIL,
    FOOD,
    COAL,
    STEEL,
    CARS,
    TEXTILES,
    IRON_ORE,
    OTHER;

    /**
     * Returns a formatted name for display purposes.
     *
     * @return A user-friendly name for the cargo type.
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}