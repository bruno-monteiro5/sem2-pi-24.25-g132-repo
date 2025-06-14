package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Delivery;
import pt.ipp.isep.dei.domain.Station;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargoDeliveryRepository {
    private final Map<Integer, List<Delivery>> deliveriesByYear = new HashMap<>();
    private final Map<Delivery, Station> deliveryStations = new HashMap<>();

    public void addDelivery(Station station, Delivery delivery, int year) {
        deliveriesByYear.computeIfAbsent(year, k -> new ArrayList<>()).add(delivery);
        deliveryStations.put(delivery, station);
    }

    public List<Delivery> getDeliveriesByYear(int year) {
        return deliveriesByYear.getOrDefault(year, new ArrayList<>());
    }

    public Station getStationForDelivery(Delivery delivery) {
        return deliveryStations.get(delivery);
    }

    public void clear() {
        deliveriesByYear.clear();
        deliveryStations.clear();
    }
}