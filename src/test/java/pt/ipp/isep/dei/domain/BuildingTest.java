package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Building class.
 */
class BuildingTest {

    /**
     * Tests the constructor and the getType() method.
     * Ensures that the building type is stored and returned correctly.
     */
    @Test
    void testConstructorAndGetType() {
        // Arrange
        BuildingType buildingType = BuildingType.CAFE_LARGE;

        // Act
        Building building = new Building(buildingType);

        // Assert
        assertEquals(buildingType, building.getType(), "The building type should match the one provided in the constructor.");
    }

    /**
     * Tests the equals() method when two Building objects have the same type.
     * Ensures that such objects are considered equal.
     */
    @Test
    void testEquals_SameType() {
        // Arrange
        Building building1 = new Building(BuildingType.POST_OFFICE);
        Building building2 = new Building(BuildingType.POST_OFFICE);

        // Act & Assert
        assertEquals(building1, building2, "Buildings with the same type should be considered equal.");
    }

    /**
     * Tests the equals() method when two Building objects have different types.
     * Ensures that such objects are not considered equal.
     */
    @Test
    void testEquals_DifferentType() {
        // Arrange
        Building building1 = new Building(BuildingType.TELEGRAPH);
        Building building2 = new Building(BuildingType.CAFE_SMALL);

        // Act & Assert
        assertNotEquals(building1, building2, "Buildings with different types should not be considered equal.");
    }

    /**
     * Tests the equals() method when comparing a Building object to null.
     * Ensures that the result is false.
     */
    @Test
    void testEquals_Null() {
        // Arrange
        Building building = new Building(BuildingType.CAFE_LARGE);

        // Act & Assert
        assertNotEquals(building, null, "A Building object should not be considered equal to null.");
    }

    /**
     * Tests the equals() method when comparing a Building object to an object of a different class.
     * Ensures that the result is false.
     */
    @Test
    void testEquals_DifferentClass() {
        // Arrange
        Building building = new Building(BuildingType.CAFE_SMALL);
        Object differentObject = "Some String";

        // Act & Assert
        assertNotEquals(building, differentObject, "A Building object should not be equal to an object of a different class.");
    }

    /**
     * Tests the toString() method.
     * Ensures that the returned string representation is in the expected format.
     */
    @Test
    void testToString() {
        // Arrange
        Building building = new Building(BuildingType.TELEPHONE);

        // Act
        String result = building.toString();

        // Assert
        assertEquals("Building: TELEPHONE", result, "The toString method should return the correct string representation.");
    }

    /**
     * Tests the equals() method when both objects refer to the same instance.
     * Ensures that they are considered equal.
     */
    @Test
    void testEquals_SameInstance() {
        // Arrange
        Building building = new Building(BuildingType.POST_OFFICE);

        // Act & Assert
        assertEquals(building, building, "A Building object should be equal to itself.");
    }
}
