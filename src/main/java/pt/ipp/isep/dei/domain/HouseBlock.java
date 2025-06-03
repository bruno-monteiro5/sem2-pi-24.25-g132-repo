package pt.ipp.isep.dei.domain;

/**
 * Represents a block of houses within a city on the map.
 *
 * <p>The {@code HouseBlock} class encapsulates information about a block of houses
 * and its associated position within a city. Each house block must be located at a valid
 * {@link Position} on the map, adhering to the constraints and boundaries defined
 * by the city's map.</p>
 *
 * <p>This class provides functionality to:
 * <ul>
 *     <li>Define the house block's position upon creation.</li>
 *     <li>Update the house block's position as needed.</li>
 *     <li>Generate a string representation of the house block, useful for logging or debugging.</li>
 * </ul>
 * </p>
 */
public class HouseBlock {

    /**
     * The position of the house block on the map.
     *
     * <p>This attribute holds the {@link Position} object that specifies
     * the house block's location on the map. The position must not be null
     * and must be set during the creation of the house block.</p>
     */
    private Position position;

    /**
     * Constructs a {@code HouseBlock} with the specified position.
     *
     * <p>This constructor initializes the house block's position. The position must
     * not be {@code null} to ensure the house block is validly placed on the map.</p>
     *
     * @param position The location of the house block on the map. Must not be {@code null}.
     * @throws IllegalArgumentException If the provided {@code position} is {@code null}.
     */
    public HouseBlock(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        this.position = position;
    }

    public Position getPosition() { return position;}


    /**
     * Updates the position of the house block.
     *
     * <p>This method allows modifying the house block's position after its creation,
     * as long as the new position is valid and not {@code null}.</p>
     *
     * @param position The new {@link Position} of the house block. Must not be {@code null}.
     * @throws IllegalArgumentException If the provided {@code position} is {@code null}.
     */
    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        this.position = position;
    }

    /**
     * Retrieves a string representation of the house block.
     *
     * <p>The string representation includes the {@link Position} of the house block,
     * providing useful information for logging, debugging, or user output.</p>
     *
     * @return A {@link String} representation of the house block, including its position.
     */
    @Override
    public String toString() {
        return "HouseBlock{" +
                "position=" + position +
                '}';
    }
}