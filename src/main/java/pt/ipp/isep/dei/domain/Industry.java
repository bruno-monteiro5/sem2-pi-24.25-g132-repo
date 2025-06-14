package pt.ipp.isep.dei.domain;

import java.io.Serializable;

/**
 * Represents an industry with a specific name, geographical position, and type.
 * <p>
 * The Industry class provides methods to manage and access the attributes of an industry,
 * including its name, position, and type. It also includes utility methods for string
 * representation and position comparison.
 * </p>
 *
 * <p>
 * This class provides methods to manage and access the attributes of an industry, such as its name,
 * position, and type. It also includes utility methods for string representation and position comparison.
 * </p>
 */
public class Industry implements Serializable {

    private String name;
    private Position position;
    private IndustryType type;
    private CargoType cargoType;
    private int yearlyQuantity;


    /**
     * Constructs an Industry with a custom name, position, and type.
     *
     * @param name     The name of the industry (e.g., "TechCorp").
     * @param position The geographical position of the industry.
     * @param type     The type/category of the industry.
     */
    public Industry(String name, Position position, IndustryType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    /**
     * Constructs an Industry using a predefined IndustryData and a position.
     *
     * @param data     The predefined data for the industry.
     * @param position The geographical position of the industry.
     */
    public Industry(IndustryData data, Position position) {
        this.name = data.getName();
        this.type = data.getType();
        this.position = position;
    }

    public CargoType getCargoType() {
        return this.cargoType;
    }

    public int getYearlyQuantity() {
        return this.yearlyQuantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public IndustryType getType() {
        return type;
    }

    public void setType(IndustryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Industry{" +
                "-Name='" + name + '\'' +
                "-Position=" + position.toString() +
                "-Type=" + type +
                '}';
    }

    public boolean isAtPosition(Position position) {
        return this.position.equals(position);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Industry industry = (Industry) o;

        return name.equalsIgnoreCase(industry.name)
                && position.equals(industry.position)
                && type == industry.type;
    }
}