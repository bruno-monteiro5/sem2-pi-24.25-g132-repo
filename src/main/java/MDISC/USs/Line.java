package MDISC.USs;

public class Line {
    private final Station stationA;
    private final Station stationB;
    private final boolean electrified;
    private final int distance;

    public Line(Station stationA, Station stationB, boolean electrified, int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be a positive integer.");
        }
        this.stationA = stationA;
        this.stationB = stationB;
        this.electrified = electrified;
        this.distance = distance;
    }

    public Station getStationA() {
        return stationA;
    }

    public Station getStationB() {
        return stationB;
    }

    public boolean isElectrified() {
        return electrified;
    }

    public int getDistance() {
        return distance;
    }

    public boolean connects(Station s1, Station s2) {
        return (stationA.equals(s1) && stationB.equals(s2)) || (stationA.equals(s2) && stationB.equals(s1));
    }

    @Override
    public String toString() {
        return stationA + " <-> " + stationB + " | " + distance + "km | " + (electrified ? "electrified" : "non-electrified");
    }
}
