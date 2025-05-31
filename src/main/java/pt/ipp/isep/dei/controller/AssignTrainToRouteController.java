package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Route;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.RouteRepository;
import pt.ipp.isep.dei.repository.TrainRepository;

import java.util.List;

public class AssignTrainToRouteController {

    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    public AssignTrainToRouteController() {
        this.trainRepository = Repositories.getInstance().getTrainRepository();
        this.routeRepository = Repositories.getInstance().getRouteRepository();
    }

    public List<Train> getAvailableTrains() {
        return trainRepository.getAll();
    }

    public List<Route> getAllRoutes() {
        return routeRepository.getAll();
    }

    public boolean assignTrainToRoute(int trainId, String routeName) {
        Train train = trainRepository.findById(trainId);
        Route route = routeRepository.findByName(routeName);

        if (train == null || route == null) {
            return false;
        }

        route.setAssignedTrain(train);
        return true;
    }
}

