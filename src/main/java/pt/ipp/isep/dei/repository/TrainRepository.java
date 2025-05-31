package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainRepository {
    private final List<Train> trains = new ArrayList<>();
    private int nextId = 1;

    public Train createTrain(Locomotive locomotive) {
        Train train = new Train(nextId++, locomotive);
        trains.add(train);
        return train;
    }

    public void save(Train train) {
        trains.add(train);
    }

    public List<Train> getAll() {
        return new ArrayList<>(trains);
    }

    public Train findById(int id) {
        for (Train t : trains) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public boolean exists(int id) {
        return trains.stream().anyMatch(t -> t.getId() == id);
    }
}
