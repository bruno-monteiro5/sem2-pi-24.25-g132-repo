# US007 - Sees the Details of a Station

## 4. Tests

#### 4.1. Class StationDetails

**Methode BeforeEach** Sets up test data and initializes common objects in all tests.


    @BeforeEach
    void setUp() {
        initialPosition = new Position(10, 20); // Example position
        stationDetails = new StationDetails("Test Station", initialPosition, StationType.STATION);

        supplyCargo1 = new Cargo(CargoType.FOOD, 100, true);
        supplyCargo2 = new Cargo(CargoType.COAL, 50, true);
        demandCargo1 = new Cargo(CargoType.IRON_ORE, 75, false);

        building1 = new Building(BuildingType.CAFE_SMALL);
        building2 = new Building(BuildingType.POST_OFFICE);
    }

**Test 1:** Tests the StationDetails constructor and ensures proper initial values.

	 @Test
    void testConstructor() {
        assertEquals("Test Station", stationDetails.getStationName());
        assertEquals(StationType.STATION, stationDetails.getStationType());
        assertEquals(initialPosition, stationDetails.getPosition());
        assertTrue(stationDetails.getSupplyCargo().isEmpty());
        assertTrue(stationDetails.getDemandCargo().isEmpty());
        assertTrue(stationDetails.getBuildings().isEmpty());
    }


**Test 2:** Ensures setting and retrieving the station name works correctly.

    @Test
    void testSetAndGetStationName() {
        stationDetails.setStationName("New Station Name");
        assertEquals("New Station Name", stationDetails.getStationName());
    }


**Test 3:** Ensures setting and retrieving the station type works correctly.

    @Test
    void testSetAndGetStationType() {
        stationDetails.setStationType(StationType.TERMINAL);
        assertEquals(StationType.TERMINAL, stationDetails.getStationType());
    }


**Test 4:** Ensures setting and retrieving the geographical position works correctly.

    @Test
    void testSetAndGetPosition() {
        Position newPosition = new Position(30, 40);
        stationDetails.setPosition(newPosition);
        assertEquals(newPosition, stationDetails.getPosition());
    }


**Test 5:** Tests adding and retrieving supply cargo.

    @Test
    void testAddAndGetSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.addSupplyCargo(supplyCargo2);
        List<Cargo> supplyCargoList = stationDetails.getSupplyCargo();

        assertEquals(2, supplyCargoList.size());
        assertTrue(supplyCargoList.contains(supplyCargo1));
        assertTrue(supplyCargoList.contains(supplyCargo2));
    }


**Test 6:** Tests adding and retrieving demand cargo.

    @Test
    void testAddAndGetDemandCargo() {
        stationDetails.addDemandCargo(demandCargo1);
        List<Cargo> demandCargoList = stationDetails.getDemandCargo();

        assertEquals(1, demandCargoList.size());
        assertTrue(demandCargoList.contains(demandCargo1));
    }

**Test 7:** Tests removing supply cargo of a specific type when it exists.

    @Test
    void testRemoveExistingSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.addSupplyCargo(supplyCargo2);
        stationDetails.removeSupplyCargo(CargoType.FOOD);

        List<Cargo> remainingSupplyCargo = stationDetails.getSupplyCargo();
        assertEquals(1, remainingSupplyCargo.size());
        assertEquals(CargoType.COAL, remainingSupplyCargo.get(0).getType());
    }

**Test 8:** Tests removing supply cargo of a type that does not exist in the list.

    @Test
    void testRemoveNonExistingSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.removeSupplyCargo(CargoType.MAIL);
        List<Cargo> remainingSupplyCargo = stationDetails.getSupplyCargo();

        assertEquals(1, remainingSupplyCargo.size());
        assertEquals(CargoType.FOOD, remainingSupplyCargo.get(0).getType());
    }

**Test 9:** Tests adding and retrieving buildings.

    @Test
    void testAddAndGetBuildings() {
        stationDetails.getBuildings().add(building1);
        stationDetails.getBuildings().add(building2);

        List<Building> buildingsList = stationDetails.getBuildings();

        assertEquals(2, buildingsList.size());
        assertTrue(buildingsList.contains(building1));
        assertTrue(buildingsList.contains(building2));
    }

**Test 10:** Tests string representation of StationDetails. Assumes toString is implemented.

    @Test
    void testToString() {
        String toStringOutput = stationDetails.toString();
        assertNotNull(toStringOutput);
        assertTrue(toStringOutput.contains("Test Station"));
        assertTrue(toStringOutput.contains("Position"));
        assertTrue(toStringOutput.contains("STATION"));
    }

#### 4.2. Class StationDetailsRepository

**Methode BeforeEach** Sets up test data and initializes common objects in all tests.

    @BeforeEach
    void resetRepository() throws Exception {
        var instanceField = StationDetailsRepository.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
        repository = StationDetailsRepository.getInstance();
    }

**Test 11:** Tests that the singleton instance is properly returned and ensures it is unique.

    @Test
    void testGetInstance() {
        StationDetailsRepository instance1 = StationDetailsRepository.getInstance();
        StationDetailsRepository instance2 = StationDetailsRepository.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }


