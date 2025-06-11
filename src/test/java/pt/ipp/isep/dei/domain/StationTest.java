package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    private Station station;
    private Position position;
    private String stationName;

    @BeforeEach
    void setUp() {
        position = new Position(10, 20);
        stationName = "Test Station";
        station = new Station(stationName, position, StationType.DEPOT);
    }

    @Test
    void testConstructor() {
        assertEquals(stationName, station.getName());
        assertEquals(position, station.getLocation());
        assertEquals(StationType.DEPOT, station.getType());
        assertTrue(station.getBuildings().isEmpty());
    }

    @Test
    void testGetName() {
        assertEquals(stationName, station.getName());
    }

    @Test
    void testGetLocation() {
        assertEquals(position, station.getLocation());
    }

    @Test
    void testGetType() {
        assertEquals(StationType.DEPOT, station.getType());
    }

    @Test
    void testGetBuildingsInitiallyEmpty() {
        assertNotNull(station.getBuildings());
        assertTrue(station.getBuildings().isEmpty());
    }

    @Test
    void testAddBuilding() {
        boolean added = station.addBuilding(BuildingType.TELEGRAPH);
        assertTrue(added);
        assertEquals(1, station.getBuildings().size());
        assertEquals(BuildingType.TELEGRAPH, station.getBuildings().get(0).getType());
    }

    @Test
    void testAddBuildingWithReplacement() {
        station.addBuilding(BuildingType.TELEGRAPH);
        boolean replaced = station.addBuilding(BuildingType.TELEPHONE);
        assertTrue(replaced);
        assertEquals(1, station.getBuildings().size());
        assertEquals(BuildingType.TELEPHONE, station.getBuildings().get(0).getType());
    }

    @Test
    void testRemoveExistingBuilding() {
        station.addBuilding(BuildingType.CAFE_SMALL);
        boolean removed = station.removeBuilding(BuildingType.CAFE_SMALL);
        assertTrue(removed);
        assertTrue(station.getBuildings().isEmpty());
    }

    @Test
    void testRemoveNonExistingBuilding() {
        assertFalse(station.removeBuilding(BuildingType.CAFE_SMALL));
    }

    @Test
    void testGetAvailableUpgrades() {
        List<BuildingType> allTypes = List.of(
                BuildingType.TELEGRAPH,
                BuildingType.TELEPHONE,
                BuildingType.CAFE_SMALL,
                BuildingType.CAFE_LARGE,
                BuildingType.POST_OFFICE
        );

        //List<BuildingType> available = station.getAvailableUpgrades(allTypes);
        assertTrue(available.contains(BuildingType.TELEGRAPH));
        assertTrue(available.contains(BuildingType.CAFE_SMALL));
        assertTrue(available.contains(BuildingType.POST_OFFICE));
    }

    @Test
    void testGetAvailableUpgrades_WithExistingBuildings() {
        station.addBuilding(BuildingType.TELEGRAPH);
        station.addBuilding(BuildingType.CAFE_SMALL);

        List<BuildingType> allTypes = List.of(
                BuildingType.TELEGRAPH,
                BuildingType.TELEPHONE,
                BuildingType.CAFE_SMALL,
                BuildingType.CAFE_LARGE,
                BuildingType.POST_OFFICE
        );

        List<BuildingType> upgrades = station.getAvailableUpgrades(allTypes);

        assertTrue(upgrades.contains(BuildingType.TELEPHONE));
        assertTrue(upgrades.contains(BuildingType.CAFE_LARGE));
        assertTrue(upgrades.contains(BuildingType.POST_OFFICE));
        assertFalse(upgrades.contains(BuildingType.TELEGRAPH));
        assertFalse(upgrades.contains(BuildingType.CAFE_SMALL));
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(station, station);
    }

    @Test
    void testEquals_EqualObjects() {
        Station other = new Station(stationName, new Position(5, 5), StationType.TERMINAL);
        assertEquals(station, other);
    }

    @Test
    void testEquals_DifferentName() {
        Station other = new Station("Other", position, StationType.DEPOT);
        assertNotEquals(station, other);
    }

    @Test
    void testEquals_Null() {
        assertNotEquals(station, null);
    }

    @Test
    void testEquals_DifferentClass() {
        assertNotEquals(station, "Not a station");
    }
}
