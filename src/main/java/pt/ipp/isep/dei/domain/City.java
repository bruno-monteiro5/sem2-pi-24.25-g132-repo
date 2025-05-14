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
        if (x <= 0 || y <= 0 || numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("Os valores de x, y e numberOfHouseBlocks devem ser positivos.");
        }
        this.name = name;
        this.x = x;
        this.y = y;
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }

    /**
     * Returns the name of the city.
     *
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The new name for the city. Must not be null or empty.
     * @throws IllegalArgumentException if {@code name} is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser nulo ou vazio.");
        }
        this.name = name;
    }

    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the city's location.
     *
     * @param x The new x-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code x} is not positive.
     */
    public void setX(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("O valor de x deve ser positivo.");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the city's location.
     *
     * @param y The new y-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code y} is not positive.
     */
    public void setY(int y) {
        if (y <= 0) {
            throw new IllegalArgumentException("O valor de y deve ser positivo.");
        }
        this.y = y;
    }

    public int getNumberOfHouseBlocks() {
        return numberOfHouseBlocks;
    }

    /**
     * Sets the number of house blocks in the city.
     *
     * @param numberOfHouseBlocks The new number of house blocks. Must be a positive integer.
     * @throws IllegalArgumentException if {@code numberOfHouseBlocks} is not positive.
     */
    public void setNumberOfHouseBlocks(int numberOfHouseBlocks) {
        if (numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("O número de blocos de casas deve ser positivo.");
        }
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }
}
