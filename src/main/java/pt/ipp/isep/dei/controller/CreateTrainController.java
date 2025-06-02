package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.TrainRepository;

public class CreateTrainController {

    private final TrainRepository trainRepository = Repositories.getInstance().getTrainRepository();

    public Train createTrainFromLocomotive(Locomotive loco) {
        return trainRepository.createTrain(loco);
    }
}