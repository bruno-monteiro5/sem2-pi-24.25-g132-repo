package pt.ipp.isep.dei.domain;

import java.io.Serializable;

/**
 * Represents a locomotive in the simulation.
 * <p>
 * A locomotive has a type, which defines its characteristics such as speed and fuel type.
 */
public class Locomotive implements Serializable {

    /**
     * The type of this locomotive (defines name, speed, fuel, etc.).
     */
    private final LocomotiveType type;

    /**
     * The year the locomotive becomes available or was built.
     */
    private int startYear;

    /**
     * Constructs a locomotive with the specified type.
     *
     * @param type the type of the locomotive
     */
    public Locomotive(LocomotiveType type) {
        this.type = type;
    }

    /**
     * Gets the type of the locomotive.
     *
     * @return the locomotive type
     */
    public LocomotiveType getType() {
        return type;
    }

    /**
     * Gets the start year of the locomotive.
     *
     * @return the year the locomotive becomes available
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * Returns a string representation of the locomotive.
     *
     * @return a formatted string with name, fuel type, and top speed
     */
    @Override
    public String toString() {
        return String.format("Locomotive{name='%s', fuel='%s', speed=%.1f}",
                type.getName(), type.getFuelType(), type.getTopSpeed());
    }
}
