package pt.ipp.isep.dei.domain;

public class Electric extends FuelType {

    public Electric() {
        super ("Electric");
    }


    @Override
    public boolean requiresElectrifiedLine() {
        return true;
    }

    @Override
    public String toString() {
        return "Electric";
    }
}
