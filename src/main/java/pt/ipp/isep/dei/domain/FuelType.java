package pt.ipp.isep.dei.domain;

import java.util.List;

/**
 * Abstract class representing a type of fuel used by locomotives.
 * <p>
 * Fuel types determine whether a locomotive requires an electrified line
 * and are used to differentiate train technology in scenarios.
 */
public abstract class FuelType implements Comparable<FuelType> {

    /**
     * The name of the fuel type.
     */
    private final String name;

    /**
     * Constructs a fuel type with the given name.
     *
     * @param name the name of the fuel type
     */
    public FuelType(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the fuel type.
     *
     * @return the name as a string
     */
    public String name() {
        return name;
    }

    /**
     * Determines whether this fuel type requires an electrified line.
     *
     * @return true if electrification is required; false otherwise
     */
    public abstract boolean requiresElectrifiedLine();

    /**
     * Returns the name of the fuel type as a string.
     *
     * @return the fuel type name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a list of all available fuel types.
     *
     * @return a list containing Steam, Diesel, and Electric fuel types
     */
    public static List<FuelType> availableFuelTypes() {
        List<FuelType> types = new java.util.ArrayList<>();
        types.add(new Steam());
        types.add(new Diesel());
        types.add(new Electric());
        return types;
    }

    /**
     * Compares this fuel type with another based on their names.
     *
     * @param other the other fuel type to compare with
     * @return a negative number, zero, or a positive number as this name is less than,
     * equal to, or greater than the other name
     */
    @Override
    public int compareTo(FuelType other) {
        return this.name.compareTo(other.name);
    }
}
