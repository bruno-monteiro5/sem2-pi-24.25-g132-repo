package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a city with a name, a position on the map, and a number of house blocks.
 * <p>
 * A city generates or consumes cargo depending on its house blocks and nearby stations.
 * House blocks can be added manually or generated automatically.
 */
public class City implements Serializable {

    /**
     * The name of the city.
     * Must contain only letters and spaces.
     */
    private final String name;

    /**
     * The position (X, Y) of the city on the map.
     */
    private final Position position;

    /**
     * The number of house blocks associated with this city.
     */
    private final int houseBlocks;

    /**
     * The list of house blocks assigned to the city.
     * Can be manually or automatically filled.
     */
    private final List<HouseBlock> houseBlocksList;

    /**
     * Constructs a new City with the given name, position, and number of house blocks.
     *
     * @param name        the name of the city (letters and spaces only)
     * @param position    the city's coordinates on the map
     * @param houseBlocks the number of house blocks (must be positive)
     * @throws IllegalArgumentException if the name is invalid, the position is invalid, or the number of house blocks is not positive
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

    /**
     * Gets the name of the city.
     *
     * @return the city's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this city instance.
     *
     * @return the current city
     */
    public City getCity() {
        return this;
    }

    /**
     * Gets the position of the city on the map.
     *
     * @return the city's position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Gets the number of house blocks defined for the city.
     *
     * @return the number of house blocks
     */
    public int getHouseBlocks() {
        return houseBlocks;
    }

    /**
     * Gets a copy of the list of house blocks assigned to the city.
     *
     * @return list of house blocks
     */
    public List<HouseBlock> getHouseBlocksList() {
        return new ArrayList<>(houseBlocksList);
    }

    /**
     * Adds house blocks to the city manually using specified positions.
     *
     * @param positions list of positions to assign house blocks
     * @param map       the map used to validate positions
     * @throws IllegalArgumentException if the number of positions is incorrect or any position is invalid
     */
    public void addHouseBlocksManually(List<Position> positions, Map map) {
        if (positions == null || positions.size() != houseBlocks) {
            throw new IllegalArgumentException("The number of house block positions must match the house block count.");
        }

        this.houseBlocksList.clear();

        for (Position pos : positions) {
            if (!isValidBlockPosition(pos, map)) {
                throw new IllegalArgumentException("Invalid house block position: " + pos);
            }
            this.houseBlocksList.add(new HouseBlock(pos));
        }
    }

    /**
     * Automatically generates house blocks around the city's position using a normal distribution.
     *
     * @param map the map used to validate generated positions
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
     * Checks if a position is valid for placing a house block.
     *
     * @param blockPosition the position to check
     * @param map           the map used for boundary validation
     * @return true if the position is valid; false otherwise
     */
    public boolean isValidBlockPosition(Position blockPosition, Map map) {
        if (blockPosition.getX() < 0 || blockPosition.getX() >= map.getWidth() ||
                blockPosition.getY() < 0 || blockPosition.getY() >= map.getHeight()) {
            return false;
        }

        if (blockPosition.equals(this.position)) {
            return false;
        }

        int dx = Math.abs(blockPosition.getX() - this.position.getX());
        int dy = Math.abs(blockPosition.getY() - this.position.getY());
        int maxDistance = 20;

        return dx <= maxDistance && dy <= maxDistance;
    }


    /**
     * Checks if a city name is valid (letters and spaces only).
     *
     * @param name the name to validate
     * @return true if the name is valid; false otherwise
     */
     public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Returns a string with the city's details.
     *
     * @return formatted string with name, position, and house blocks
     */
    @Override
    public String toString() {
        return String.format(
                "City{name='%s', position=[%d, %d], houseBlocks=%d, houseBlocksList=%s}",
                name, position.getX(), position.getY(), houseBlocks, houseBlocksList
        );
    }

}
