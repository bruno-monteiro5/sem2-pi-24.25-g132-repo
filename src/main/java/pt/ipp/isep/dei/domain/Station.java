package pt.ipp.isep.dei.domain;

/**
 * Represents a station in the railway network.
 * A station is defined by a name and its (x, y) coordinates in the map.
 */
public abstract class Station {
    private String name;
    private int x;
    private int y;
    private String type;
    private int cost;
    private int radius;

    /**
     * Constructs a Station object, representing a point in the railway network
     * with its associated properties such as name, coordinates, type, operational cost,
     * and the radius of influence.
     * <p>
     * This constructor ensures that all input values are valid, enforcing constraints to prevent
     * the creation of invalid station objects.
     * </p>
     *
     * @param name  The name of the station. Must not be {@code null} or empty.
     * @param x     The x-coordinate of the station in the map. Must be a non-negative integer.
     * @param y     The y-coordinate of the station in the map. Must be a non-negative integer.
     * @param type  The type of the station (e.g., "passenger", "freight"). Must not be {@code null}.
     * @param cost  The operational cost associated with the station. Typically represents
     *              maintenance or operational expenses.
     * @param radius The radius of influence of the station, representing its effective
     *               range of service (e.g., coverage area).
     *
     * @throws IllegalArgumentException If {@code name} is {@code null} or empty,
     *                                  if {@code x} or {@code y} are negative,
     *                                  or if any invalid value is specified.
     */

    public Station(String name, int x, int y, String type, int cost, int radius) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Station name cannot be null or empty.");
        }
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;
        this.cost = cost;
        this.radius = radius;

    }


    public String getName() { return name; }

    public int getX() { return x; }

    public int getY() { return y; }

    public String getType() { return type; }

    public int getCost() { return cost; }

    public int getRadius() { return radius; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Station name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.y = y;
    }

    /**
     * Returns a string representation of the station.
     * The representation includes detailed attributes of the station, such as:
     * name, position (coordinates), type of station, associated cost, and its operational radius.
     * <p>
     * @return A formatted string with the station's details.
     */
    @Override
    public String toString() {
        return String.format(
                "Station: '%s'%n" +
                        "    - Coordinates: (x=%d, y=%d)%n" +
                        "    - Type: %s%n" +
                        "    - Cost: %d%n" +
                        "    - Radius: %d",
                name, x, y, type, cost, radius
        );

    }
}