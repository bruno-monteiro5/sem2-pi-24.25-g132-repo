package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {

    private final List<PointOfRoute> pointsOfRoute;

    public Route() {
        this.pointsOfRoute = new ArrayList<>();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addPointOfRoute(PointOfRoute point) {
        pointsOfRoute.add(point);
    }

    public List<PointOfRoute> getPointsOfRoute() {
        return pointsOfRoute;
    }

    public PointOfRoute getFirstPoint() {
        if (pointsOfRoute.isEmpty()) return null;
        return pointsOfRoute.get(0);
    }

    public PointOfRoute getLastPoint() {
        if (pointsOfRoute.isEmpty()) return null;
        return pointsOfRoute.get(pointsOfRoute.size() - 1);
    }

    public Station getNextStation(Station current) {
        for (int i = 0; i < pointsOfRoute.size() - 1; i++) {
            if (pointsOfRoute.get(i).getStation().equals(current)) {
                return pointsOfRoute.get(i + 1).getStation();
            }
        }
        return null; // caso seja a última estação ou não encontrada
    }

    @Override
    public String toString() {
        return "Route{" + pointsOfRoute + '}';
    }
}