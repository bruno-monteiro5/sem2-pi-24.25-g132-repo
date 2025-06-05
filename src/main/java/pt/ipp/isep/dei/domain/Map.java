package pt.ipp.isep.dei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a railway map with defined dimensions and support for managing various entities.
 *
 * <p>The {@code Map} class provides methods for adding stations, industries, and cities, as well as
 * validating and managing their positions. Each map is defined by a unique id, name, width, and height.
 * Entities can only be placed within the map's boundaries, and the class ensures that no two entities
 * occupy the same position.</p>
 *
 * <p>Core functionalities include:</p>
 * <ul>
 *     <li>Tracking and managing industries, cities, and stations.</li>
 *     <li>Validating positions on the map to ensure proper placement of entities.</li>
 *     <li>Supporting the calculation of distances between entities and finding the closest city.</li>
 * </ul>
 */

public class Map implements Serializable {

    private int id;
    private final String name;
    private final int width;
    private final int height;
    private String emailEditor;

    private final List<City> cities = new ArrayList<>();
    private final List<Station> stations = new ArrayList<>();
    private final List<Industry> industries = new ArrayList<>();
    private final List<Position> ocupiedPositions = new ArrayList<>();
    private final List<Scenario> scenarios = new ArrayList<>();
    private List<RailwayLine> railwayLines;


    private static final int ID_BY_OMISSION = 1;
    private static final String NAME_BY_OMISON = "Without name";
    private static final int WIDTH_BY_OMISSION = 1;
    private static final int HEIGHT_BY_OMISSION = 1;
    private static final String EMAIL_EDITOR_BY_OMISSION = null;


    /**
     * Constructs a {@code Map} instance with the specified name, dimensions, and creator.
     *
     * @param name The name of the map (must be a valid file name). Cannot be null or empty.
     * @param width The width (number of columns) of the map. Must be greater than zero.
     * @param height The height (number of rows) of the map. Must be greater than zero.
     * @param createdBy The email of the user who created the map.
     *
     * @throws IllegalArgumentException if {@code name} is invalid, or if {@code width/height} are less than or equal to zero.
     *
     * <p>Example:</p>
     * <pre>
     *     Map railwayMap = new Map("CityRailways", 10, 8, "editor@example.com");
     * </pre>
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

    public RailwayLine getLineBetweenStations(Station a, Station b) {
        for (RailwayLine line : railwayLines) {
            if (line.connects(a, b)) {
                return line;
            }
        }
        return null;
    }


    /**
     * Constructs a basic map instance with default properties.
     *
     * <p>The default map includes:</p>
     * <ul>
     *     <li>Name: {@code "Without name"}.</li>
     *     <li>Dimensions: 1x1 (width = 1; height = 1).</li>
     *     <li>No entities (stations, cities, etc.).</li>
     * </ul>
     *
     * <p>Primarily used for testing or as a placeholder instance.</p>
     */
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
     * Checks if a given position is within the map's boundaries.
     *
     * @param p The position to check. Cannot be null.
     * @return {@code true} if the position is within the map's boundaries, {@code false} otherwise.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position = new Position(5, 5);
     *     boolean isInside = map.isInsideBounds(position);
     * </pre>
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
     * Adds a station to the map, if the specified position is valid and not already occupied.
     *
     * @param s The station to add. Cannot be null.
     * @return {@code true} if the station was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if the station is null or placed outside the map's boundaries.
     *
     * <p>Example:</p>
     * <pre>
     *     Station newStation = new Station("Central", new Position(3, 4), StationType.METRO);
     *     boolean success = map.addStation(newStation);
     * </pre>
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

    /**
     * Adds an {@link Industry} to the map, provided its position is valid and unoccupied.
     *
     * <p>The industry must have a non-null position that is within the map's boundaries.
     * Additionally, the position cannot already be occupied by another entity.</p>
     *
     * @param industry The industry to be added. Cannot be null.
     *
     * @throws IllegalArgumentException If the industry or its position is null.
     * @throws IllegalArgumentException If the industry's position is outside the map's boundaries.
     * @throws IllegalArgumentException If the position is already occupied by another entity.
     *
     * <p>Example:</p>
     * <pre>
     *     Industry industry = new Industry("Tech Factory", new Position(4, 5), IndustryTypes.IndustryType.FACTORY);
     *     map.addIndustry(industry);
     * </pre>
     */
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

    /**
     * Adds a {@link City} to the map and marks its position as occupied.
     *
     * <p>This method does not perform validations on the position of the city
     * (e.g., whether it's already occupied or within the map's boundaries). Ensure
     * that the position is valid before calling this method.</p>
     *
     * @param city The city to be added. Cannot be null.
     *
     * <p>Example:</p>
     * <pre>
     *     City city = new City("Porto", new Position(3, 6));
     *     map.addCity(city);
     * </pre>
     */
    public void addCity(City city) {
        cities.add(city);
        ocupiedPositions.add(city.getPosition());
    }

