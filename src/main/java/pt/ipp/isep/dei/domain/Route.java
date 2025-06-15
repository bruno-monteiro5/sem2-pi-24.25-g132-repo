package pt.ipp.isep.dei.domain;

import pt.ipp.isep.dei.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a train route, composed of an ordered list of {@link PointOfRoute}.
 * <p>
 * A route may have a name and defines the sequence of stations a train will visit.
 */
public class Route implements Serializable {

    /**
     * List of points of the route (station + cargo configuration).
     */
    private final List<PointOfRoute> pointsOfRoute;

    /**
     * Optional name for the route.
     */
    private String name;

    /**
     * Constructs an empty route.
     */
    public Route() {
        this.pointsOfRoute = new ArrayList<>();
    }

    /**
     * Gets the route's name.
     *
     * @return the name of the route
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the route's name.
     *
     * @param name the name to assign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a point of route to the list.
     *
     * @param point the point to add
     */
    public void addPointOfRoute(PointOfRoute point) {
        pointsOfRoute.add(point);
    }

    /**
     * Returns the list of all points in this route.
     *
     * @return the list of {@link PointOfRoute}
     */
    public List<PointOfRoute> getPointsOfRoute() {
        return pointsOfRoute;
    }

    /**
     * Gets the first point of the route.
     *
     * @return the first {@link PointOfRoute}, or null if the route is empty
     */
    public PointOfRoute getFirstPoint() {
        if (pointsOfRoute.isEmpty()) return null;
        return pointsOfRoute.get(0);
    }

    /**
     * Gets the last point of the route.
     *
     * @return the last {@link PointOfRoute}, or null if the route is empty
     */
    public PointOfRoute getLastPoint() {
        if (pointsOfRoute.isEmpty()) return null;
        return pointsOfRoute.get(pointsOfRoute.size() - 1);
    }

    /**
     * Finds the next station after the current one in the route.
     *
     * @param current the current station
     * @return the next station, or null if it's the last or not found
     */
    public Station getNextStation(Station current) {
        for (int i = 0; i < pointsOfRoute.size() - 1; i++) {
            if (pointsOfRoute.get(i).getStation().equals(current)) {
                return pointsOfRoute.get(i + 1).getStation();
            }
        }
        return null;
    }

    /**
     * Retrieves the railway line connecting two stations in the current map.
     *
     * @param from the origin station
     * @param to   the destination station
     * @return the {@link RailwayLine} if found; null otherwise
     */
    public RailwayLine getRailwayLine(Station from, Station to) {
        for (RailwayLine line : Repositories.getInstance().getRailwayLineRepository().getAll()) {
            if ((line.getStation1().equals(from) && line.getStation2().equals(to)) ||
                    (line.getStation2().equals(from) && line.getStation1().equals(to))) {
                return line;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the route.
     *
     * @return the route name and list of points
     */
    @Override
    public String toString() {
        return String.format("Route{name='%s', pointsOfRoute=%s}", name, pointsOfRoute);
    }
}
