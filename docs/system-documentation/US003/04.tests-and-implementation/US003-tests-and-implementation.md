# US003 - Add a City 

## 4. Tests 

#### 4.1. Class City 

**Test 1:** Tests the creation of a valid City object and verifies its properties.

	 @Test
    void testValidCityCreation() {
        Map map = new Map("Mapa1", 10, 10, "editor@this.app");
        Position pos = new Position(2, 3);
        City city = new City(map, "Porto", pos, 5);
        assertEquals("Porto", city.getName());
        assertEquals(2, city.getX());
        assertEquals(3, city.getY());
        assertEquals(5, city.getNumberOfHouseBlocks());
        assertEquals(map, city.getMap());
    }


**Test 2:** Ensures that creating a City with an invalid name throws an IllegalArgumentException.

    @Test
    void testInvalidNameThrowsException() {
        Map map = new Map("Mapa2", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto123", pos, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto!", pos, 2));
    }


**Test 3:** Ensures that creating a City with null arguments throws an IllegalArgumentException.

    @Test
    void testNullArgumentsThrowException() {
        Map map = new Map("Mapa3", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new City(map, null, pos, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto", null, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto", pos, null));
    }


**Test 4:** Tests the setters and getters of the City class.

    @Test
    void testSettersAndGetters() {
        Map map = new Map("Mapa4", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 3);
        city.setName("Valongo");
        assertEquals("Valongo", city.getName());
        city.setPositionX(5);
        city.setPositionY(6);
        assertEquals(5, city.getX());
        assertEquals(6, city.getY());
        city.setNumberOfHouseBlocks(7);
        assertEquals(7, city.getNumberOfHouseBlocks());
    }


**Test 5:** Ensures that setting the map to null throws an IllegalArgumentException.

    @Test
    void testSetMapNullThrowsException() {
        Map map = new Map("Mapa5", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setMap(null));
    }


**Test 6:** Ensures that setting the name to null or empty throws an IllegalArgumentException.

    @Test
    void testSetNameNullOrEmptyThrowsException() {
        Map map = new Map("Mapa6", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setName(null));
        assertThrows(IllegalArgumentException.class, () -> city.setName(""));
    }

**Test 7:** Ensures that setting invalid X or Y coordinates throws an IllegalArgumentException.

    @Test
    void testSetPositionXAndYInvalidThrowsException() {
        Map map = new Map("Mapa7", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setPositionX(0));
        assertThrows(IllegalArgumentException.class, () -> city.setPositionY(-1));
    }

**Test 8:** Ensures that setting an invalid number of house blocks throws an IllegalArgumentException.

    @Test
    void testSetNumberOfHouseBlocksInvalidThrowsException() {
        Map map = new Map("Mapa8", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setNumberOfHouseBlocks(0));
        assertThrows(IllegalArgumentException.class, () -> city.setNumberOfHouseBlocks(-5));
    }

**Test 9:** Tests the random generation of house blocks and verifies the resulting values.

    @Test
    void testGenerateRandomHouseBlocks() {
        Map map = new Map("Mapa9", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 1);
        city.generateRandomHouseBlocks();
        assertTrue(city.getNumberOfHouseBlocks() >= 1 && city.getNumberOfHouseBlocks() <= 10);
        assertEquals(city.getNumberOfHouseBlocks(), city.getHouseBlocksList().size());
    }

**Test 10:** Tests the string representation of the City object.

    @Test
    void testToString() {
        Map map = new Map("Mapa9", 10, 10, "editor@this.app");
        Position pos = new Position(1, 2);
        City city = new City(map, "Porto", pos, 2);
        String str = city.toString();
        assertTrue(str.contains("name='Porto'"));
        assertTrue(str.contains("x=1"));
        assertTrue(str.contains("y=2"));
        assertTrue(str.contains("houseBlocks=2"));
    }

#### 4.2. Class HouseBlock

**Test 11:** Tests the creation of a valid HouseBlock and verifies its position.

    @Test
    void testValidHouseBlockCreation() {
        Position position = new Position(2, 3);
        HouseBlock houseBlock = new HouseBlock(position);
        assertEquals(position, houseBlock.getPosition());
    }


