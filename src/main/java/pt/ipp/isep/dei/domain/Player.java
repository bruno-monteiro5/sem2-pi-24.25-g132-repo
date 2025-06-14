package pt.ipp.isep.dei.domain;

/**
 * Represents a player in the simulation, responsible for managing balance and actions.
 * <p>
 * A player has a name and a monetary balance, which is used to buy elements like stations,
 * buildings, locomotives, etc.
 */
public class Player {

    /**
     * The name of the player.
     */
    private String name;

    /**
     * The current balance available to the player.
     */
    private double balance;

    /**
     * Creates a new player with a given name and initial balance.
     *
     * @param name           the name of the player
     * @param initialBalance the starting balance
     */
    public Player(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    /**
     * Gets the player's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's current balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Checks if the player can afford a certain cost.
     *
     * @param cost the amount to check
     * @return true if the balance is equal to or greater than the cost; false otherwise
     */
    public boolean canAfford(double cost){
        return balance >= cost;
    }

    /**
     * Deducts funds from the player's balance if possible.
     *
     * @param cost the amount to deduct
     * @return true if the deduction was successful; false if insufficient funds
     */
    public boolean deductFunds(double cost){
        if (canAfford(cost)){
            balance -= cost;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Adds funds to the player's balance.
     *
     * @param amount the amount to add
     */
    public void addToBudget(double amount) {
        this.balance += amount;
    }

    /**
     * Returns a string representation of the player.
     *
     * @return the player's name and balance
     */
    @Override
    public String toString() {
        return String.format("Player{name='%s', balance=%.2f}", name, balance);
    }

}
