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
        List<Train> trains = repository.getAll();
        Collections.sort(trains, new TrainComparator());
        return trains;
    }
}