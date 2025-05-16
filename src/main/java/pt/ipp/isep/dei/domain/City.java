package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a City that can be placed on a Map in the system.
 * Each city has a name, a position on the map, and an assigned number of house blocks.
 * It also supports automatic or manual generation of house blocks.
 */
public class City {

    private String name;
    private Position position;
    private int houseBlocks;
    private List<HouseBlock> houseBlocksList;

    /**
     * Constructs a {@code City} object with the specified name, x-coordinate, y-coordinate, and number of house blocks.
     *
     * @param name                The name of the city. Must not be null or empty.
     * @param position                   The coordinates of the city's location. Must be a positive integer.
     * @param houseBlocks The number of house blocks in the city. Must be a positive integer.
     * @throws IllegalArgumentException if {@code x}, {@code y}, or {@code numberOfHouseBlocks} is not positive,
     * or if {@code name} is null or empty.
     */
    public City(String name, Position position, Integer houseBlocks) {
        if (name == null || position == null || houseBlocks == null) {
            throw new IllegalArgumentException("No argument can be null");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name cannot contain special characters or digits");
        }

        if (houseBlocks <= 0) {
            throw new IllegalArgumentException("The number of house blocks must be positive");
        }

        this.name = name;
        this.position = position;
        this.houseBlocks = houseBlocks;
        this.houseBlocksList = new ArrayList<>();
    }

    public String getName() { return name; }

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public int getNumberOfHouseBlocks() { return houseBlocks; }

    public List<HouseBlock> getHouseBlocksList() { return new ArrayList<>(houseBlocksList); }
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
    public void setPositionX(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.position.setX(x);
    }


    /**
     * Sets the y-coordinate of the city's location.
     *
     * @param y The new y-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code y} is not positive.
     */
    public void setPositionY(int y) {
        if (y <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.position.setY(y);
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
        this.houseBlocks = numberOfHouseBlocks;
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }


    /**
     * Generates house blocks for the city based on either manual or automatic placement.
     * Manual placement should be handled by the user via the user interface.
     *
     * @param automatic If true, the house blocks are distributed randomly around the city's position.
     * @param map The map on which the city is placed, required for automatic generation.
     */
    public void generateHouseBlocks(boolean automatic, Map map) {
        houseBlocksList.clear();

        if (automatic) {
            generateRandomHouseBlocks(map);
        } else {
            // Implementation for manual placement will be done in the UI
        }
    }

    /**
     * Generates house blocks placed randomly around the city's position.
     * Ensures that the blocks are within the map boundaries and do not overlap with
     * the city's position.
     *
     * @param map The map on which the city is placed.
     */
    public void generateRandomHouseBlocks(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }

        houseBlocksList.clear();
        Random random = new Random();

        for (int i = 0; i < houseBlocks; i++) {
            Position blockPosition;
            do {
                int offsetX = random.nextInt(11) - 5; // -5 to 5
                int offsetY = random.nextInt(11) - 5;

                int blockX = position.getX() + offsetX;
                int blockY = position.getY() + offsetY;
                blockPosition = new Position(blockX, blockY);
            } while (!isValidBlockPosition(blockPosition, map));

            HouseBlock block = new HouseBlock(blockPosition);
            houseBlocksList.add(block);
        }
    }

    private boolean isValidBlockPosition(Position blockPosition, Map map) {
        // Check if the block is within map boundaries
        if (blockPosition.getX() < 0 || blockPosition.getX() >= map.getWidth() ||
                blockPosition.getY() < 0 || blockPosition.getY() >= map.getHeight()) {
            return false;
        }

        // Check if the block doesn't overlap the city's position
        if (blockPosition.equals(this.position)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", x=" + position.getX() +
                ", y=" + position.getY() +
                '}';
    }
}
