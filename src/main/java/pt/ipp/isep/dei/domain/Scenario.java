package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simulation scenario, which includes the map, time period, stations,
 * railway lines, locomotives, and trains.
 * <p>
 * A scenario defines the rules and available resources for the simulation to run.
 */
public class Scenario implements Serializable {

    /**
     * The name of the scenario.
     */
    private final String name;

    /**
     * The map associated with this scenario.
     */
    private final Map map;

    /**
     * The list of ports included in the scenario.
     */
    private final ArrayList<Port> ports;

    /**
     * The list of locomotive types available in the scenario.
     */
    private final ArrayList<LocomotiveType> availableLocomotiveTypes;

    /**
     * The list of locomotives created in the scenario.
     */
    private final ArrayList<Locomotive> locomotives = new ArrayList<>();

    /**
     * The list of trains operating in the scenario.
     */
    private final ArrayList<Train> trains = new ArrayList<>();

    /**
     * The list of stations on the map.
     */
    private final List<Station> stations = new ArrayList<>();

    /**
     * The list of railway lines connecting stations.
     */
    private List<RailwayLine> railwayLines = new ArrayList<>();

    /**
     * Factor that adjusts the generation frequency of industries.
     */
    private double industryGenerationFactor;

    /**
     * The time period in which the scenario takes place.
     */
    private final PeriodTime periodTime;

    /**
     * Creates a new scenario with a name, map, and time period.
     *
     * @param name        the scenario name
     * @param map         the associated map
     * @param periodTime  the time period
     */
    public Scenario(String name, Map map, PeriodTime periodTime) {
        this.name = name;
        this.map = map;
        this.ports = new ArrayList<>();
        this.availableLocomotiveTypes = new ArrayList<>();
        this.industryGenerationFactor = 1.0;
        this.periodTime = periodTime;
    }

    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
    }

    public PeriodTime getPeriodTime() {
        return periodTime;
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }

    public void addPort(Port port) {
        ports.add(port);
    }

    public ArrayList<LocomotiveType> getAvailableLocomotiveTypes() {
        return availableLocomotiveTypes;
    }

    public void addLocomotiveType(LocomotiveType type) {
        if (!availableLocomotiveTypes.contains(type)) {
            availableLocomotiveTypes.add(type);
        }
    }

    public ArrayList<Locomotive> getLocomotives() {
        return locomotives;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public double getIndustryGenerationFactor() {
        return industryGenerationFactor;
    }

    public void setIndustryGenerationFactor(double factor) {
        this.industryGenerationFactor = factor;
    }

    /**
     * Checks if the scenario is valid (period and map must not be null).
     *
     * @return true if valid; false otherwise
     */
    public boolean isValid() {
        return periodTime != null && periodTime.isValid() && map != null;
    }

    /**
     * Checks whether a station exists at the specified position.
     *
     * @param p the position to check
     * @return true if a station exists at the position; false otherwise
     */
    public boolean hasStationAt(Position p) {
        for (Station s : stations) {
            if (s.getLocation().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a station to the scenario, if it does not already exist.
     *
     * @param s the station to add
     * @return true if added successfully; false if station is null or name already exists
     */
    public boolean addStation(Station s) {
        if (s == null) return false;

        for (Station station : stations) {
            if (station.getName().equals(s.getName())) {
                return false;
            }
        }

        return stations.add(s);
    }

    /**
     * Gets all stations in the scenario.
     *
     * @return a list of stations
     */
    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }

    /**
     * Adds a railway line to the scenario if valid.
     *
     * @param line the line to add
     * @return true if added; false if already exists or stations are invalid
     */
    public boolean addRailwayLine(RailwayLine line) {
        if (line == null || railwayLines.contains(line)) {
            return false;
        }

        if (!stations.contains(line.getStation1()) || !stations.contains(line.getStation2())) {
            return false;
        }

        return railwayLines.add(line);
    }

    /**
     * Gets all railway lines in the scenario.
     *
     * @return a list of railway lines
     */
    public List<RailwayLine> getRailwayLines() {
        return new ArrayList<>(railwayLines);
    }

    /**
     * Gets the railway line connecting two specific stations.
     *
     * @param a first station
     * @param b second station
     * @return the line connecting them, or null if none
     */
    public RailwayLine getLineBetweenStations(Station a, Station b) {
        if (a == null || b == null) return null;

        for (RailwayLine line : railwayLines) {
            if (line.connects(a, b)) {
                return line;
            }
        }
        return null;
    }

    /**
     * Removes a railway line from the scenario.
     *
     * @param line the line to remove
     * @return true if removed; false otherwise
     */
    public boolean removeRailwayLine(RailwayLine line) {
        return railwayLines.remove(line);
    }

    /**
     * Checks if two stations are connected by a railway line.
     *
     * @param a first station
     * @param b second station
     * @return true if connected; false otherwise
     */
    public boolean areStationsConnected(Station a, Station b) {
        return getLineBetweenStations(a, b) != null;
    }



    /**
     * Returns a string representation of the scenario.
     *
     * @return formatted string with name and counts of key elements
     */
    @Override
    public String toString() {
        return String.format("Scenario{name='%s', map=%s, periodTime=%s, stations=%d, railwayLines=%d, trains=%d}",
                name, map, periodTime, stations.size(), railwayLines.size(), trains.size());
    }
}
