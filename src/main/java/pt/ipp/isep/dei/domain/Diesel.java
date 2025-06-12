package pt.ipp.isep.dei.domain;

public class Diesel extends FuelType {

    public Diesel() {
        super("Diesel");
    }

    @Override
    public boolean requiresElectrifiedLine() {
        return false;
    }

    @Override
    public String toString() {
        return "Diesel";
    }


}
