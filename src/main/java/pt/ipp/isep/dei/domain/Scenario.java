package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario implements Serializable {

    private final String name;
    private final Map map;
    private final ArrayList<Port> ports;
    private final ArrayList<LocomotiveType> availableLocomotiveTypes;
    private final ArrayList<Locomotive> locomotives = new ArrayList<>();
    private final ArrayList<Train> trains = new ArrayList<>();
    private final List<Station> stations = new ArrayList<>();
    public List<Train> getTrains() {
        return trains;
    }
    private List<RailwayLine> railwayLines = new ArrayList<>();




    private double industryGenerationFactor;
    private final PeriodTime periodTime;


    public Scenario(String name, Map map, PeriodTime periodTime) {
        this.name = name;
        this.map = map;
        this.ports = new ArrayList<>();
        this.availableLocomotiveTypes = new ArrayList<>();
        this.industryGenerationFactor = 1.0;
        this.periodTime = periodTime;

    }

    public List<Locomotive> getLocomotives() {
        return locomotives;
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



    public void addTrain(Train train) {
        trains.add(train);
    }

    public void addLocomotiveType(LocomotiveType type) {
        if (!availableLocomotiveTypes.contains(type)) {
            availableLocomotiveTypes.add(type);
        }
    }

    public double getIndustryGenerationFactor() {
        return industryGenerationFactor;
    }

    public void setIndustryGenerationFactor(double factor) {
        this.industryGenerationFactor = factor;
    }


    public boolean isValid() {
        return periodTime != null && periodTime.isValid() && map != null;
    }


    public boolean hasStationAt(Position p) {
        for (Station s : stations) {
            if (s.getLocation().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean addStation(Station s) {
        if (s == null){
            return false;
        }

        for (Station station : stations) {
            if (station.getName().equals(s.getName())) {
                return false;
            }
        }

        return stations.add(s);
    }

    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }

    public boolean addRailwayLine(RailwayLine line) {
        if (line == null || railwayLines.contains(line)) {
            return false;
        }

        // Verify if both the stations exist in this map
        if (!stations.contains(line.getStation1()) || !stations.contains(line.getStation2())) {
            return false;
        }

        return railwayLines.add(line);
    }


    public List<RailwayLine> getRailwayLines() {
        return new ArrayList<>(railwayLines);
    }


    public RailwayLine getLineBetweenStations(Station a, Station b) {
        if (a == null || b == null) return null;

        for (RailwayLine line : railwayLines) {
            if (line.connects(a, b)) {
                return line;
            }
        }
        return null;
    }


    public boolean removeRailwayLine(RailwayLine line) {
        return railwayLines.remove(line);
    }


    public boolean areStationsConnected(Station a, Station b) {
        return getLineBetweenStations(a, b) != null;
    }




}