package pt.ipp.isep.dei.domain;

public class City {

    private String name;
    private int x;
    private int y;
    private int numberOfHouseBlocks;

    public City(String name, int x, int y, int numberOfHouseBlocks) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumberOfHouseBlocks() {
        return numberOfHouseBlocks;
    }

    public void setNumberOfHouseBlocks(int numberOfHouseBlocks) {
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }
}
