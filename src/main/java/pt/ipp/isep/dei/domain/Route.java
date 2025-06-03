package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;


public class Route {
    private String name;
    private Train assignedTrain;
    private final List<PointOfRoute> points = new ArrayList<>();

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

    public void addPoint(PointOfRoute point) {
        points.add(point);
    }

    public List<PointOfRoute> getPoints() {
        return new ArrayList<>(points);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route '").append(name).append("' assigned to train ID ");
        sb.append(assignedTrain != null ? assignedTrain.getId() : "none").append("\n");

        for (PointOfRoute point : points) {
            sb.append("Station: ").append(point.getStation().getName())
                    .append(" | Cargos: ").append(point.getCargoesToPick())
                    .append(" | Mode: ").append(point.getCargoMode())
                    .append("\n");
        }

        return sb.toString();
    }
}
