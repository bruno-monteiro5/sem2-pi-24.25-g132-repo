package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;
import pt.ipp.isep.dei.repository.TrainRepository;

public class CreateTrainController {

    private final TrainRepository trainRepository = Repositories.getInstance().getTrainRepository();


    public Train createTrainFromLocomotive(Locomotive loco) {
        Train train = trainRepository.createTrain(loco);

        ScenarioRepository scenarioRepository = Repositories.getInstance().getScenarioRepository();
        if (scenarioRepository.getActiveScenario() != null) {
            scenarioRepository.getActiveScenario().getTrains().add(train);
        }

        return train;
    }

}