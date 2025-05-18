package pt.ipp.isep.dei.domain;

/**
 * Represents an Industry with a specific name, geographical position, and type.
 * This class provides methods to manage and access the industry's attributes.
 */
public class Industry {

    // Attributes
    private String name; // The name of the industry
    private Position position; // The geographical position of the industry
    private IndustryTypes.IndustryType type; // The type/category of the industry

    /**
     * Constructs an Industry object with the specified name, position, and type.
     *
     * @param name The name of the industry (e.g., "TechCorp").
     * @param position The geographical position of the industry, represented as a {@link Position} object.
     * @param type The type of the industry, selected from {@link IndustryTypes.IndustryType}.
     */
    public Industry(String name, Position position, IndustryTypes.IndustryType type) {
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
     * Updates the position of the industry.
     *
     * @param position A {@link Position} object representing the new location of the industry.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the type of the industry.
     *
     * @return An {@link IndustryTypes.IndustryType} representing the industry's type/category.
     */
    public IndustryTypes.IndustryType getType() {
        return type;
    }

    /**
     * Updates the type/category of the industry.
     *
     * @param type A {@link IndustryTypes.IndustryType} representing the new type of the industry.
     */
    public void setType(IndustryTypes.IndustryType type) {
        this.type = type;
    }

    /**
     * Provides a {@link String} representation of the industry.
     *
     * The format includes the name, position, and type of the industry.
     *
     * @return A {@link String} containing the industry's name, position, and type.
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
     * @return {@code true} if the industry is at the specified position, otherwise {@code false}.
     */
    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }
}
