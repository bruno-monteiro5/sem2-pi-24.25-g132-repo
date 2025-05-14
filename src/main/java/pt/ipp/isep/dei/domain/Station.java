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
     * Constructs a Station object.
     *
     * @param name The name of the station.
     * @param x    The x-coordinate.
     * @param y    The y-coordinate.
     * @throws IllegalArgumentException If name is invalid or coordinates are negative.
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