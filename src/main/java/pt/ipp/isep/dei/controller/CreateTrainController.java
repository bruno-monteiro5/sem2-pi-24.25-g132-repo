package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.TrainRepository;

public class CreateTrainController {

    private final TrainRepository trainRepository = Repositories.getInstance().getTrainRepository();

    public Train createEmptyTrain() {
        int newId = trainRepository.getNextId();
        return new Train(newId);
    }

    public void saveTrain(Train train) {
        trainRepository.save(train);
    }
}