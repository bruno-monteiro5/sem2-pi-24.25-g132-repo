package pt.ipp.isep.dei.domain;

public enum StationType {
    DEPOT(50000, 3, "Depot", false),
    STATION(100000, 4, "Station", true),
    TERMINAL(200000, 5, "Terminal", false),;

    private final int cost;
    private final int radius;
    private final String Name;
    private final boolean needsCenter;

    StationType(int cost, int radius, String Name, boolean needsCenter) {
        this.cost = cost;
        this.radius = radius;
        this.Name = Name;
        this.needsCenter = needsCenter;
    }

    public int getCost() {
        return cost;
    }

    public int getRadius() {
        return radius;
    }

    public String getName() {
        return Name;
    }

    public boolean needsCenter() {
        return needsCenter;
    }
}
