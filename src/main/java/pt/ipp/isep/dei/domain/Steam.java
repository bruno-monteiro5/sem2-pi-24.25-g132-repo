package pt.ipp.isep.dei.domain;

/**
 * Represents the steam fuel type used by older locomotives.
 * <p>
 * Steam trains do not require electrified railway lines to operate.
 */
public class Steam extends FuelType {

    /**
     * Creates a new Steam fuel type.
     */
    public Steam() {
        super("Steam");
    }

    /**
     * Indicates whether this fuel type requires electrified railway lines.
     *
     * @return false, since steam locomotives do not require electrification
     */
    @Override
    public boolean requiresElectrifiedLine() {
        return false;
    }

    /**
     * Returns a string representation of the fuel type.
     *
     * @return "Steam"
     */
    @Override
    public String toString() {
        return "Steam";
    }


}