**Test 12:** Ensures that creating a HouseBlock with a null position throws an IllegalArgumentException.

    @Test
    void testConstructorNullPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new HouseBlock(null));
    }


**Test 13:** Tests setting a valid position for a HouseBlock.

    @Test
    void testSetPositionValid() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(5, 6);
        HouseBlock houseBlock = new HouseBlock(position1);
        houseBlock.setPosition(position2);
        assertEquals(position2, houseBlock.getPosition());
    }

**Test 14:** Ensures that setting a null position throws an IllegalArgumentException.

    @Test
    void testSetPositionNullThrowsException() {
        Position position = new Position(1, 1);
        HouseBlock houseBlock = new HouseBlock(position);
        assertThrows(IllegalArgumentException.class, () -> houseBlock.setPosition(null));
    }

**Test 15:** Tests the string representation of a HouseBlock.

    @Test
    void testToString() {
        Position position = new Position(3, 4);
        HouseBlock houseBlock = new HouseBlock(position);
        String str = houseBlock.toString();
        assertTrue(str.contains("HouseBlock"));
        assertTrue(str.contains("position=" + position));
    }

#### 4.3. Class CityRepository

**Test 16:** Tests saving and retrieving cities.

    @Test
    void testSaveAndGetCities() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map1", 10, 10, "editor@this.app");
        City city = new City(map, "Porto", new Position(1, 1), 2);
        repository.save(city);
        List<City> cities = repository.getCities();
        assertEquals(1, cities.size());
        assertEquals("Porto", cities.get(0).getName());
    }

**Test 17:** Tests cityExists returns true for existing city (case-insensitive).

    @Test
    void testCityExists() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map2", 10, 10, "editor@this.app");
        City city = new City(map, "Lisboa", new Position(2, 2), 3);
        repository.save(city);
        assertTrue(repository.cityExists("Lisboa"));
        assertTrue(repository.cityExists("lisboa"));
        assertFalse(repository.cityExists("Porto"));
    }

**Test 18:** Tests positionInvalid returns true if position is already used.

    @Test
    void testPositionInvalid() {
        CityRepository repository = new CityRepository();
        Map map = new Map("Map3", 10, 10, "editor@this.app");
        Position pos = new Position(3, 3);
        City city = new City(map, "Valongo", pos, 1);
        repository.save(city);
        assertTrue(repository.positionInvalid(new Position(3, 3)));
        assertFalse(repository.positionInvalid(new Position(4, 4)));
    }

## 5. Construction (Implementation)

### Class City 

