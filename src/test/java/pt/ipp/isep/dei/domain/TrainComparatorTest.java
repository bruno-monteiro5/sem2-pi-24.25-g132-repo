/**
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.LocomotiveType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrainComparatorTest {
    private final Locomotive steamLoco1 = new Locomotive(1, LocomotiveType.STEPHENSON_ROCKET);
    private final Locomotive steamLoco2 = new Locomotive(2, LocomotiveType.IRON_DUKE);
    private final Locomotive dieselLoco = new Locomotive(3, LocomotiveType.F9);
    private final Locomotive electricLoco = new Locomotive(4, LocomotiveType.GG1);

    private final TrainComparator comparator = new TrainComparator();

    @Test
    void compare_ShouldOrderByFuelTypeFirst() {
        Train t1 = new Train(1, steamLoco1);    // Steam
        Train t2 = new Train(2, dieselLoco);    // Diesel
        Train t3 = new Train(3, electricLoco);  // Electric

        List<Train> trains = Arrays.asList(t3, t2, t1);
        Collections.sort(trains, comparator);

        assertEquals("Steam", trains.get(0).getLocomotive().getType().getFuelType());
        assertEquals("Diesel", trains.get(1).getLocomotive().getType().getFuelType());
        assertEquals("Electric", trains.get(2).getLocomotive().getType().getFuelType());
    }

    @Test
    void compare_ShouldOrderByNameWhenFuelTypeIsEqual() {
        Train t1 = new Train(1, steamLoco1);
        Train t2 = new Train(2, steamLoco2);

        List<Train> trains = Arrays.asList(t2, t1);
        Collections.sort(trains, comparator);

        assertEquals("Iron Duke", trains.get(0).getLocomotive().getType().getName());
        assertEquals("Stephenson Rocket", trains.get(1).getLocomotive().getType().getName());
    }

    @Test
    void compare_ShouldHandleEmptyList() {
        List<Train> emptyList = Collections.emptyList();
        assertDoesNotThrow(() -> Collections.sort(emptyList, comparator));
    }

    @Test
    void compare_ShouldMaintainOrderForEqualTrains() {
        Train t1 = new Train(1, steamLoco1);
        Train t2 = new Train(2, steamLoco1);

        List<Train> trains = Arrays.asList(t1, t2);
        Collections.sort(trains, comparator);

        assertEquals(1, trains.get(0).getId());
        assertEquals(2, trains.get(1).getId());
    }

    @Test
    void compare_ShouldCorrectlyOrderMixedTrains() {
        Train t1 = new Train(1, steamLoco2);
        Train t2 = new Train(2, dieselLoco);
        Train t3 = new Train(3, steamLoco1);

        List<Train> trains = Arrays.asList(t3, t2, t1);
        Collections.sort(trains, comparator);

        assertEquals("Iron Duke", trains.get(0).getLocomotive().getType().getName());
        assertEquals("Stephenson Rocket", trains.get(1).getLocomotive().getType().getName());
        assertEquals("F9", trains.get(2).getLocomotive().getType().getName());
    }
}
 */