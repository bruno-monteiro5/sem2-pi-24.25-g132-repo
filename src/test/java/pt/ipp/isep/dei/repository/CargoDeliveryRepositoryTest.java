package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import pt.ipp.isep.dei.domain.*;

import java.util.List;

class CargoDeliveryRepositoryTest {
    private CargoDeliveryRepository repository;
    private Station testStation;
    private Delivery testDelivery;

    @BeforeEach
    void setUp() {
        repository = new CargoDeliveryRepository();
        testStation = new Station("TestStation", new Position(0, 0), StationType.STATION);
        testDelivery = new Delivery(CargoType.COAL);
    }

    @Test
    void whenAddingDelivery_StoreWithCorrectYear() {
        int testYear = 2023;

        repository.addDelivery(testStation, testDelivery, testYear);

        List<Delivery> deliveries = repository.getDeliveriesByYear(testYear);
        assertEquals(1, deliveries.size());
        assertEquals(testDelivery, deliveries.get(0));
    }

    @Test
    void whenGettingNonexistentYear_ReturnEmptyList() {
        List<Delivery> result = repository.getDeliveriesByYear(9999);

        assertTrue(result.isEmpty());
    }

    @Test
    void whenAddingMultipleYears_KeepThemSeparated() {
        Delivery delivery2023 = new Delivery(CargoType.PASSENGERS);
        Delivery delivery2024 = new Delivery(CargoType.MAIL);

        repository.addDelivery(testStation, delivery2023, 2023);
        repository.addDelivery(testStation, delivery2024, 2024);

        assertEquals(1, repository.getDeliveriesByYear(2023).size());
        assertEquals(1, repository.getDeliveriesByYear(2024).size());
        assertEquals(CargoType.PASSENGERS, repository.getDeliveriesByYear(2023).get(0).getType());
        assertEquals(CargoType.MAIL, repository.getDeliveriesByYear(2024).get(0).getType());
    }

    @Test
    void whenGettingStationForDelivery_ReturnCorrectStation() {
        repository.addDelivery(testStation, testDelivery, 2023);

        Station result = repository.getStationForDelivery(testDelivery);

        assertEquals(testStation, result);
    }

    @Test
    void whenClearingRepository_RemoveAllData() {
        repository.addDelivery(testStation, testDelivery, 2023);

        repository.clear();

        assertTrue(repository.getDeliveriesByYear(2023).isEmpty());
        assertNull(repository.getStationForDelivery(testDelivery));
    }

    @Test
    void whenAddingMultipleDeliveries_TrackCountCorrectly() {
        Delivery delivery1 = new Delivery(CargoType.STEEL);
        Delivery delivery2 = new Delivery(CargoType.STEEL);
        delivery2.increment();

        repository.addDelivery(testStation, delivery1, 2023);
        repository.addDelivery(testStation, delivery2, 2023);

        List<Delivery> deliveries = repository.getDeliveriesByYear(2023);
        assertEquals(2, deliveries.size());
        assertEquals(1, deliveries.get(0).getCount());
        assertEquals(2, deliveries.get(1).getCount());
        assertEquals(testStation, repository.getStationForDelivery(deliveries.get(0)));
    }
}