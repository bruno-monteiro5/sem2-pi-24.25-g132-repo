# US003 - Add a City 

## 4. Tests 

#### 4.1. Class City 

**Test 1:** Check that it is not possible to create an instance of the City class with null values.

	@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        City instance = new City(null, null, null);
    }


**Test 2:** Check that it is not possible to create an instance of the City class with a name containing special characters or digits - AC1.


	@Test(expected = IllegalArgumentException.class)
    public void ensureNameMeetsAC1() {
        Position position = new Position(10, 20);
        City instance = new City("City123", position, 5);
    }


**Test 3:** Check that it is not possible to create an instance of the City class with a non-positive number of house blocks.

    @Test(expected = IllegalArgumentException.class)
    public void ensurePositiveHouseBlocks() {
        Position position = new Position(10, 20);
        City instance = new City("Porto", position, 0);
    }


**Test 4:** Check that a city is correctly created with valid values.


    @Test
    public void ensureValidCityIsCreated() {
        Position position = new Position(10, 20);
        int houseBlocks = 5;
        String name = "Lisboa";

        City instance = new City(name, position, houseBlocks);
    
        assertEquals(name, instance.getName());
        assertEquals(position, instance.getPosition());
        assertEquals(houseBlocks, instance.getHouseBlocks());
    }

**Test 5:** Ensure that the house blocks created automatically are valid and within the map boundaries.


    @Test
    public void ensureRandomBlocksAreValid() {
        Position cityPosition = new Position(10, 20);
        int houseBlocks = 5;
        Map map = new Map("Test Map", 30, 30);
    
        City city = new City("Lisbon", cityPosition, houseBlocks);
        city.generateRandomHouseBlocks(map);
    
        for (HouseBlock block : city.getHouseBlocksList()) {
            Position blockPosition = block.getPosition();
            assertTrue(blockPosition.getX() >= 0 && blockPosition.getX() < map.getWidth());
            assertTrue(blockPosition.getY() >= 0 && blockPosition.getY() < map.getHeight());
            assertNotEquals(blockPosition, cityPosition); // Ensures no overlap with city
        }
    }    
#### 4.2. Class AddCityController

**Test 6:** Check that the controller alerts when a city with a repeated name is added.

    @Test
    public void ensureAlertOnRepeatedName() {
        Position position1 = new Position(10, 20);
        Position position2 = new Position(30, 40);
        Map map = new Map("Test Map", 100, 100);
    
        City city1 = new City("Lisbon", position1, 5);
        map.addCity(city1);

        AddCityController controller = new AddCityController(map);
        boolean isRepeated = controller.checkNameExists("Lisbon");

        assertTrue(isRepeated);
    }

**Test 7:** Check that a city cannot be placed on top of another city.

    @Test(expected = IllegalArgumentException.class)
    public void ensureCityCannotBeOnAnotherCity() {
        Position position = new Position(10, 20);
        Map map = new Map("Test Map", 100, 100);
        
        City city1 = new City("Lisboa", position, 5);
        map.addCity(city1);
        
        AddCityController controller = new AddCityController(map);
        controller.addCity("Porto", position, 6, true);
    }

**Test 8:** Ensure that cities cannot be added outside the map boundaries.


    @Test(expected = IllegalArgumentException.class)
        public void ensureCityIsWithinMapBoundaries() {
        Position invalidPosition = new Position(150, 50); // Outside the map boundaries
        Map map = new Map("Test Map", 100, 100);
    
        AddCityController controller = new AddCityController(map);
        controller.addCity("Invalid City", invalidPosition, 5, true);
    }


## 5. Construction (Implementation)

### Class City 

