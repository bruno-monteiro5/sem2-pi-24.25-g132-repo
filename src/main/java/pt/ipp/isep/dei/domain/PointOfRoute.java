package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a point in a train's route.
 * <p>
 * Each point defines a station to stop at, the cargo types to pick up,
 * and the loading mode to apply before departure.
 */
public class PointOfRoute implements Serializable {

    /**
     * The station associated with this point.
     */
    private final Station station;

    /**
     * The list of cargo types to be picked up at this station.
     */
    private final List<String> cargoTypes;

    /**
     * The cargo loading mode for this stop.
     */
    private final CargoMode mode;

    /**
     * Constructs a new point of route.
     *
     * @param station     the station to stop at
     * @param cargoTypes  the cargo types to pick up
     * @param mode        the cargo loading mode (e.g., FULL, HALF, AVAILABLE)
     */
    public PointOfRoute(Station station, List<String> cargoTypes, CargoMode mode) {
        this.station = station;
        this.cargoTypes = cargoTypes;
        this.mode = mode;
    }

    /**
     * Gets the station for this point of route.
     *
     * @return the station
     */
    public Station getStation() {
        return station;
    }

    /**
     * Gets the list of cargo types to be picked up at this point.
     *
     * @return list of cargo type strings
     */
    public List<String> getCargoTypes() {
        return cargoTypes;
    }

    /**
     * Gets the cargo loading mode.
     *
     * @return the mode (FULL, HALF, or AVAILABLE)
     */
    public CargoMode getMode() {
        return mode;
    }

    /**
     * Returns a string representation of the point of route.
     *
     * @return formatted string with station, cargo types, and mode
     */
    @Override
    public String toString() {
        return String.format("PointOfRoute{station=%s, cargoTypes=%s, mode=%s}",
                station.getName(), cargoTypes, mode);
    }
}
