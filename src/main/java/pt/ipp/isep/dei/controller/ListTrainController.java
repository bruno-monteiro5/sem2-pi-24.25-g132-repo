package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.TrainComparator;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.TrainRepository;
import java.util.Collections;
import java.util.List;

public class ListTrainController {
    private final TrainRepository repository = Repositories.getInstance().getTrainRepository();

    public List<Train> getAllTrainsSorted() {
        var scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        List<Train> trains = (scenario == null) ? new java.util.ArrayList<>() : scenario.getTrains();
        trains.sort(new TrainComparator());
        return trains;
    }

}