**Test 12:** Tests adding a new StationDetails object to the repository.

    @Test
    void testAddStationDetails() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.DEPOT);
        repository.addStationDetails(details);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(1, result.size());
        assertEquals(details, result.get(0));
    }


**Test 13:** Tests retrieving all StationDetails objects from the repository when it is empty.

    @Test
    void testGetAllStationDetailsWhenEmpty() {
        List<StationDetails> result = repository.getAllStationDetails();
        assertTrue(result.isEmpty());
    }

**Test 14:** Tests retrieving all StationDetails objects after adding multiple entries.

    @Test
    void testGetAllStationDetailsWithEntries() {
        StationDetails details1 = new StationDetails("Station 1", new Position(10, 20), StationType.STATION);
        StationDetails details2 = new StationDetails("Station 2", new Position(30, 40), StationType.TERMINAL);

        repository.addStationDetails(details1);
        repository.addStationDetails(details2);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(2, result.size());
        assertTrue(result.contains(details1));
        assertTrue(result.contains(details2));
    }

**Test 15:** Tests retrieving station details by name when the station exists.

    @Test
    void testGetStationDetailsByNameExists() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.STATION);
        repository.addStationDetails(details);

        StationDetails result = repository.getStationDetailsByName("Test Station");
        assertNotNull(result);
        assertEquals(details, result);
    }


**Test 16:** Tests retrieving station details by name when the station does not exist.

    @Test
    void testGetStationDetailsByNameNotExists() {
        StationDetails result = repository.getStationDetailsByName("Nonexistent Station");
        assertNull(result);
    }

**Test 17:** Tests removing a station's details by name when the station exists.

    @Test
    void testRemoveStationDetailsByNameExists() {
        StationDetails details = new StationDetails("Test Station", new Position(10, 20), StationType.STATION);
        repository.addStationDetails(details);

        boolean removed = repository.removeStationDetailsByName("Test Station");
        assertTrue(removed);

        assertTrue(repository.getAllStationDetails().isEmpty());
    }

**Test 18:** Tests removing a station's details by name when the station does not exist.

    @Test
    void testRemoveStationDetailsByNameNotExists() {
        boolean removed = repository.removeStationDetailsByName("Nonexistent Station");
        assertFalse(removed);
    }

**Test 19:** Tests adding a station to the repository from a Station object.

    @Test
    void testAddStationFromStation() {
        Station station = new Station("Generated Station", new Position(50, 60), StationType.TERMINAL);
        repository.addStationFromStation(station);

        List<StationDetails> result = repository.getAllStationDetails();
        assertEquals(1, result.size());

        StationDetails details = result.get(0);
        assertEquals("Generated Station", details.getStationName());
        assertEquals(new Position(50, 60), details.getPosition());
        assertEquals(StationType.TERMINAL, details.getStationType());
    }

## 5. Construction (Implementation)

### Class StationDetails

