package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteRepository {

    private final List<Route> routes = new ArrayList<>();


    public void save(Route route) {
        if (!exists(route.getName())) {
            routes.add(route);
        }
    }

    public List<Route> getAll() {
        return new ArrayList<>(routes);
    }

    public Route findByName(String name) {
        for (Route route : routes) {
            if (route.getName().equalsIgnoreCase(name)) {
                return route;
            }
        }
        return null;
    }

    public boolean exists(String name) {
        return routes.stream()
                .anyMatch(route -> route.getName().equalsIgnoreCase(name));
    }
}
