package pt.ipp.isep.dei.domain;

import java.io.Serializable;

public class Locomotive implements Serializable {

    private LocomotiveType type;
    private int startYear;

    public Locomotive( LocomotiveType type) {
        this.type = type;
    }


    public LocomotiveType getType() {
        return type;
    }

    public int getStartYear() {
        return startYear;
    }


    @Override
    public String toString() {
        return "Locomotive {" +
                ", Name='" + type.getName() + '\'' +
                ", Fuel='" + type.getFuelType() + '\'' +
                ", Speed=" + type.getTopSpeed() +
                '}';
    }
}
