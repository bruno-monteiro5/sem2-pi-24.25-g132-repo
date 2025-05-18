/**
package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private String name;
    private Train assignedTrain;
    private final List<Station> stations = new ArrayList<>();
    private final List<List<Cargo>> cargosPerStation = new ArrayList<>();

    public void addStationWithCargo(Station station, List<Cargo> cargos) {
        this.stations.add(station);
        this.cargosPerStation.add(new ArrayList<>(cargos));
    }

    public void setAssignedTrain(Train train) {
        this.assignedTrain = train;
    }

    public Train getAssignedTrain() {
        return assignedTrain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }

    public List<List<Cargo>> getCargosPerStation() {
        List<List<Cargo>> copy = new ArrayList<>();
        for (List<Cargo> cargos : cargosPerStation) {
            copy.add(new ArrayList<>(cargos));
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route '").append(name).append("' assigned to train ID ");
        sb.append(assignedTrain != null ? assignedTrain.getIde() : "none").append("\n");

        for (int i = 0; i < stations.size(); i++) {
            sb.append("Station: ").append(stations.get(i).getName()).append(" with cargos: ").append(cargosPerStation.get(i)).append("\n");
        }
        return sb.toString();
    }
} */