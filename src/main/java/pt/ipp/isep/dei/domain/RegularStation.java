package pt.ipp.isep.dei.domain;

/**
 * Represents a RegularStation, a specific type of Station in the railway network.
 * <p>
 * A RegularStation has a predefined cost, and operational radius.
 * It extends the {@link Station} class.
 * </p>
 */
public class RegularStation extends Station {
    private static final String TYPE = "Station";
    private static final int COST = 100_000;
    private static final int RADIUS = 4;

    /**
     * Constructs a RegularStation instance with the specified name and coordinates.
     *
     * @param name The name of the regular station.
     * @param x    The x-coordinate of the regular station in the map.
     * @param y    The y-coordinate of the regular station in the map.
     */
    public RegularStation(String name, int x, int y) {
        super(name, x, y, TYPE, COST, RADIUS); // Valores fixos
    }
}
