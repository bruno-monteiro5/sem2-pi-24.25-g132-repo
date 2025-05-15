package pt.ipp.isep.dei.domain;

public enum StationType {
    DEPOT(50000, 3),
    STATION(100000, 4),
    TERMINAL(200000, 5);

    private final int cost;
    private final int radius;

    StationType(int cost, int radius) {
        this.cost = cost;
        this.radius = radius;
    }

    public int getCost() {
        return cost;
    }

    public int getRadius() {
        return radius;
    }
}
