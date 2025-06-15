package pt.ipp.isep.dei.domain;

import java.io.Serializable;

/**
 * Represents a railway line that connects two distinct stations on the map.
 * <p>
 * A railway line has a type (e.g., single/double, electrified or not),
 * a fixed length, and belongs to a specific scenario.
 */
public class RailwayLine implements Serializable {

    /**
     * The scenario where this line exists.
     */
    private final Scenario scenario;

    /**
     * The first station connected by this line.
     */
    private final Station station1;

    /**
     * The second station connected by this line.
     */
    private final Station station2;

    /**
     * The type of the railway line (single/double, electrified or not).
     */
    private final LineType type;

    /**
     * The length of the railway line, in kilometers.
     */
    private final int length;

    /**
     * Constructs a railway line between two distinct stations with the specified type and length.
     *
     * @param scenario the scenario to which the line belongs
     * @param station1 the first station
     * @param station2 the second station
     * @param type     the type of the line
     * @param length   the length of the line (must be positive)
     * @throws IllegalArgumentException if any argument is null, the stations are the same, or the length is not positive
     */
    public RailwayLine(Scenario scenario, Station station1, Station station2, LineType type, int length) {
        if (scenario == null || station1 == null || station2 == null || type == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (station1.equals(station2)) {
            throw new IllegalArgumentException("Cannot connect a station to itself");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        this.scenario = scenario;
        this.station1 = station1;
        this.station2 = station2;
        this.type = type;
        this.length = length;
    }

    /**
     * Checks if this line connects two given stations (regardless of order).
     *
     * @param a first station
     * @param b second station
     * @return true if this line connects station a and b
     */
    public boolean connects(Station a, Station b) {
        return (station1.equals(a) && station2.equals(b)) || (station1.equals(b) && station2.equals(a));
    }

    /**
     * Gets the scenario associated with this line.
     *
     * @return the scenario
     */
    public Scenario getScenario() {
        return scenario;
    }

    /**
     * Gets the first station of this line.
     *
     * @return station1
     */
    public Station getStation1() {
        return station1;
    }

    /**
     * Gets the second station of this line.
     *
     * @return station2
     */
    public Station getStation2() {
        return station2;
    }

    /**
     * Gets the type of the line.
     *
     * @return the line type
     */
    public LineType getType() {
        return type;
    }

    /**
     * Gets the total length of the line in kilometers.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Alias for {@link #getType()}.
     *
     * @return the line type
     */
    public LineType getLineType() {
        return type;
    }

    /**
     * Checks if the line is electrified.
     *
     * @return true if the line is electrified; false otherwise
     */
    public boolean isElectrified() {
        return type == LineType.SINGLE_ELECTRIFIED || type == LineType.DOUBLE_ELECTRIFIED;
    }

    /**
     * Checks if the line is a double track.
     *
     * @return true if the line is double track; false otherwise
     */
    public boolean isDouble() {
        return type == LineType.DOUBLE_NOT_ELECTRICITY || type == LineType.DOUBLE_ELECTRIFIED;
    }

    /**
     * Returns a string representation of the railway line.
     *
     * @return a string with connected stations, type and length
     */
    @Override
    public String toString() {
        return String.format("RailwayLine{station1=%s, station2=%s, type=%s, length=%d}",
                station1.getName(), station2.getName(), type.name(), length);
    }
}