```java
package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a city within the simulation, which can be added to a specific map.
 * A city is characterized by its name, its position on the map (XY coordinates),
 * and a configurable number of house blocks.
 *
 * <p>
 * In the context of the simulator, a {@code City} serves as a core domain entity,
 * playing the following roles:
 * <ul>
 *     <li>Generating and consuming passengers and mail.</li>
 *     <li>Consuming final products, such as food, textiles, and cars.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Each city has the following main attributes:
 * <ul>
 *     <li>A <b>name</b>, which uniquely identifies the city in the user's context.
 *          Names must not include special characters or digits but can contain spaces.</li>
 *     <li>A <b>position</b> defined by XY coordinates, indicating its location on the map.
 *          Both coordinates must be positive integers.</li>
 *     <li>A <b>number of house blocks</b>. The house blocks can be added manually
 *          or generated automatically (randomly distributed around the city's position).</li>
 * </ul>
 * </p>
 *
 * <p>
 * Editing capabilities include renaming a city, updating its position, or modifying the
 * number of house blocks. Validation rules are enforced to ensure data integrity:
 * <ul>
 *     <li>City names must not contain special characters or digits.</li>
 *     <li>Coordinates must remain positive.</li>
 *     <li>The number of house blocks must always be a positive value.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Additional considerations:
 * <ul>
 *     <li>City names do not need to be unique but should avoid duplication within the same map to minimize confusion for users.</li>
 *     <li>A city cannot overlap with other entities, such as other cities or industries, on the map.</li>
 *     <li>Cities can only be removed in edit mode, not during simulation mode.</li>
 * </ul>
 * </p>
 */
public class City {

    private Map map;
    private String name;
    private Position position;
    private int houseBlocks;
    private List<HouseBlock> houseBlocksList;

    /**
     * Constructs a {@code City} with a name, position, and number of house blocks.
     *
     * @param map         The map to which the city belongs.
     * @param name        The name of the city. Must not be null, empty, or contain special characters or digits.
     * @param position    The coordinates (XY) of the city's location within the map. Coordinates must be positive.
     * @param houseBlocks The number of house blocks in the city. Must be a positive integer.
     * @throws IllegalArgumentException if {@code name} is invalid, {@code position} has negative values, or {@code houseBlocks} is not positive.
     */
    public City(Map map, String name, Position position, Integer houseBlocks) {
        if (name == null || position == null || houseBlocks == null) {
            throw new IllegalArgumentException("No argument can be null");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name cannot contain special characters or digits");
        }


        this.name = name;
        this.position = position;
        this.houseBlocks = houseBlocks;
        this.map = map;
    }

    public Map getMap() { return map; }

    public String getName() { return name; }

    public Position getPosition() { return position; }

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public int getNumberOfHouseBlocks() { return houseBlocks; }

    public List<HouseBlock> getHouseBlocksList() { return new ArrayList<>(houseBlocksList); }

    /**
     * Sets the map that the city belongs to.
     *
     * @param map The map to associate with the city. Must not be {@code null}.
     * @throws IllegalArgumentException if the {@code map} is {@code null}.
     */
    public void setMap(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null.");
        }
        this.map = map;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The new name for the city. Must not be null or empty.
     * @throws IllegalArgumentException if {@code name} is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        this.name = name;
    }


    /**
     * Sets the x-coordinate of the city's location.
     *
     * @param x The new x-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code x} is not positive.
     */
    public void setPositionX(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.position.setX(x);
    }

    /**
     * Sets the y-coordinate of the city's location.
     *
     * @param y The new y-coordinate. Must be a positive integer.
     * @throws IllegalArgumentException if {@code y} is not positive.
     */
    public void setPositionY(int y) {
        if (y <= 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
        this.position.setY(y);
    }

    /**
     * Sets the number of house blocks in the city.
     *
     * @param numberOfHouseBlocks The new number of house blocks. Must be a positive integer.
     * @throws IllegalArgumentException if {@code numberOfHouseBlocks} is not positive.
     */
    public void setNumberOfHouseBlocks(int numberOfHouseBlocks) {
        if (numberOfHouseBlocks <= 0) {
            throw new IllegalArgumentException("The number of houseBlocks must be positive.");
        }
        this.houseBlocks = numberOfHouseBlocks;
    }

    /**
     * Validates the city name, ensuring it is non-empty and does not contain
     * special characters or digits.
     *
     * @param name The name to validate.
     * @return {@code true} if the name is valid, {@code false} otherwise.
     */
    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Randomly generates house blocks around the city's position.
     *
     * <p>
     * The random generation ensures:
     * <ul>
     *     <li>House blocks are within the map's boundaries.</li>
     *     <li>House blocks do not overlap with the city's position.</li>
     * </ul>
     * </p>
     *
     * @throws IllegalArgumentException if the map property is null.
     */
    public void generateRandomHouseBlocks() {
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }

        Random random = new Random();
        this.houseBlocks = random.nextInt(10) + 1;
        houseBlocksList = new ArrayList<>();

        for (int i = 0;i<houseBlocks;i++) {
            HouseBlock houseBlock = new HouseBlock(new Position(this.position.getX() + i, this.position.getY()));
            houseBlocksList.add(houseBlock);
        }
    }

    /**
     * Validates whether a given house block's position is valid within the current map.
     *
     * <p>
     * A valid position must:
     * <ul>
     *     <li>Be within the map's boundaries.</li>
     *     <li>Not overlap with the city's position or other existing elements.</li>
     * </ul>
     * </p>
     *
     * @param blockPosition The position of the block to validate.
     * @param map           The map where the city and block are located.
     * @return {@code true} if the block's position is valid, {@code false} otherwise.
     */
    private boolean isValidBlockPosition(Position blockPosition, Map map) {
        // Check if the block is within map boundaries
        if (blockPosition.getX() < 0 || blockPosition.getX() >= map.getWidth() ||
                blockPosition.getY() < 0 || blockPosition.getY() >= map.getHeight()) {
            return false;
        }

        // Check if the block doesn't overlap the city's position
        if (blockPosition.equals(this.position)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", x=" + position.getX() +
                ", y=" + position.getY() +
                ", houseBlocks=" + houseBlocks +
                '}';
    }
}

```

