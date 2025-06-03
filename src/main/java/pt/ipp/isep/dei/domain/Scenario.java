package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Scenario implements Serializable {

    private String name;
    private Map map;
    private ArrayList<Port> ports;
    private ArrayList<LocomotiveType> availableLocomotiveTypes;
    private ArrayList<Train> trains = new ArrayList<>();
    private double industryGenerationFactor;
    private PeriodTime periodTime;

    public Scenario(String name, Map map, PeriodTime periodTime) {
        this.name = name;
        this.map = map;
        this.ports = new ArrayList<>();
        this.availableLocomotiveTypes = new ArrayList<>();
        this.industryGenerationFactor = 1.0;
        this.periodTime = periodTime;

    }


    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
    }

    public PeriodTime getPeriodTime() {
        return periodTime;
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }

    public void addPort(Port port) {
        ports.add(port);
    }

    public ArrayList<LocomotiveType> getAvailableLocomotiveTypes() {
        return availableLocomotiveTypes;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void addLocomotiveType(LocomotiveType type) {
        if (!availableLocomotiveTypes.contains(type)) {
            availableLocomotiveTypes.add(type);
        }
    }

    public double getIndustryGenerationFactor() {
        return industryGenerationFactor;
    }

    public void setIndustryGenerationFactor(double factor) {
        this.industryGenerationFactor = factor;
    }


    public boolean isValid() {
        return periodTime != null && periodTime.isValid() && map != null;
    }
}