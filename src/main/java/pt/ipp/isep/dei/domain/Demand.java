package pt.ipp.isep.dei.domain;

import java.io.Serializable;

public class Demand implements Serializable {
    private CargoType type;
    private int value;

    public Demand(CargoType type, int value) {
        this.type = type;
        this.value = value;
    }

    public CargoType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
