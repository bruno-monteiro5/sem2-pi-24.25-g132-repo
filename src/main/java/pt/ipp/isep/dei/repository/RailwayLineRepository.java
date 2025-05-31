package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.LineType;
import pt.ipp.isep.dei.domain.RailwayLine;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;

import java.util.ArrayList;

public class RailwayLineRepository {
    ArrayList<RailwayLine> railwayLines;

    public RailwayLineRepository() {
        railwayLines = new ArrayList<>();
    }

    public ArrayList<RailwayLine> getRailwayLines(Scenario scenario) {
        ArrayList<RailwayLine> lines = new ArrayList<>();

        for (int i = 0; i < railwayLines.size(); ++i) {
            if (railwayLines.get(i).getScenario() == scenario) {
                lines.add(railwayLines.get(i));
            }
        }

        return lines;
    }

    public void addRailwayLine(Scenario scenario, Station station1, Station station2, LineType type) {
        railwayLines.add(new RailwayLine(scenario, station1, station2, type));
    }

    public boolean connectionExists(Scenario scenario, Station station1, Station station2) {
        boolean exists = false;

        for (int i = 0; i < railwayLines.size(); ++i) {
            if (railwayLines.get(i).getScenario() == scenario) {
                Station s1 = railwayLines.get(i).getStation1(),
                        s2 = railwayLines.get(i).getStation2();

                if ((s1 == station1 && s2 == station2) || (s1 == station2 && s2 == station1)) {
                    exists = true;
                }
            }
        }

        return exists;
    }

    public RailwayLine getConnection(Scenario scenario, Station station1, Station station2) {
        RailwayLine connection = null;

        for (int i = 0; i < railwayLines.size(); ++i) {
            if (railwayLines.get(i).getScenario() == scenario) {
                Station s1 = railwayLines.get(i).getStation1(),
                        s2 = railwayLines.get(i).getStation2();

                if ((s1 == station1 && s2 == station2) || (s1 == station2 && s2 == station1)) {
                    connection = railwayLines.get(i);
                }
            }
        }

        return connection;
    }
}