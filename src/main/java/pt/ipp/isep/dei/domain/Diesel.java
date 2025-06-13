package pt.ipp.isep.dei.domain;

/**
 * Represents the diesel fuel type used by certain locomotives.
 * <p>
 * Diesel trains do not require electrified railway lines.
 */
public class Diesel extends FuelType {

    /**
     * Creates a new Diesel fuel type.
     */
    public Diesel() {
        super("Diesel");
    }

    /**
     * Indicates whether this fuel type requires electrified railway lines.
     *
     * @return false, since diesel locomotives do not need electrification
     */
    @Override
    public boolean requiresElectrifiedLine() {
        return false;
    }

    /**
     * Returns a string representation of the fuel type.
     *
     * @return "Diesel"
     */
    @Override
    public String toString() {
        return "Diesel";
    }


}
