package pt.ipp.isep.dei.domain;


import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the railway map with defined width and height.
 * Supports placing and validating stations within its boundaries.
 */
public class Map {

    private int id;
    private final String name;
    private final int width;
    private final int height;
    private String emailEditor;

    private final List<City> cities = new ArrayList<>();
    private final List<Station> stations = new ArrayList<>();
    private final List<Industry> industries = new ArrayList<>();
    private final List<Position> ocupiedPositions = new ArrayList<>();

    private static final int ID_BY_OMISSION = 1;
    private static final String NAME_BY_OMISON = "Without name";
    private static final int WIDTH_BY_OMISSION = 1;
    private static final int HEIGHT_BY_OMISSION = 1;
    private static final String EMAIL_EDITOR_BY_OMISSION = null;


    /**
     * Constructs a Map with a name, width, and height.
     * @param name map name (must be valid file name)
     * @param width number of columns
     * @param height number of rows
     */
    public Map(String name, int width, int height, String createdBy) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Map must have positive width and height");
        }
        if (!isValidFileName(name)) {
            throw new IllegalArgumentException("Map must have a valid name");
        }
        this.name = name;
        this.width = width;
        this.height = height;
        this.emailEditor = createdBy;
    }

    public Map() {
        this(NAME_BY_OMISON, WIDTH_BY_OMISSION, HEIGHT_BY_OMISSION, EMAIL_EDITOR_BY_OMISSION);
        this.id = ID_BY_OMISSION;
    }

    public int getId() {
        return id;
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

    public String getEmailEditor() {
        return emailEditor;
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



    public boolean buildStation(StationType type, Position position, String name) {
        if (!isInsideBounds(position) || hasStationAt(position)) {
            return false;
        }

        Station s = new Station(name, position, type);
        ocupiedPositions.add(position);
        return addStation(s);
    }

    //ADDERS

    /**
     * Places a station on the map if the position is valid and unoccupied.
     * @param s the station to place
     */
    public boolean addStation(Station s) {
        if (s == null){
            return false;
        }

        for (Station station : stations) {
            if (station.getName().equals(s.getName())) {
                return false;
            }
        }

        return stations.add(s);
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
        ocupiedPositions.add(industry.getPosition());
    }

    public void addCity(City city) {
        cities.add(city);
        ocupiedPositions.add(city.getPosition());
    }


    private boolean isPositionValid(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    private boolean isPositionOccupied(Position position) {
        if (position.getX() > width || position.getY() > height) {
            return false;
        }
        for (Position occupiedPosition : ocupiedPositions) {
            if (occupiedPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }



    public List<Industry> getIndustries() {
        return new ArrayList<>(industries);
    }

    public List<City> getCities() {
        return new ArrayList<>(cities);
    }

    public List<Position> getOcupiedPositions() {
        return new ArrayList<>(ocupiedPositions);
    }

    /**
     * Gets a copy of the list of all placed stations.
     * @return list of stations
     */
    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }



    public City findClosestCity(Position position) {
        City closest = null;
        double minDistance = Double.MAX_VALUE;

        for (City city : cities) {
            double distance = positionDistance(position, city.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                closest = city;
            }
        }

        return closest;
    }

    public String getSuggestedName(Position location, StationType type) {
        String city = findClosestCity(location).getName();
        return city + " " + type.getName();
    }

    private double positionDistance(Position p1, Position p2) {
        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Position createPosition(int x, int y) {
        return new Position(x, y);
    }

    public boolean validatePosition(Position position) {
        if (isPositionValid(position) && isInsideBounds(position) && !isPositionOccupied(position)) {
            return true;
        }else
            return false;
    }


    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                "name='" + name + '\'' +
                "width=" + width +
                "height=" + height +
                '}';
    }
}
