package pt.ipp.isep.dei.domain;

/**
 * Enum representing the different types of cargo in the railway system.
 * <p>
 * These cargo types can be transported by trains, demanded by cities or industries,
 * or supplied by stations depending on the simulation context.
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
     * <p>
     * The name is converted to a more user-friendly format,
     * with only the first letter capitalized.
     *
     * @return a nicely formatted cargo type name
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}