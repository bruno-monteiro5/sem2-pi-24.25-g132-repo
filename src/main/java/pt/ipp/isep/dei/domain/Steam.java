package pt.ipp.isep.dei.domain;

public class Steam extends FuelType {

    public Steam() {
        super("Steam");
    }

    @Override
    public boolean requiresElectrifiedLine() {
        return false;
    }

    @Override
    public String toString() {
        return "Steam";
    }


}
