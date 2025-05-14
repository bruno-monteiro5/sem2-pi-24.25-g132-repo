package pt.ipp.isep.dei.domain;

/**
 * Represents a Depot, a specific type of Station in the railway network.
 * <p>
 * A Depot has a predefined cost, and operational radius.
 * It extends the {@link Station} class.
 * </p>
 */
public class Depot extends Station {
    private static final String TYPE = "Depot";
    private static final int COST = 50_000;
    private static final int RADIUS = 3;

    /**
     * Constructs a Depot instance with the specified name and coordinates.
     *
     * @param name The name of the depot.
     * @param x    The x-coordinate of the depot in the map.
     * @param y    The y-coordinate of the depot in the map.
     */
    public Depot(String name, int x, int y) {
        super(name, x, y, TYPE, COST, RADIUS); // Valores fixos
    }
}