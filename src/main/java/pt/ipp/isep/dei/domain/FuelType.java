package pt.ipp.isep.dei.domain;

import java.util.List;

public abstract class FuelType implements Comparable<FuelType> {

    private final String name;

    public FuelType(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public abstract boolean requiresElectrifiedLine();

    @Override
    public String toString() {
        return name;
    }

    public static List<FuelType> availableFuelTypes() {
        return List.of(new Steam(), new Diesel(), new Electric());
    }

    @Override
    public int compareTo(FuelType other) {
        return this.name.compareTo(other.name);
    }
}
