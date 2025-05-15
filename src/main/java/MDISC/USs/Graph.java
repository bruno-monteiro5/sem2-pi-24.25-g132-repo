package MDISC.USs;

import java.util.ArrayList;

public class Graph {
    private final ArrayList<Station> vertices = new ArrayList<>();
    private final ArrayList<Line> edges = new ArrayList<>();

    public void addStation(Station s) {
        if (!vertices.contains(s)) vertices.add(s);
    }

    public void addLine(Line l) {
        edges.add(l);
        addStation(l.getStationA());
        addStation(l.getStationB());
    }

    public ArrayList<Station> getVertices() {
        return vertices;
    }

    public ArrayList<Line> getEdges() {
        return edges;
    }

    public ArrayList<Station> getNeighbours(Station s, boolean onlyElectrified) {
        ArrayList<Station> result = new ArrayList<>();
        for (Line l : edges) {
            if (onlyElectrified && !l.isElectrified()) continue;
            if (l.getStationA().equals(s)) result.add(l.getStationB());
            else if (l.getStationB().equals(s)) result.add(l.getStationA());
        }
        return result;
    }
}
