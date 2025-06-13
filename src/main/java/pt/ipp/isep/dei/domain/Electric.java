package pt.ipp.isep.dei.domain;

/**
 * Represents the electric fuel type used by electric locomotives.
 * <p>
 * Electric trains require electrified railway lines to operate.
 */
public class Electric extends FuelType {

    /**
     * Creates a new Electric fuel type.
     */
    public Electric() {
        super ("Electric");
    }


    /**
     * Indicates whether this fuel type requires electrified railway lines.
     *
     * @return true, since electric locomotives require electrification
     */
    @Override
    public boolean requiresElectrifiedLine() {
        return true;
    }

    /**
     * Returns a string representation of the fuel type.
     *
     * @return "Electric"
     */
    @Override
    public String toString() {
        return "Electric";
    }
}
