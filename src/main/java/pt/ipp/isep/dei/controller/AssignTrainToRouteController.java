package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Route;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.StationRepository;
import pt.ipp.isep.dei.repository.TrainRepository;

import java.util.List;


/**
 * Controller responsible for assigning a route to a train.
 * <p>
 * Provides access to available trains and stations, and allows associating a route to a selected train.
 */
public class AssignTrainToRouteController {

    /**
     * Repository for accessing and managing trains.
     */
    private final TrainRepository trainRepository;

    /**
     * Repository for accessing and managing stations.
     */
    private final StationRepository stationRepository;


    /**
     * Constructs a new controller for assigning routes to trains.
     */
    public AssignTrainToRouteController() {
        this.trainRepository = Repositories.getInstance().getTrainRepository();
        this.stationRepository = Repositories.getInstance().getStationRepository();
    }

    /**
     * Retrieves all available trains in the system.
     *
     * @return a list of all {@link Train} objects
     */
    public List<Train> getAvailableTrains() {
        return trainRepository.getAll();
    }


    /**
     * Retrieves all stations currently available in the system.
     *
     * @return a list of all {@link Station} objects
     */
    public List<Station> getAllStations() {
        return stationRepository.getAll();
    }

    /**
     * Assigns a route to a given train.
     *
     * @param train the train to assign the route to
     * @param route the route to assign
     */
    public void assignRouteToTrain(Train train, Route route) {
        train.assignRoute(route);
    }
}
