package pt.ipp.isep.dei.domain;

/**
 * Represents a specific cargo that can be supplied or demanded by a station.
 * <p>
 * A Cargo object includes its type, quantity, whether it is supply or demand,
 * and the base value used for calculating delivery rewards.
 */
public class Cargo {

    /**
     * The type of cargo (e.g., PASSENGERS, COAL).
     */
    private CargoType type;

    /**
     * The quantity of cargo available or required.
     */
    private int quantity;

    /**
     * True if the cargo represents supply; false if it is demand.
     */
    private boolean isSupply;

    /**
     * The base value of the cargo (used in revenue calculation).
     */
    private double baseValue;

    /**
     * Constructs a new Cargo object with the specified type, quantity, and role (supply/demand).
     *
     * @param type      the type of cargo
     * @param quantity  the quantity of cargo
     * @param isSupply  true if supply, false if demand
     * @param baseValue the base value for this type of cargo
     */
    public Cargo(CargoType type, int quantity, boolean isSupply, double baseValue) {
        this.type = type;
        this.quantity = quantity;
        this.isSupply = isSupply;
        this.baseValue = baseValue;
    }

    public Cargo(Cargo original) {
        this.type = original.type;
        this.quantity = original.quantity;
        this.isSupply = original.isSupply;
        this.baseValue = original.baseValue;
    }


    /**
     * Gets the type of cargo.
     *
     * @return the cargo type
     */
    public CargoType getType() {
        return type;
    }

    /**
     * Gets the quantity of cargo.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Checks whether the cargo is a supply.
     *
     * @return true if supply; false if demand
     */
    public boolean isSupply() {
        return isSupply;
    }

    /**
     * Gets the base value of the cargo.
     *
     * @return the base value
     */
    public double getBaseValue() {
        return this.baseValue;
    }

    /**
     * Sets the type of the cargo.
     *
     * @param type the new cargo type
     */
    public void setType(CargoType type) {
        this.type = type;
    }

    /**
     * Sets the quantity of the cargo.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets whether the cargo is a supply.
     *
     * @param isSupply true if supply; false if demand
     */
    public void setSupply(boolean isSupply) {
        this.isSupply = isSupply;
    }

    /**
     * Returns a formatted string representation of the cargo.
     *
     * @return string with type, quantity, supply flag, and base value
     */
    @Override
    public String toString() {
        return String.format(
                "Cargo{type=%s, quantity=%d, isSupply=%b, baseValue=%.2f}",
                type.name(), quantity, isSupply, baseValue
        );
    }
}