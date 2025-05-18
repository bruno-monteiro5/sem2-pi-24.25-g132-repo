package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BuildingType enum.
 */
class BuildingTypeTest {

    /**
     * Tests the getCost() method for a BuildingType.
     * Ensures the cost is correctly returned.
     */
    @Test
    void testGetCost() {
        // Arrange
        BuildingType buildingType = BuildingType.TELEGRAPH;

        // Act
        double cost = buildingType.getCost();

        // Assert
        assertEquals(100, cost, "Cost should match the value defined in the BuildingType.");
    }

    /**
     * Tests the getReplaces() method for a BuildingType that replaces another type.
     * Ensures the replaced building type is returned correctly.
     */
    @Test
    void testGetReplaces_NotNull() {
        // Arrange
        BuildingType buildingType = BuildingType.TELEPHONE;

        // Act
        BuildingType replaces = buildingType.getReplaces();

        // Assert
        assertEquals(BuildingType.TELEGRAPH, replaces, "The replaced building type should be TELEGRAPH for TELEPHONE.");
    }

    /**
     * Tests the getReplaces() method for a BuildingType that does not replace another type.
     * Ensures null is returned when there is no replaced building type.
     */
    @Test
    void testGetReplaces_Null() {
        // Arrange
        BuildingType buildingType = BuildingType.POST_OFFICE;

        // Act
        BuildingType replaces = buildingType.getReplaces();

        // Assert
        assertNull(replaces, "Replaced building type should be null for POST_OFFICE.");
    }

    /**
     * Tests the isAvailableIn() method for a year less than the available year of the BuildingType.
     * Verifies that false is returned in this case.
     */
    @Test
    void testIsAvailableIn_YearBeforeAvailable() {
        // Arrange
        BuildingType buildingType = BuildingType.TELEPHONE;

        // Act
        boolean isAvailable = buildingType.isAvailableIn(1899);

        // Assert
        assertFalse(isAvailable, "BuildingType TELEPHONE should not be available before 1900.");
    }

    /**
     * Tests the isAvailableIn() method for the exact year when the BuildingType becomes available.
     * Verifies that true is returned in this case.
     */
    @Test
    void testIsAvailableIn_YearExactAvailable() {
        // Arrange
        BuildingType buildingType = BuildingType.TELEPHONE;

        // Act
        boolean isAvailable = buildingType.isAvailableIn(1900);

        // Assert
        assertTrue(isAvailable, "BuildingType TELEPHONE should be available starting from 1900.");
    }

    /**
     * Tests the isAvailableIn() method for a year greater than the available year of the BuildingType.
     * Verifies that true is returned in this case.
     */
    @Test
    void testIsAvailableIn_YearAfterAvailable() {
        // Arrange
        BuildingType buildingType = BuildingType.TELEGRAPH;

        // Act
        boolean isAvailable = buildingType.isAvailableIn(1900);

        // Assert
        assertTrue(isAvailable, "BuildingType TELEGRAPH should be available after 1850.");
    }

    /**
     * Tests the getBuildingTypeDetails() method.
     * Verifies that the method returns the current building type itself.
     */
    @Test
    void testGetBuildingTypeDetails() {
        // Arrange
        BuildingType buildingType = BuildingType.CAFE_LARGE;

        // Act
        BuildingType result = buildingType.getBuildingTypeDetails();

        // Assert
        assertEquals(buildingType, result, "The getBuildingTypeDetails method should return the current building type.");
    }
}
