package pt.ipp.isep.dei.domain;

import javax.sound.sampled.Line;

// A railway line connects two stations from the map. Each line has a type.
public class RailwayLine {
    private Scenario scenario;
    private Station station1, station2;
    private LineType type;
    private int length;

    // Constructor of the railway line class.
    public RailwayLine(Scenario scenario, Station station1, Station station2, LineType type, int length) {
        this.scenario = scenario;
        this.station1 = station1;
        this.station2 = station2;
        this.type = type;
        this.length = length;
    }

    public boolean connects(Station a, Station b) {
        return (station1.equals(a) && station2.equals(b)) || (station1.equals(b) && station2.equals(a));
    }



    public Scenario getScenario() {
        return scenario;
    }

    public Station getStation1() {
        return this.station1;
    }

    public Station getStation2() {
        return this.station2;
    }

    public LineType getType() {
        return this.type;
    }

    public int getLength() {
        return length;
    }

    public LineType getLineType() {
        return this.type;
    }

    public boolean isElectrified() {
        return type == LineType.SINGLE_ELECTRIFIED || type == LineType.DOUBLE_ELECTRIFIED;
    }

    public boolean isDouble() {
        return type == LineType.DOUBLE_NOT_ELECTRICITY || type == LineType.DOUBLE_ELECTRIFIED;
    }
}