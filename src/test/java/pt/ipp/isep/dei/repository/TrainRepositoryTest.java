/*
package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Train;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainRepositoryTest {
    private TrainRepository repository;
    private Locomotive steamLoco;
    private Locomotive dieselLoco;

    @BeforeEach
    void setUp() {
        repository = new TrainRepository();
        steamLoco = new Locomotive(1, LocomotiveType.STEPHENSON_ROCKET);
        dieselLoco = new Locomotive(2, LocomotiveType.F9);
    }

    @Test
    void createTrain_AddTrainToRepository() {
        Train train = repository.createTrain(steamLoco);
        assertNotNull(train);
        assertEquals(1, train.getId());
        assertEquals(steamLoco, train.getLocomotive());
        assertTrue(repository.getAll().contains(train));
    }

    @Test
    void getAll_ReturnEmptyListWhenNoTrains() {
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void getAll_ReturnAllTrainsInRepository() {
        Train t1 = repository.createTrain(steamLoco);
        Train t2 = repository.createTrain(dieselLoco);

        List<Train> trains = repository.getAll();
        assertEquals(2, trains.size());
        assertTrue(trains.contains(t1) && trains.contains(t2));
    }

    @Test
    void findById_ReturnTrainWhenIdExists() {
        Train train = repository.createTrain(steamLoco);
        Train found = repository.findById(train.getId());
        assertEquals(train, found);
    }

    @Test
    void findById_ReturnNullWhenIdDoesNotExist() {
        assertNull(repository.findById(999)); // ID inexistente
    }

    @Test
    void exists_ReturnTrueForExistingTrainId() {
        Train train = repository.createTrain(dieselLoco);
        assertTrue(repository.exists(train.getId()));
    }
}

 */