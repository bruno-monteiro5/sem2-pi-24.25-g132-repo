package MDISC.USs;

import java.util.List;

class MaintenanceRouteSolver {

    private RailwayNetwork network;

    public MaintenanceRouteSolver(RailwayNetwork network) {
        this.network = network;
    }

    public boolean isMaintenanceRoutePossible(boolean onlyElectrified) {
        return network.isEulerianTrailPossible(onlyElectrified);
    }

    public List<Station> getStartStations(boolean onlyElectrified) {
        return network.getPossibleStartStations(onlyElectrified);
    }

}
