package pt.ipp.isep.dei.domain;

/**
 * Represents the different types of stations in the railway network.
 *
 * <p>Each station type is defined by its associated cost, operational radius,
 * display name, and whether it requires a connection to a centralized station.</p>
 *
 * <p>The available station types are:</p>
 * <ul>
 *   <li><b>DEPOT</b>: A depot station with a small radius and lower cost.</li>
 *   <li><b>STATION</b>: A standard station with medium radius and cost.</li>
 *   <li><b>TERMINAL</b>: A terminal station with the largest radius and highest cost.</li>
 * </ul>
 */
public enum StationType {
    DEPOT(50000, 3, "Depot", false),
    STATION(100000, 4, "Station", true),
    TERMINAL(200000, 5, "Terminal", false),;

    private final int cost;
    private final int radius;
    private final String Name;
    private final boolean needsCenter;

    /**
     * Constructor for a {@code StationType}.
     *
     * @param cost The cost associated with the station type.
     * @param radius The operational radius (range) of the station type.
     * @param Name The display name of the station type.
     * @param needsCenter Whether the station type requires connection to a central station.
     */
    StationType(int cost, int radius, String Name, boolean needsCenter) {
        this.cost = cost;
        this.radius = radius;
        this.Name = Name;
        this.needsCenter = needsCenter;
    }

    /**
     * Retrieves the cost associated with the station type.
     *
     * @return The cost of the station type.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Retrieves the operational radius of the station type.
     *
     * @return The radius of the station type.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Retrieves the display name of the station type.
     *
     * @return The name of the station type.
     */
    public String getName() {
        return Name;
    }

    /**
     * Determines whether the station type requires a connection to a centralized station.
     *
     * @return {@code true} if the station type needs a center; {@code false} otherwise.
     */
    public boolean needsCenter() {
        return needsCenter;
    }

}
