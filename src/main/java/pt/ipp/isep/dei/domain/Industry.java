package pt.ipp.isep.dei.domain;

/**
 * Represents an industry with a specific name, geographical position, and type.
 * <p>
 * The Industry class provides methods to manage and access the attributes of an industry,
 * including its name, position, and type. It also includes utility methods for string
 * representation and position comparison.
 * </p>
 *
 * <p>
 * Example of usage:
 * <pre>
 *     Position position = new Position(10, 20);
 *     IndustryType type = IndustryType.TECHNOLOGY;
 *     Industry industry = new Industry("TechCorp", position, type);
 * </pre>
 * </p>
 */
public class Industry {

    /**
     * The name of the industry (e.g., "TechCorp").
     */
    private String name;

    /**
     * The geographical position of the industry.
     */
    private Position position;

    /**
     * The type or category of the industry (e.g., Technology, Automotive, etc.).
     */
    private IndustryType type;

    /**
     * Constructs a new instance of the Industry class with a specified name, position, and type.
     *
     * @param name     The name of the industry (e.g., "TechCorp").
     * @param position The geographical position of the industry.
     * @param type     The type or category of the industry.
     */
    public Industry(String name, Position position, IndustryType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    /**
     * Constructs a new instance of the Industry class using predefined industry data and a position.
     *
     * @param data     The predefined data about the industry, containing name and type.
     * @param position The geographical position of the industry.
     */
    public Industry(IndustryData data, Position position) {
        this.name = data.getName();
        this.type = data.getType();
        this.position = position;
    }

    /**
     * Returns a string representation of the Industry instance.
     * <p>
     * The returned string includes the name, position, and type of the industry.
     * </p>
     *
     * @return A string representing the attributes of the Industry instance.
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
     * Checks if the current industry's location matches a given position.
     * <p>
     * This method compares the current position of the industry with the position passed as an argument.
     * </p>
     *
     * @param position The position to compare with the industry's current position.
     * @return {@code true} if the positions are equal, {@code false} otherwise.
     */
    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }
}
