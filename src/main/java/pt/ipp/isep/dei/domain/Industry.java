package pt.ipp.isep.dei.domain;

/**
 * Represents an Industry with a specific name, geographical position, and type.
 * <p>
 * This class provides methods to manage and access the attributes of an industry, such as its name,
 * position, and type. It also includes utility methods for string representation and position comparison.
 * </p>
 */
public class Industry {

    // Attributes
    private String name; // The name of the industry
    private Position position; // The geographical position of the industry
    private IndustryType type; // The type/category of the industry

    /**
     * Constructs an {@code Industry} object with the specified name, position, and type.
     *
     * @param name     The name of the industry (e.g., "TechCorp").
     * @param position The geographical position of the industry, represented as a {@link Position} object.
     * @param type     The type of the industry, selected from {@link IndustryType}.
     */
    public Industry(String name, Position position, IndustryType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    /**
     * Retrieves the name of the industry.
     *
     * @return A {@link String} representing the name of the industry.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the industry.
     *
     * @param name A {@link String} representing the new name of the industry.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the geographical position of the industry.
     *
     * @return A {@link Position} object representing the industry's location.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Updates the geographical position of the industry.
     *
     * @param position A {@link Position} object representing the new location of the industry.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the type of the industry.
     *
     * @return An {@link IndustryType} representing the type or category of the industry.
     */
    public IndustryType getType() {
        return type;
    }

    /**
     * Updates the type or category of the industry.
     *
     * @param type An {@link IndustryType} representing the new type of the industry.
     */
    public void setType(IndustryType type) {
        this.type = type;
    }

    /**
     * Provides a {@link String} representation of the industry.
     * <p>
     * The string representation includes the name, geographical position, and type of the industry.
     * </p>
     *
     * @return A {@link String} containing the name, position, and type of the industry.
     */
    @Override
    public String toString() {
        return "Industry{" +
                "-Name='" + name + '\'' +
                "-Position=" + position.toString() +
                "-Type=" + type +
                '}';
    }

    /**
     * Checks whether the industry is located at a specific position.
     *
     * @param position A {@link Position} object to compare with the industry's current position.
     * @return {@code true} if the industry is at the specified position; otherwise, {@code false}.
     */
    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }
}
