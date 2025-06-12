package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.RailwayLine;
import java.util.ArrayList;
import java.util.List;

public class RailwayLineRepository {
    private final List<RailwayLine> railwayLines;

    public RailwayLineRepository() {
        this.railwayLines = new ArrayList<>();
    }

    public boolean addRailwayLine(RailwayLine line) {
        if (line == null || railwayLines.contains(line)) {
            return false;
        }
        return railwayLines.add(line);
    }

    public List<RailwayLine> getAll() {
        return new ArrayList<>(railwayLines);
    }

    public boolean removeRailwayLine(RailwayLine line) {
        return railwayLines.remove(line);
    }

    public void clear() {
        railwayLines.clear();
    }
}