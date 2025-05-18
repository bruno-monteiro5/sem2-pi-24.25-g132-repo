package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Scenario implements Serializable {

    private String name;
    private Map map;
    private ArrayList<Port> ports;
    private ArrayList<LocomotiveType> availableLocomotiveTypes;
    private double industryGenerationFactor;

    public Scenario(String name, Map map) {
        this.name = name;
        this.map = map;
        this.ports = new ArrayList<>();
        this.availableLocomotiveTypes = new ArrayList<>();
        this.industryGenerationFactor = 1.0;

    }


    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
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
}