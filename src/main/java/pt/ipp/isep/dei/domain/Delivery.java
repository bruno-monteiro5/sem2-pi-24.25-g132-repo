package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import pt.ipp.isep.dei.domain.CargoType;

public class Delivery implements Serializable {
    private CargoType type;
    private int count;

    public Delivery(CargoType type) {
        this.type = type;
        this.count = 1;
    }

    public CargoType getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
