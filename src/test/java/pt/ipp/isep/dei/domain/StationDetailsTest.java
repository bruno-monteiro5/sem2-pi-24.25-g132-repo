package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StationDetails} class.
 * <p>
 * The tests ensure correct behavior of all public methods and validation for all scenarios.
 */
class StationDetailsTest {

    private StationDetails stationDetails;
    private Position initialPosition;
    private Cargo supplyCargo1;
    private Cargo supplyCargo2;
    private Cargo demandCargo1;
    private Building building1;
    private Building building2;

    /**
     * Sets up reusable test data before each test case.
     */
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

    /**
     * Tests the StationDetails constructor and ensures proper initial values.
     */
    @Test
    void testConstructor() {
        assertEquals("Test Station", stationDetails.getStationName());
        assertEquals(StationType.STATION, stationDetails.getStationType());
        assertEquals(initialPosition, stationDetails.getPosition());
        assertTrue(stationDetails.getSupplyCargo().isEmpty());
        assertTrue(stationDetails.getDemandCargo().isEmpty());
        assertTrue(stationDetails.getBuildings().isEmpty());
    }

    /**
     * Ensures setting and retrieving the station name works correctly.
     */
    @Test
    void testSetAndGetStationName() {
        stationDetails.setStationName("New Station Name");
        assertEquals("New Station Name", stationDetails.getStationName());
    }

    /**
     * Ensures setting and retrieving the station type works correctly.
     */
    @Test
    void testSetAndGetStationType() {
        stationDetails.setStationType(StationType.TERMINAL);
        assertEquals(StationType.TERMINAL, stationDetails.getStationType());
    }

    /**
     * Ensures setting and retrieving the geographical position works correctly.
     */
    @Test
    void testSetAndGetPosition() {
        Position newPosition = new Position(30, 40);
        stationDetails.setPosition(newPosition);
        assertEquals(newPosition, stationDetails.getPosition());
    }

    /**
     * Tests adding and retrieving supply cargo.
     */
    @Test
    void testAddAndGetSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.addSupplyCargo(supplyCargo2);
        List<Cargo> supplyCargoList = stationDetails.getSupplyCargo();

        assertEquals(2, supplyCargoList.size());
        assertTrue(supplyCargoList.contains(supplyCargo1));
        assertTrue(supplyCargoList.contains(supplyCargo2));
    }

    /**
     * Tests adding and retrieving demand cargo.
     */
    @Test
    void testAddAndGetDemandCargo() {
        stationDetails.addDemandCargo(demandCargo1);
        List<Cargo> demandCargoList = stationDetails.getDemandCargo();

        assertEquals(1, demandCargoList.size());
        assertTrue(demandCargoList.contains(demandCargo1));
    }

    /**
     * Tests removing supply cargo of a specific type when it exists.
     */
    @Test
    void testRemoveExistingSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.addSupplyCargo(supplyCargo2);
        stationDetails.removeSupplyCargo(CargoType.FOOD);

        List<Cargo> remainingSupplyCargo = stationDetails.getSupplyCargo();
        assertEquals(1, remainingSupplyCargo.size());
        assertEquals(CargoType.COAL, remainingSupplyCargo.get(0).getType());
    }

    /**
     * Tests removing supply cargo of a type that does not exist in the list.
     */
    @Test
    void testRemoveNonExistingSupplyCargo() {
        stationDetails.addSupplyCargo(supplyCargo1);
        stationDetails.removeSupplyCargo(CargoType.MAIL);
        List<Cargo> remainingSupplyCargo = stationDetails.getSupplyCargo();

        assertEquals(1, remainingSupplyCargo.size());
        assertEquals(CargoType.FOOD, remainingSupplyCargo.get(0).getType());
    }

    /**
     * Tests adding and retrieving buildings.
     */
    @Test
    void testAddAndGetBuildings() {
        stationDetails.getBuildings().add(building1);
        stationDetails.getBuildings().add(building2);

        List<Building> buildingsList = stationDetails.getBuildings();

        assertEquals(2, buildingsList.size());
        assertTrue(buildingsList.contains(building1));
        assertTrue(buildingsList.contains(building2));
    }

    /**
     * Tests string representation of StationDetails. Assumes toString is implemented.
     */
    @Test
    void testToString() {
        String toStringOutput = stationDetails.toString();
        assertNotNull(toStringOutput);
        assertTrue(toStringOutput.contains("Test Station"));
        assertTrue(toStringOutput.contains("Position"));
        assertTrue(toStringOutput.contains("STATION"));
    }
}