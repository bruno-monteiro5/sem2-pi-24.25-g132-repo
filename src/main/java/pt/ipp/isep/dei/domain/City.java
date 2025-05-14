package pt.ipp.isep.dei.domain;

public class City {

    private String name;
    private int x;
    private int y;
    private int numberOfHouseBlocks;

    public City(String name, int x, int y, int numberOfHouseBlocks) {
        if (x <= 0 || y <= 0 || numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("Os valores de x, y e numberOfHouseBlocks devem ser positivos.");
        }
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

    // Setter com validação
    public void setX(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("O valor de x deve ser positivo.");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y <= 0) {
            throw new IllegalArgumentException("O valor de y deve ser positivo.");
        }
        this.y = y;
    }

    public int getNumberOfHouseBlocks() {
        return numberOfHouseBlocks;
    }

    // Setter com validação
    public void setNumberOfHouseBlocks(int numberOfHouseBlocks) {
        if (numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("O número de blocos de casas deve ser positivo.");
        }
        this.numberOfHouseBlocks = numberOfHouseBlocks;
    }
}
