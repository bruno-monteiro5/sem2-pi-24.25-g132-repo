package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a city within the simulation, which can be added to a specific map.
 * A city is characterized by its name, its position on the map (XY coordinates),
 * and a configurable number of house blocks.
 *
 * <p>
 * In the context of the simulator, a {@code City} serves as a core domain entity,
 * playing the following roles:
 * <ul>
 *     <li>Generating and consuming passengers and mail.</li>
 *     <li>Consuming final products, such as food, textiles, and cars.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Each city has the following main attributes:
 * <ul>
 *     <li>A <b>name</b>, which uniquely identifies the city in the user's context.
 *          Names must not include special characters or digits but can contain spaces.</li>
 *     <li>A <b>position</b> defined by XY coordinates, indicating its location on the map.
 *          Both coordinates must be positive integers.</li>
 *     <li>A <b>number of house blocks</b>. The house blocks can be added manually
 *          or generated automatically (randomly distributed around the city's position).</li>
 * </ul>
 * </p>
 *
 * <p>
 * Editing capabilities include renaming a city, updating its position, or modifying the
 * number of house blocks. Validation rules are enforced to ensure data integrity:
 * <ul>
 *     <li>City names must not contain special characters or digits.</li>
 *     <li>Coordinates must remain positive.</li>
 *     <li>The number of house blocks must always be a positive value.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Additional considerations:
 * <ul>
 *     <li>City names do not need to be unique but should avoid duplication within the same map to minimize confusion for users.</li>
 *     <li>A city cannot overlap with other entities, such as other cities or industries, on the map.</li>
 *     <li>Cities can only be removed in edit mode, not during simulation mode.</li>
 * </ul>
 * </p>
 */
public class City {

    private Map map;
    private String name;
    private Position position;
    private int houseBlocks;
    private List<HouseBlock> houseBlocksList;

    /**
     * Constructs a {@code City} with a name, position, and number of house blocks.
     *
     * @param map         The map to which the city belongs.
     * @param name        The name of the city. Must not be null, empty, or contain special characters or digits.
     * @param position    The coordinates (XY) of the city's location within the map. Coordinates must be positive.
     * @param houseBlocks The number of house blocks in the city. Must be a positive integer.
     * @throws IllegalArgumentException if {@code name} is invalid, {@code position} has negative values, or {@code houseBlocks} is not positive.
     */
    public City(Map map, String name, Position position, Integer houseBlocks) {
        if (name == null || position == null || houseBlocks == null) {
            throw new IllegalArgumentException("No argument can be null");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name cannot contain special characters or digits");
        }


        this.name = name;
        this.position = position;
        this.houseBlocks = houseBlocks;
        this.map = map;
    }

    public Map getMap() { return map; }

    public String getName() { return name; }

    public Position getPosition() { return position; }

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public int getNumberOfHouseBlocks() { return houseBlocks; }

    public List<HouseBlock> getHouseBlocksList() { return new ArrayList<>(houseBlocksList); }

    /**
     * Sets the map that the city belongs to.
     *
     * @param map The map to associate with the city. Must not be {@code null}.
     * @throws IllegalArgumentException if the {@code map} is {@code null}.
     */
    public void setMap(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null.");
        }
        this.map = map;
    }

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

    /**
     * Validates the city name, ensuring it is non-empty and does not contain
     * special characters or digits.
     *
     * @param name The name to validate.
     * @return {@code true} if the name is valid, {@code false} otherwise.
     */
    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Randomly generates house blocks around the city's position.
     *
     * <p>
     * The random generation ensures:
     * <ul>
     *     <li>House blocks are within the map's boundaries.</li>
     *     <li>House blocks do not overlap with the city's position.</li>
     * </ul>
     * </p>
     *
     * @throws IllegalArgumentException if the map property is null.
     */
    public void generateRandomHouseBlocks() {
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }

        Random random = new Random();
        this.houseBlocks = random.nextInt(10) + 1;
        houseBlocksList = new ArrayList<>();

        for (int i = 0;i<houseBlocks;i++) {
            HouseBlock houseBlock = new HouseBlock(new Position(this.position.getX() + i, this.position.getY()));
            houseBlocksList.add(houseBlock);
        }
    }

    /**
     * Validates whether a given house block's position is valid within the current map.
     *
     * <p>
     * A valid position must:
     * <ul>
     *     <li>Be within the map's boundaries.</li>
     *     <li>Not overlap with the city's position or other existing elements.</li>
     * </ul>
     * </p>
     *
     * @param blockPosition The position of the block to validate.
     * @param map           The map where the city and block are located.
     * @return {@code true} if the block's position is valid, {@code false} otherwise.
     */
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
                ", houseBlocks=" + houseBlocks +
                '}';
    }

}