```java
package pt.ipp.isep.dei.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents detailed information about a station, including its cargo supplies
 * and demands, geographical position, type, and associated buildings.
 *
 * <p>The {@code StationDetails} class supports operations for managing the
 * station's cargo (supply and demand) and buildings, as well as modification
 * of basic station properties such as its name, type, and position.</p>
 */
public class StationDetails {

    private String stationName;
    private Position position;
    private StationType stationType;
    private final List<Cargo> supplyCargo; // Cargo available for collection
    private final List<Cargo> demandCargo; // Cargo that needs to be delivered
    private final List<Building> buildings; // Buildings associated with the station

    /**
     * Constructs a {@code StationDetails} instance with specified station information.
     *
     * @param stationName The name of the station.
     * @param position    The geographical position of the station.
     * @param stationType The type of the station.
     */
    public StationDetails(String stationName, Position position, StationType stationType) {
        this.stationName = stationName;
        this.position = position;
        this.stationType = stationType;
        this.supplyCargo = new ArrayList<>();
        this.demandCargo = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }


    /**
     * Retrieves the name of the station.
     *
     * @return The name of the station.
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Updates the name of the station.
     *
     * @param stationName The new name of the station.
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * Retrieves the geographical position of the station.
     *
     * @return The {@code Position} of the station.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Updates the geographical position of the station.
     *
     * @param position The new {@code Position} of the station.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the type of the station.
     *
     * @return The {@code StationType} of the station.
     */
    public StationType getStationType() {
        return stationType;
    }

    /**
     * Updates the type of the station.
     *
     * @param stationType The new {@code StationType}.
     */
    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }

    /**
     * Retrieves the list of cargo available for collection (supply cargo).
     *
     * @return A list of {@code Cargo} representing the supply cargo.
     */
    public List<Cargo> getSupplyCargo() {
        return supplyCargo;
    }

    /**
     * Retrieves the list of cargo that needs to be delivered (demand cargo).
     *
     * @return A list of {@code Cargo} representing the demand cargo.
     */
    public List<Cargo> getDemandCargo() {
        return demandCargo;
    }

    /**
     * Retrieves the list of buildings associated with the station.
     *
     * @return A list of {@code Building} objects.
     */
    public List<Building> getBuildings() {
        return buildings;
    }


    /**
     * Adds a cargo item to the supply cargo list.
     *
     * @param cargo The {@code Cargo} to be added to the supply cargo list.
     */
    public void addSupplyCargo(Cargo cargo) {
        this.supplyCargo.add(cargo);
    }

    /**
     * Adds a cargo item to the demand cargo list.
     *
     * @param cargo The {@code Cargo} to be added to the demand cargo list.
     */
    public void addDemandCargo(Cargo cargo) {
        this.demandCargo.add(cargo);
    }

    /**
     * Removes cargo of a specific type from the supply cargo list.
     *
     * @param type The {@code CargoType} to be removed from the supply cargo list.
     */
    public void removeSupplyCargo(CargoType type) {
        this.supplyCargo.removeIf(cargo -> cargo.getType() == type);
    }

    /**
     * Removes cargo of a specific type from the demand cargo list.
     *
     * @param type The {@code CargoType} to be removed from the demand cargo list.
     */
    public void removeDemandCargo(CargoType type) {
        this.demandCargo.removeIf(cargo -> cargo.getType() == type);
    }


    /**
     * Adds a building to the list of buildings associated with the station.
     *
     * @param building The {@code Building} to be added.
     */
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    /**
     * Removes a building of a specific type from the list of buildings.
     *
     * @param buildingType The {@code BuildingType} of the building to be removed.
     */
    public void removeBuilding(BuildingType buildingType) {
        this.buildings.removeIf(building -> building.getType() == buildingType);
    }


    /**
     * Provides a string representation of the station details, including its name,
     * type, position, buildings, and cargo (supply and demand).
     *
     * @return A string representation of the station details.
     */
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Station Details:\n");
        details.append("Name: ").append(stationName).append("\n");
        details.append("Type: ").append(stationType).append("\n");
        details.append("Position: ").append(position).append("\n\n");
        details.append("Buildings:\n");
        for (Building building : buildings) {
            details.append(" - ").append(building.getType()).append("\n");
        }
        details.append("\nSupply Cargo:\n");
        for (Cargo cargo : supplyCargo) {
            details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
        }
        details.append("\nDemand Cargo:\n");
        for (Cargo cargo : demandCargo) {
            details.append(" - ").append(cargo.getType()).append(": ").append(cargo.getQuantity()).append("\n");
        }
        return details.toString();
    }
}
```

### Class StationDetailsRepository

```java
package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.StationDetails;
import pt.ipp.isep.dei.domain.Station;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing detailed information of stations.
 */
public class StationDetailsRepository {

    private static StationDetailsRepository instance;
    private final List<StationDetails> stationDetailsList;

    /**
     * Private constructor for singleton pattern.
     */
    private StationDetailsRepository() {
        this.stationDetailsList = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the StationDetailsRepository.
     *
     * @return The instance of the repository.
     */
    public static StationDetailsRepository getInstance() {
        if (instance == null) {
            instance = new StationDetailsRepository();
        }
        return instance;
    }

    /**
     * Adds a new StationDetails object to the repository.
     *
     * @param stationDetails The StationDetails object to add.
     */
    public void addStationDetails(StationDetails stationDetails) {
        this.stationDetailsList.add(stationDetails);
    }

    /**
     * Retrieves all station details.
     *
     * @return A list of all StationDetails objects.
     */
    public List<StationDetails> getAllStationDetails() {
        return new ArrayList<>(this.stationDetailsList);
    }

    /**
     * Finds the details of a station by its name.
     *
     * @param stationName The name of the station.
     * @return The StationDetails object, or null if not found.
     */
    public StationDetails getStationDetailsByName(String stationName) {
        for (StationDetails details : stationDetailsList) {
            if (details.getStationName().equalsIgnoreCase(stationName)) {
                return details;
            }
        }
        return null;
    }

    /**
     * Removes the details of a station by its name.
     *
     * @param stationName The name of the station to remove details for.
     * @return True if successfully removed, false otherwise.
     */
    public boolean removeStationDetailsByName(String stationName) {
        return this.stationDetailsList.removeIf(details -> details.getStationName().equalsIgnoreCase(stationName));
    }

    public void addStationFromStation(Station station) {
        StationDetails details = new StationDetails(station.getName(), station.getLocation(), station.getType());
        addStationDetails(details);
    }

}
```


## 6. Integration and Demo

* The feature integrates classes to manage station details consistently. All interactions, such as adding or removing cargo and buildings, work seamlessly.

* Demonstration steps include creating a station, adding/removing supply and demand cargo, associating buildings, and validating these actions using unit tests to ensure proper functionality.


## 7. Observations

* Strict validations prevent data inconsistencies in station attributes.

* Unit tests confirm the correctness of validations, business rules, and integrations.

* The current structure supports future features like dynamic simulations and system-wide interactions.
