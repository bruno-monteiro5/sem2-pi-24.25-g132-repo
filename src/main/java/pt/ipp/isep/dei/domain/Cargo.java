package pt.ipp.isep.dei.domain;

/**
 * Represents a specific cargo that can be supplied or demanded by a station.
 * It encapsulates the type of cargo, the quantity, and other relevant details.
 */
public class Cargo {
    private CargoType type;    // The type of the cargo, now using the enum.
    private int quantity;      // Quantity of cargo available (or required).
    private boolean isSupply;// True if it's supply cargo, false if it's demand cargo.
    private double baseValue;

    /**
     * Constructor for creating a Cargo object.
     *
     * @param type      The type of the cargo (e.g., passengers, coal).
     * @param quantity  The quantity of the cargo.
     * @param isSupply  True if the cargo is in supply, false if it's in demand.
     */
    public Cargo(CargoType type, int quantity, boolean isSupply, double baseValue) {
        this.type = type;
        this.quantity = quantity;
        this.isSupply = isSupply;
        this.baseValue = baseValue;
    }

    // Getters
    public CargoType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSupply() {
        return isSupply;
    }

    public double getBaseValue() {
        return this.baseValue;
    }

    // Setters
    public void setType(CargoType type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSupply(boolean isSupply) {
        this.isSupply = isSupply;
    }

    /**
     * String representation for display or debugging purposes.
     */
    @Override
    public String toString() {
        return "Cargo{" +
                "type=" + type +
                ", quantity=" + quantity +
                ", isSupply=" + isSupply +
                '}';
    }
}