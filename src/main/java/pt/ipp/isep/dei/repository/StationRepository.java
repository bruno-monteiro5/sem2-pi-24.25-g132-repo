package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing all stations in the system.
 */
public class StationRepository {

    private static StationRepository instance;
    private final List<Station> stations;

    public StationRepository() {
        this.stations = new ArrayList<>();
    }

    public static StationRepository getInstance() {
        if (instance == null) {
            instance = new StationRepository();
        }
        return instance;
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

    public List<Station> getStations() {
        return this.stations;
    }

    public Station getStationByName(String name) {
        for(Station s : this.stations) {
            if(s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }


}