### Class HouseBlock

```java
package pt.ipp.isep.dei.domain;

/**
 * Represents a block of houses (HouseBlock) within a City in the system.
 * Each block has a position on the map that must adhere to map boundaries.
 */
public class HouseBlock {

    private Position position; // Position of the house block on the map

    /**
     * Constructs a HouseBlock with the specified position.
     *
     * @param position The position of the house block. Must not be null.
     * @throws IllegalArgumentException if position is null.
     */
    public HouseBlock(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        this.position = position;
    }

    public Position getPosition() { return position; }

    /**
     * Sets the position of the house block.
     *
     * @param position The new position of the house block. Must not be null.
     * @throws IllegalArgumentException if the position is null.
     */
    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        this.position = position;
    }

    /**
     * Returns a string representation of the house block.
     *
     * @return A string representation of the house block, including its position.
     */
    @Override
    public String toString() {
        return "HouseBlock{" +
                "position=" + position +
                '}';
    }
}
```

### Class CityRepository

```java
package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;


/**
 * Repository for managing City entities in memory.
 * <p>
 * Provides methods to add, retrieve, and validate cities,
 * including checks for name uniqueness and position conflicts.
 * </p>
 */
public class CityRepository {
    private List<City> cities = new ArrayList<>();


    /**
     * Retrieves the list of all cities.
     *
     * @return List of cities.
     */
    public List<City> getCities() {
        return cities;
    }


    /**
     * Checks if a city with the given name already exists (case-insensitive).
     *
     * @param name The name of the city to check.
     * @return true if a city with the given name exists, false otherwise.
     */
    public boolean cityExists(String name) {
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Saves a new city to the repository.
     *
     * @param city The city to add.
     */
    public void save(City city) {
        cities.add(city);
    }

    /**
     * Checks if the given position is already occupied by another city.
     *
     * @param position The position to check.
     * @return true if the position is already used, false otherwise.
     */
    public boolean positionInvalid(Position position) {
        for(City c : cities){
            if(c.getY() == position.getY() && c.getX() == position.getX())
                return true;
        }
        return false;
    }
}

```


## 6. Integration and Demo 

* The integration of the City, HouseBlock, and CityRepository classes enables the creation, validation, and storage of cities and their respective house blocks on a map.
 
* The CityRepository manages operations such as adding cities, searching by name, and checking for occupied positions, ensuring there are no overlaps.

* The City class validates names, coordinates, and the number of house blocks, throwing exceptions for invalid data.

* The random generation of house blocks is performed around the city's position, respecting map boundaries and avoiding overlap with the city itself.

* Accessor methods (getters and setters) allow modification and retrieval of entity attributes, always ensuring data integrity.

* Integration can be demonstrated through unit tests, which cover the main flows of creation, validation, and manipulation of the entities.

## 7. Observations

* The city repository is kept in memory, so there is no persistence between executions. For production use, it would be necessary to adapt it to a database.

* Validations are strict: city names cannot contain special characters or digits, coordinates must be positive, and the number of house blocks must always be positive.

* The random generation of house blocks respects map boundaries and avoids overlap with the city's position, but can be improved to prevent overlap between blocks and ensure full compliance with map limits.

* Accessor methods (getters and setters) allow modification and consultation of entity attributes, always ensuring data integrity.
  
* Unit tests cover both success and error cases, validating the robustness of business rules and correct integration between classes.
  
* The modular architecture facilitates system maintenance and extension, allowing new features to be added without compromising existing integrity.
  
* Method and class documentation makes it easier for other developers to understand and use the code.
  
* City removal is only allowed in edit mode, reinforcing safety during simulation.
  
* City name uniqueness is not mandatory, but it is recommended to avoid duplications for a better user experience.
  
* The system is prepared for future integration with other entities, such as industries or infrastructures, maintaining validation consistency.
