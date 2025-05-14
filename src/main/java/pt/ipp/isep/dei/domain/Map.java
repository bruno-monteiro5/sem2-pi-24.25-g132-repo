package pt.ipp.isep.dei.domain;

public class Map {
    private String name;
    private int width;
    private int height;


    public Map(String name, int width, int height) {
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Map must have positive width and height");
        }
        if (!isValidFileName(name)){
            throw new IllegalArgumentException("Map must have a valid map name");
        }
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private boolean isValidFileName(String name){
        return name.matches("[a-zA-Z0-9_-]+") && !name.isEmpty();
    }

    // getters
    public String getName() {
        return name;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
