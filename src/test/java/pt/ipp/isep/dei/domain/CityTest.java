// src/test/java/pt/ipp/isep/dei/domain/CityTest.java
package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    /**
     * Tests the creation of a valid City object and verifies its properties.
     */
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

    /**
     * Ensures that creating a City with an invalid name throws an IllegalArgumentException.
     */
    @Test
    void testInvalidNameThrowsException() {
        Map map = new Map("Mapa2", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto123", pos, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto!", pos, 2));
    }


    /**
     * Ensures that creating a City with null arguments throws an IllegalArgumentException.
     */
    @Test
    void testNullArgumentsThrowException() {
        Map map = new Map("Mapa3", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new City(map, null, pos, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto", null, 2));
        assertThrows(IllegalArgumentException.class, () -> new City(map, "Porto", pos, null));
    }

    /**
     * Tests the setters and getters of the City class.
     */
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

    /**
     * Ensures that setting the map to null throws an IllegalArgumentException.
     */
    @Test
    void testSetMapNullThrowsException() {
        Map map = new Map("Mapa5", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setMap(null));
    }

    /**
     * Ensures that setting the name to null or empty throws an IllegalArgumentException.
     */
    @Test
    void testSetNameNullOrEmptyThrowsException() {
        Map map = new Map("Mapa6", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setName(null));
        assertThrows(IllegalArgumentException.class, () -> city.setName(""));
    }


    /**
     * Ensures that setting invalid X or Y coordinates throws an IllegalArgumentException.
     */
    @Test
    void testSetPositionXAndYInvalidThrowsException() {
        Map map = new Map("Mapa7", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setPositionX(0));
        assertThrows(IllegalArgumentException.class, () -> city.setPositionY(-1));
    }

    /**
     * Ensures that setting an invalid number of house blocks throws an IllegalArgumentException.
     */
    @Test
    void testSetNumberOfHouseBlocksInvalidThrowsException() {
        Map map = new Map("Mapa8", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 2);
        assertThrows(IllegalArgumentException.class, () -> city.setNumberOfHouseBlocks(0));
        assertThrows(IllegalArgumentException.class, () -> city.setNumberOfHouseBlocks(-5));
    }

    /**
     * Tests the random generation of house blocks and verifies the resulting values.
     */
    @Test
    void testGenerateRandomHouseBlocks() {
        Map map = new Map("Mapa9", 10, 10, "editor@this.app");
        Position pos = new Position(1, 1);
        City city = new City(map, "Porto", pos, 1);
        city.generateRandomHouseBlocks();
        assertTrue(city.getNumberOfHouseBlocks() >= 1 && city.getNumberOfHouseBlocks() <= 10);
        assertEquals(city.getNumberOfHouseBlocks(), city.getHouseBlocksList().size());
    }

    /**
     * Tests the string representation of the City object.
     */
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
}