package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Route;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.StationRepository;
import pt.ipp.isep.dei.repository.TrainRepository;

import java.util.List;

public class AssignTrainToRouteController {

    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;

    public AssignTrainToRouteController() {
        this.trainRepository = Repositories.getInstance().getTrainRepository();
        this.stationRepository = Repositories.getInstance().getStationRepository();
    }

    public List<Train> getAvailableTrains() {
        return trainRepository.getAll(); // Ou filtrar os que ainda não têm rota, se quiseres
    }

    public List<Station> getAllStations() {
        return stationRepository.getAll();
    }

    public void assignRouteToTrain(Train train, Route route) {
        train.assignRoute(route); // método que já criaste na classe Train
    }
}
