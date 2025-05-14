package pt.ipp.isep.dei.domain;

/**
 * Represents a Terminal, a specific type of Station in the railway network.
 * <p>
 * A Terminal has a predefined cost, and operational radius.
 * It extends the {@link Station} class.
 * </p>
 */
public class Terminal extends Station {
    private static final String TYPE = "Terminal";
    private static final int COST = 200_000;
    private static final int RADIUS = 5;

    /**
     * Constructs a Terminal instance with the specified name and coordinates.
     *
     * @param name The name of the terminal.
     * @param x    The x-coordinate of the terminal in the map.
     * @param y    The y-coordinate of the terminal in the map.
     */
    public Terminal(String name, int x, int y) {
        super(name, x, y, TYPE, COST, RADIUS); // Valores fixos
    }
}
