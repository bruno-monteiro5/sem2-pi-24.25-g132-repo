package pt.ipp.isep.dei.repository;

import java.util.ArrayList;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.domain.Locomotive;

public class SimulatorRepository {

    private ArrayList<Train> trains;
    private ArrayList<Station> stations;
    private ArrayList<Locomotive> allLocomotives;
    private Scenario scenario;
    private int startYear;

    public SimulatorRepository(ArrayList<Train> trains,
                               ArrayList<Station> stations,
                               ArrayList<Locomotive> allLocomotives,
                               Scenario scenario,
                               int startYear) {
        this.trains = trains;
        this.stations = stations;
        this.allLocomotives = allLocomotives;
        this.scenario = scenario;
        this.startYear = startYear;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Locomotive> getAllLocomotives() {
        return allLocomotives;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public int getStartYear() {
        return startYear;
    }
}
