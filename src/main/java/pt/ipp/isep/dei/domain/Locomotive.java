package pt.ipp.isep.dei.domain;

import java.io.Serializable;

public class Locomotive implements Serializable {

    private int id;
    private LocomotiveType type;

    public Locomotive(int id, LocomotiveType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public LocomotiveType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Locomotive {" +
                "ID=" + id +
                ", Name='" + type.getName() + '\'' +
                ", Fuel='" + type.getFuelType() + '\'' +
                ", Speed=" + type.getTopSpeed() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locomotive that = (Locomotive) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