    /**
     * Verifies whether a position is valid within the map's boundaries.
     *
     * <p>A position is considered valid if it is between 0 and the map's width (for X-axis)
     * and between 0 and the map's height (for Y-axis).</p>
     *
     * @param position The position to verify. Cannot be null.
     * @return {@code true} if the position is valid, {@code false} otherwise.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position = new Position(2, 3);
     *     boolean isValid = map.isPositionValid(position);
     *     System.out.println(isValid); // Output: true or false
     * </pre>
     */
    private boolean isPositionValid(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    /**
     * Verifies whether a given position is already occupied on the map.
     *
     * <p>A position is considered occupied if it matches any of the positions stored in
     * the list of occupied positions ({@code ocupiedPositions}).</p>
     *
     * <p>If the position is outside the map's boundaries, it will be considered not occupied.</p>
     *
     * @param position The position to check. Cannot be null.
     * @return {@code true} if the position is occupied, {@code false} otherwise.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position = new Position(6, 7);
     *     boolean isOccupied = map.isPositionOccupied(position);
     *     System.out.println(isOccupied); // Output: true or false
     * </pre>
     */
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


    /**
     * Retrieves a list of all industries placed on the map.
     *
     * <p>The returned list is a copy, meaning modifications to the list
     * will not affect the original industries stored in the map.</p>
     *
     * @return A {@link List} of {@link Industry} objects currently on the map.
     */
    public List<Industry> getIndustries() {
        return new ArrayList<>(industries);
    }

    /**
     * Retrieves a list of all cities placed on the map.
     *
     * <p>The returned list is a copy, meaning modifications to the list
     * will not affect the original cities stored in the map.</p>
     *
     * @return A {@link List} of {@link City} objects currently on the map.
     */
    public List<City> getCities() {
        return new ArrayList<>(cities);
    }

    /**
     * Retrieves a list of all occupied positions on the map.
     *
     * <p>The returned list is a copy, meaning modifications to the list
     * will not affect the original occupied positions stored in the map.</p>
     *
     * @return A {@link List} of {@link Position} objects representing all
     * currently occupied positions on the map.
     */
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



    /**
     * Finds the closest city to the specified position.
     *
     * <p>The method calculates the distance to each city on the map and
     * determines the city with the shortest distance to the provided position.</p>
     *
     * @param position The position to search from. Cannot be null.
     * @return The {@link City} closest to the given position, or {@code null} if no cities are present.
     *
     * <p>Example:</p>
     * <pre>
     *     Position userPosition = new Position(10, 15);
     *     City closestCity = map.findClosestCity(userPosition);
     * </pre>
     */
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

    /**
     * Suggests a name for a station at a given location and of a specific type.
     *
     * <p>If there is a city near the provided location, the suggested name will be a
     * combination of the city's name and the station type. Otherwise, the name
     * will default to the station type's name.</p>
     *
     * @param location The geographical position where the station is located. Cannot be null.
     * @param type The type of the station, represented as a {@link StationType}. Cannot be null.
     * @return A {@link String} suggesting the name for the station.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position = new Position(10, 15);
     *     StationType stationType = StationType.METRO;
     *     String suggestedName = map.getSuggestedName(position, stationType);
     *     System.out.println(suggestedName); // Output: "{CityName} Metro" or "Metro"
     * </pre>
     */
    public String getSuggestedName(Position location, StationType type) {
        City city = findClosestCity(location);
        if (city == null) {
            return type.getName();
        }
        return city + " " + type.getName();
    }

    /**
     * Calculates the Euclidean distance between two positions on the map.
     *
     * <p>The Euclidean distance is determined using the formula:</p>
     * <pre>
     *     distance = sqrt((x2 - x1)² + (y2 - y1)²)
     * </pre>
     *
     * @param p1 The first position. Cannot be null.
     * @param p2 The second position. Cannot be null.
     * @return A {@code double} representing the Euclidean distance between the two positions.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position1 = new Position(2, 3);
     *     Position position2 = new Position(5, 7);
     *     double distance = map.positionDistance(position1, position2);
     *     System.out.println(distance); // Output: 5.0
     * </pre>
     */
    private double positionDistance(Position p1, Position p2) {
        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Creates a new {@link Position} object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return A new {@link Position} instance representing the specified coordinates.
     *
     * <p>Example:</p>
     * <pre>
     *     Position newPosition = map.createPosition(5, 10);
     *     System.out.println(newPosition); // Output: Position{x=5, y=10}
     * </pre>
     */
    public Position createPosition(int x, int y) {
        return new Position(x, y);
    }

    /**
     * Validates whether a given position can be used on the map.
     *
     * <p>The position is considered valid if:</p>
     * <ul>
     *     <li>The position itself is valid (determined by {@code isPositionValid}).</li>
     *     <li>The position is within the map's boundaries (checked by {@code isInsideBounds}).</li>
     *     <li>The position is not already occupied by another entity (checked by {@code isPositionOccupied}).</li>
     * </ul>
     *
     * @param position The position to validate. Cannot be null.
     * @return {@code true} if the position is valid, {@code false} otherwise.
     *
     * <p>Example:</p>
     * <pre>
     *     Position position = new Position(5, 7);
     *     boolean isValid = map.validatePosition(position);
     *     System.out.println(isValid); // Output: true or false
     * </pre>
     */
    public boolean validatePosition(Position position) {
        if (isPositionValid(position) && isInsideBounds(position) && !isPositionOccupied(position)) {
            return true;
        }else
            return false;
    }

    public List<Scenario> getScenarios() {
        return new ArrayList<>(scenarios);
    }


    /**
     * Provides a human-readable representation of the map.
     *
     * <p>The string representation includes the map's name, dimensions,
     * and a summary of all entities (stations, cities, and industries).</p>
     *
     * @return A {@link String} representing the map's current state.
     */
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
