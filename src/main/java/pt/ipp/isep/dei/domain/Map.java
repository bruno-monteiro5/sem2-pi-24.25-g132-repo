package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    private String name;
    private int width;
    private int height;
    private ArrayList<Station> stations;


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
        this.stations = new ArrayList<>();
    }

    private boolean isValidFileName(String name){
        return name.matches("[a-zA-Z0-9_-]+") && !name.isEmpty();
    }

    public void addStation(Station station) {
        if (station.getX() >= 0 && station.getY() < width && station.getY() >= 0 && station.getY() < height) {
            stations.add(station);
        } else {
            System.out.println("Estação fora dos limites do mapa!");
        }
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
    public ArrayList<Station> getStations() { return stations; }
}