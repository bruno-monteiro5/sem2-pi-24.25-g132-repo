package pt.ipp.isep.dei.domain;

import java.util.List;

public class PointOfRoute {
    private Station station;
    private List<String> cargoesToPick;
    private CargoMode cargoMode;

    public PointOfRoute(Station station, List<String> cargoesToPick, CargoMode cargoMode) {
        if (station == null || cargoesToPick == null || cargoMode == null) {
            throw new IllegalArgumentException("Fields cannot be null.");
        }
        this.station = station;
        this.cargoesToPick = cargoesToPick;
        this.cargoMode = cargoMode;
    }

    public Station getStation() {
        return station;
    }

    public List<String> getCargoesToPick() {
        return cargoesToPick;
    }

    public CargoMode getCargoMode() {
        return cargoMode;
    }

    @Override
    public String toString() {
        return "PointOfRoute{" +
                "station=" + station.getName() +
                ", cargoesToPick=" + cargoesToPick +
                ", cargoMode=" + cargoMode +
                '}';
    }
}