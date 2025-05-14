package pt.ipp.isep.dei.domain;

/**
 * Represents a city with a name, coordinates (x, y), and the number of house blocks it contains.
 */

public class City {

    private String name;
    private int x;
    private int y;
    private int numberOfHouseBlocks;

    /**
     * Constructs a {@code City} object with the specified name, x-coordinate, y-coordinate, and number of house blocks.
     *
     * @param name                The name of the city. Must not be null or empty.
     * @param x                   The x-coordinate of the city's location. Must be a positive integer.
     * @param y                   The y-coordinate of the city's location. Must be a positive integer.
     * @param numberOfHouseBlocks The number of house blocks in the city. Must be a positive integer.
     * @throws IllegalArgumentException if {@code x}, {@code y}, or {@code numberOfHouseBlocks} is not positive,
     * or if {@code name} is null or empty.
     */
    public City(String name, int x, int y, int numberOfHouseBlocks) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        if (x < 0 || y < 0 ) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        if (numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("The number of houseBlocks must be positive.");
        }
        this.name = name;
        this.x = x;
        this.y = y;
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }

    public String getName() { return name; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getNumberOfHouseBlocks() { return numberOfHouseBlocks; }

    /**
     * Sets the name of the city.
     *
     * @param name The new name for the city. Must not be null or empty.
     * @throws IllegalArgumentException if {@code name} is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        this.name = name;
    }


    /**
     * Sets the x-coordinate of the city's location.
     *
     * @param x The new x-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code x} is not positive.
     */
    public void setX(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.x = x;
    }


    /**
     * Sets the y-coordinate of the city's location.
     *
     * @param y The new y-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code y} is not positive.
     */
    public void setY(int y) {
        if (y <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.y = y;
    }


    /**
     * Sets the number of house blocks in the city.
     *
     * @param numberOfHouseBlocks The new number of house blocks. Must be a positive integer.
     * @throws IllegalArgumentException if {@code numberOfHouseBlocks} is not positive.
     */
    public void setNumberOfHouseBlocks(int numberOfHouseBlocks) {
        if (numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("The number of houseBlocks must be positive.");
        }
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
