package pt.ipp.isep.dei.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents the railway map with defined width and height.
 * Supports placing and validating stations within its boundaries.
 */
public class Map {
    private final String name;
    private final int width;
    private final int height;
    private final List<Station> stations = new ArrayList<>();
    private final List<Industry> industries = new ArrayList<>();


    /**
     * Constructs a Map with a name, width, and height.
     * @param name map name (must be valid file name)
     * @param width number of columns
     * @param height number of rows
     */
    public Map(String name, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Map must have positive width and height");
        }
        if (!isValidFileName(name)) {
            throw new IllegalArgumentException("Map must have a valid name");
        }
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private boolean isValidFileName(String name) {
        return name.matches("[a-zA-Z0-9_-]+") && !name.isEmpty();
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Checks if a given position is within the map boundaries.
     * @param p the position to check
     * @return true if inside bounds, false otherwise
     */
    public boolean isInsideBounds(Position p) {
        return p.getX() >= 0 && p.getX() < width && p.getY() >= 0 && p.getY() < height;
    }

    /**
     * Checks if a station already exists at the given position.
     * @param p the position to check
     * @return true if there is a station, false otherwise
     */
    public boolean hasStationAt(Position p) {
        for (Station s : stations) {
            if (s.getLocation().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Places a station on the map if the position is valid and unoccupied.
     * @param s the station to place
     */
    public void placeStation(Station s) {
        Position p = s.getLocation();
        if (!isInsideBounds(p)) {
            throw new IllegalArgumentException("Station position is outside the map.");
        }
        if (hasStationAt(p)) {
            throw new IllegalArgumentException("There is already a station at this position.");
        }
        stations.add(s);
    }

    public void addIndustry(Industry industry) {
        if (industry == null || industry.getPosition() == null) {
            throw new IllegalArgumentException("A indústria ou sua posição não pode ser nula.");
        }
        if (!isPositionValid(industry.getPosition())) {
            throw new IllegalArgumentException("A posição da indústria está fora dos limites do mapa.");
        }
        if (isPositionOccupied(industry.getPosition())) {
            throw new IllegalArgumentException("Já existe uma indústria nesta posição.");
        }
        industries.add(industry);
    }

    private boolean isPositionValid(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    private boolean isPositionOccupied(Position position) {
        for (Industry industry : industries) {
            if (industry.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }


    public List<Industry> getIndustries() {
        return new ArrayList<>(industries);
    }



    /**
     * Gets a copy of the list of all placed stations.
     * @return list of stations
     */
    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }
}
