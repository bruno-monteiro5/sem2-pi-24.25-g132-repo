package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {

    private String name;
    private Position position;
    private int houseBlocks;
    private List<HouseBlock> houseBlocksList;

    /**
     * Constructor for the City class.
     *
     * @param name        The name of the city. Validated for special characters and digits (AC01).
     * @param position    The position of the city on the map. Cannot be null or invalid.
     * @param houseBlocks The total number of house blocks. Must be positive.
     * @throws IllegalArgumentException If validations for name, position, or houseBlocks fail.
     */
    public City(String name, Position position, int houseBlocks) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name must only contain letters and spaces.");
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
        this.houseBlocksList = new ArrayList<>(); // Initialize an empty list of house blocks.
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getHouseBlocks() {
        return houseBlocks;
    }

    public List<HouseBlock> getHouseBlocksList() {
        return new ArrayList<>(houseBlocksList); // Return a copy to avoid direct modifications.
    }

    /**
     * Manually adds house blocks to the city.
     *
     * @param positions The list of block positions to assign to the city.
     * @throws IllegalArgumentException If the number of positions doesn't match houseBlocks or positions are invalid.
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
     * Automatically generates house blocks around the city using a normal distribution.
     *
     * @param map The map used to validate house block boundaries.
     * @throws IllegalArgumentException If generated positions exceed map boundaries.
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
     * Validates whether a block position is valid and within the map boundaries.
     */
    private boolean isValidBlockPosition(Position blockPosition, Map map) {
        if (blockPosition.getX() < 0 || blockPosition.getX() >= map.getWidth() ||
                blockPosition.getY() < 0 || blockPosition.getY() >= map.getHeight()) {
            return false;
        }
        if (blockPosition.equals(this.position)) {
            return false; // Block cannot overlap with the city's position.
        }
        return true;
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("^[a-zA-Z\\s]+$");
    }
}