```java
/**
 * Represents a City that can be placed on a Map in the system. 
 * Each city has a name, a position on the map, and an assigned number of house blocks.
 * It also supports automatic or manual generation of house blocks.
 */
public class City {
    private final String name;
    private final Position position;
    private final int houseBlocks;
    private final List<HouseBlock> houseBlocksList;

    /**
     * Constructs a new City instance.
     *
     * @param name        The name of the city. Must not be null or contain special characters/digits.
     * @param position    The position of the city on the map. Must not be null.
     * @param houseBlocks The number of house blocks in the city. Must be greater than 0.
     * @throws IllegalArgumentException If any of the parameters are invalid.
     */
    public City(String name, Position position, Integer houseBlocks) {
        if (name == null || position == null || houseBlocks == null) {
            throw new IllegalArgumentException("No argument can be null");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("City name cannot contain special characters or digits");
        }

        if (houseBlocks <= 0) {
            throw new IllegalArgumentException("The number of house blocks must be positive");
        }

        this.name = name;
        this.position = position;
        this.houseBlocks = houseBlocks;
        this.houseBlocksList = new ArrayList<>();
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getHouseBlocks() {
        return houseBlocks;
    }

    /**
     * Retrieves the list of house blocks generated for the city.
     *
     * @return A list of house blocks associated with the city.
     */
    public List<HouseBlock> getHouseBlocksList() {
        return new ArrayList<>(houseBlocksList);
    }

    /**
     * Generates house blocks for the city based on either manual or automatic placement.
     * Manual placement should be handled by the user via the user interface.
     *
     * @param automatic If true, the house blocks are distributed randomly around the city's position.
     */
    public void generateHouseBlocks(boolean automatic) {
        houseBlocksList.clear();

        if (automatic) {
            generateRandomHouseBlocks();
        } else {
            // Implementation for manual placement will be done in the UI
        }
    }

    /**
     * Generates house blocks placed randomly around the city's position.
     * Ensures that the blocks are within the map boundaries and do not overlap with
     * the city's position.
     *
     * @param map The map on which the city is placed.
     */
    public void generateRandomHouseBlocks(Map map) {
        houseBlocksList.clear();
        Random random = new Random();
        for (int i = 0; i < houseBlocks; i++) {
            Position blockPosition;
            do {
                int offsetX = random.nextInt(11) - 5; // -5 to 5
                int offsetY = random.nextInt(11) - 5;

                int blockX = position.getX() + offsetX;
                int blockY = position.getY() + offsetY;
                blockPosition = new Position(blockX, blockY);
            } while (!isValidBlockPosition(blockPosition, map));

            HouseBlock block = new HouseBlock(blockPosition);
            houseBlocksList.add(block);
        }
    }

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

}
```

### Class AddCityController

```java
/**
 * Controller responsible for adding cities to a map. 
 * Handles validation such as position checks, name uniqueness, and boundary enforcement.
 */
public class AddCityController {
    private final Map selectedMap;

    /**
     * Constructs a new AddCityController for a given map.
     *
     * @param map The map on which cities will be added. Must not be null.
     * @throws IllegalArgumentException If the map is null.
     */
    public AddCityController(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }
        this.selectedMap = map;
    }

    /**
     * Adds a new city to the map.
     * This method validates the position of the city, 
     * checks for overlapping with other cities or industries, and optionally enforces unique names.
     *
     * @param name              The name of the city.
     * @param position          The position of the city on the map.
     * @param houseBlocks       The number of house blocks in the city.
     * @param automaticBlocks   If true, automatically generates house blocks around the city position.
     * @return The created City instance.
     * @throws IllegalArgumentException If the city cannot be added due to a validation failure.
     */
    public City addCity(String name, Position position, int houseBlocks, boolean automaticBlocks) {
        validatePosition(position);

        City city = new City(name, position, houseBlocks);
        city.generateHouseBlocks(automaticBlocks);

        selectedMap.addCity(city);

        return city;
    }

    /**
     * Checks if a city with the given name already exists on the map.
     *
     * @param name The name of the city to check.
     * @return True if a city with the same name exists, false otherwise.
     */
    public boolean checkNameExists(String name) {
        return selectedMap.getCities().stream()
                .anyMatch(city -> city.getName().equalsIgnoreCase(name));
    }

    /**
     * Validates the provided position to ensure it is not already occupied
     * by another city or industry and that it is within the map's boundaries.
     *
     * @param position The position to validate.
     * @throws IllegalArgumentException If the position is invalid.
     */
    private void validatePosition(Position position) {
        // Validate boundaries of the map
        if (position.getX() < 0 || position.getX() >= selectedMap.getWidth() ||
                position.getY() < 0 || position.getY() >= selectedMap.getHeight()) {
            throw new IllegalArgumentException("Position " + position + " is outside map boundaries. Valid range: (0, 0) to ("
                    + (selectedMap.getWidth() - 1) + ", " + (selectedMap.getHeight() - 1) + ").");
        }
        // Check if the position is already occupied by another city
        for (City city : selectedMap.getCities()) {
            if (city.getPosition().equals(position)) {
                throw new IllegalArgumentException("There is already a city at this position");
            }
        }
        // Check if the position is already occupied by an industry
        for (Industry industry : selectedMap.getIndustries()) {
            if (industry.getPosition().equals(position)) {
                throw new IllegalArgumentException("There is already an industry at this position");
            }
        }
    }
}
```


## 6. Integration and Demo 

* A new option was added to the Editor menu called "Add City".

* For demonstration purposes, some cities are initialized when the system starts.

* When adding a city with a name that already exists, the system alerts the editor about the situation but allows the operation to continue.

* If the user chooses automatic placement of house blocks, they are randomly distributed around the city position.



## 7. Observations

* The implementation of manual house block placement will be done in the user interface, allowing the editor to place each block individually on the map.

* The validation of the city name (AC1) is done using a regular expression that only allows letters and spaces.
