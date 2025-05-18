package pt.ipp.isep.dei.domain;

public class Player {
    private String name;
    private double balance;

    public Player(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean canAfford(double cost){
        return balance >= cost;
    }

    public boolean deductFunds(double cost){
        if (canAfford(cost)){
            balance -= cost;
            return true;
        }else{
            return false;
        }
    }

}
