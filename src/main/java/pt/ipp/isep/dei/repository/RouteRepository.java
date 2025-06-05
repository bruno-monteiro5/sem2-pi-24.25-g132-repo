package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteRepository {

    private final List<Route> routes = new ArrayList<>();


    public void save(Route route) {
        if (!routes.contains(route)) {
            routes.add(route);
        }
    }

    public List<Route> getAll() {
        return new ArrayList<>(routes);
    }

}