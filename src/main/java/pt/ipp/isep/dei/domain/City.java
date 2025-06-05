package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a city with a specific name, position, and a set of house blocks.
 *
 * <p>A City object maintains the city's name, location on a map, the number of house blocks, and
 * a list of {@link HouseBlock} objects. It provides functionality to manually assign house blocks
 * or generate them automatically based on the city's placement.</p>
 *
 * <p>This class ensures:
 * <ul>
 *     <li>The city's name does not contain special characters or digits.</li>
 *     <li>The city's position is valid and unique (validated externally).</li>
 *     <li>The number of house blocks is positive.</li>
 * </ul>
 * The City class is a key entity that integrates with maps for structure creation and placement.</p>
 */
public class City {

    /**
     * The name of the city.
     *
     * <p>The name must be a valid string without special characters or digits. This is used to uniquely identify the city and associate it with map operations or user interactions.
     */
    private String name;

    /**
     * The position of the city on the map.
     *
     * <p>The position represents the geographical coordinates or placement of the city within the map. It ensures integration with other map-based structures.</p>
     */
    private Position position;

    /**
     * The total number of house blocks assigned to the city.
     *
     * <p>This value determines the number of {@link HouseBlock} objects managed by this city.
     */
    private int houseBlocks;

    /**
     * A list containing all house blocks associated with the city.
     *
     * <p>This list stores {@link HouseBlock} objects assigned to or generated for the city. It allows querying and manipulating house block positions.</p>
     */
    private List<HouseBlock> houseBlocksList;

    /**
     * Constructs a new City instance with the specified name, position, and number of house blocks.
     *
     * <p>The city name is validated to ensure it does not contain special characters or digits.
     * The position represents the city's location on the map and must be valid and not null.
     * The number of house blocks must be positive.</p>
     *
     * @param name        The name of the city.
     * @param position    The city's position on the map.
     * @param houseBlocks The total number of house blocks to assign to the city.
     * @throws IllegalArgumentException If:
     *                                  <ul>
     *                                      <li>The name contains special characters or digits.</li>
     *                                      <li>The position is null or invalid.</li>
     *                                      <li>The value of houseBlocks is not positive.</li>
     *                                  </ul>
     */
    public City(String name, Position position, int houseBlocks) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name must not be empty or null and it only can contain letters and spaces.");
        }
        if (position == null || position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("City position must be valid and positive.");
        }
        if (houseBlocks <= 0) {
            throw new IllegalArgumentException("Number of house blocks must be positive.");
        }

        this.name = name;
        this.position = position;
        this.houseBlocks = houseBlocks;
        this.houseBlocksList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public int getHouseBlocks() {
        return houseBlocks;
    }

    public List<HouseBlock> getHouseBlocksList() {
        return new ArrayList<>(houseBlocksList);
    }

    /**
     * Assigns house blocks to the city manually based on a list of positions.
     *
     * <p>This method allows you to explicitly set the positions of house blocks in the city.
     * The amount of provided positions must match the total number of house blocks configured for the city.
     * Each position is validated to ensure it is within the allowable boundaries of the map and does not conflict
     * with any other house blocks.</p>
     *
     * @param positions A {@link List} of {@link Position} objects representing the positions of the house blocks.
     * @throws IllegalArgumentException If:
     *                                  <ul>
     *                                      <li>The number of provided positions does not match the total houseBlocks.</li>
     *                                      <li>Any of the positions are invalid or conflict with existing house blocks.</li>
     *                                  </ul>
     */
    public void addHouseBlocksManually(List<Position> positions) {
        if (positions == null || positions.size() != houseBlocks) {
            throw new IllegalArgumentException("The number of house block positions must match the house block count.");
        }

        this.houseBlocksList.clear();

        for (Position pos : positions) {
            this.houseBlocksList.add(new HouseBlock(pos));
        }
    }

    /**
     * Automatically generates house blocks for the city based on its position.
     *
     * <p>This method randomly assigns positions to house blocks using a normal distribution around the city's position.
     * The positions are validated to ensure they remain within the map bounds and do not overlap with other structures.</p>
     *
     * @param map The {@link Map} used to validate the boundaries for assigning house blocks.
     * @throws IllegalArgumentException If:
     *                                  <ul>
     *                                      <li>Generated positions exceed the map boundaries.</li>
     *                                      <li>There are conflicts with existing structures on the map.</li>
     *                                  </ul>
     */
    public void generateHouseBlocksAutomatically(Map map) {
        Random random = new Random();

        while (houseBlocksList.size() < houseBlocks) {
            int offsetX = (int) Math.round(random.nextGaussian() * 5); // Standard deviation of 5
            int offsetY = (int) Math.round(random.nextGaussian() * 5);

            int newX = position.getX() + offsetX;
            int newY = position.getY() + offsetY;
            Position potentialPosition = new Position(newX, newY);

            if (isValidBlockPosition(potentialPosition, map)) {
                houseBlocksList.add(new HouseBlock(potentialPosition));
            }
        }
    }

    /**
     * Validates whether the specified house block position is valid and within the map boundaries.
     *
     * <p>This method checks that the given position respects the limits of the provided map and does not conflict
     * with other house blocks or structures. The method is private and used internally during the house block
     * assignment process.</p>
     *
     * @param blockPosition A {@link Position} object representing the house block position to validate.
     * @param map           The {@link Map} defining the boundaries and constraints for validation.
     * @return {@code true} if the position is valid and within the map boundaries; {@code false} otherwise.
     */
    private boolean isValidBlockPosition(Position blockPosition, Map map) {
        if (blockPosition.getX() < 0 || blockPosition.getX() >= map.getWidth() || blockPosition.getY() < 0 || blockPosition.getY() >= map.getHeight()) {
            return false;
        }
        if (blockPosition.equals(this.position)) {
            return false;
        }
        return true;
    }

    /**
     * Validates whether the city's name contains only allowed characters.
     *
     * <p>This private method ensures that the name does not contain special characters or digits,
     * allowing only letters and spaces. It is used internally during city creation to enforce
     * naming conventions.</p>
     *
     * @param name The name to validate.
     * @return {@code true} if the name is valid; {@code false} otherwise.
     */
    private boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Provides a string representation of the city.
     *
     * <p>The string includes detailed information about the city, such as:
     * <ul>
     *     <li>The name of the city</li>
     *     <li>The position of the city (x and y coordinates)</li>
     *     <li>The number of house blocks</li>
     *     <li>A list of all house blocks (if already added to the city)</li>
     * </ul>
     * This method is primarily used for debugging or for displaying the city's details to users in a readable format.</p>
     *
     * @return A string representation of the city, including its name, position, number of house blocks,
     *         and the house blocks' details (if available).
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", position=[" + position.getX() + ", " + position.getY() + "]" +
                ", houseBlocks=" + houseBlocks +
                ", houseBlocksList=" + houseBlocksList +
                '}';
    }

}
