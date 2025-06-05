package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.List;

public class PointOfRoute implements Serializable {

    private final Station station;
    private final List<String> cargoTypes;

    public PointOfRoute(Station station, List<String> cargoTypes) {
        this.station = station;
        this.cargoTypes = cargoTypes;
    }

    public Station station() {
        return this.station;
    }

    public List<String> getCargoTypes() {
        return cargoTypes;
    }

    @Override
    public String toString() {
        return "PointOfRoute{" +
                "station=" + station +
                ", cargoTypes=" + cargoTypes +
                '}';
    }
}
