package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.List;

public class PointOfRoute implements Serializable {

    private final Station station;
    private final List<String> cargoTypes;
    private final CargoMode mode;


    public PointOfRoute(Station station, List<String> cargoTypes, CargoMode mode) {
        this.station = station;
        this.cargoTypes = cargoTypes;
        this.mode = mode;
    }


    public Station getStation() {
        return station;
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
