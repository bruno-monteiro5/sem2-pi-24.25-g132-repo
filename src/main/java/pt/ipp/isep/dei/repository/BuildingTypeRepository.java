package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.BuildingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Repository for building types.
 */
public class BuildingTypeRepository {
    private List<BuildingType> buildingTypes;

    /**
     * Creates a new BuildingTypeRepository.
     */
    public BuildingTypeRepository() {
        buildingTypes = new ArrayList<>(Arrays.asList(BuildingType.values()));
    }

    /**
     * Gets all available building types.
     *
     * @return A list of all building types
     */
    public List<BuildingType> getAllBuildingTypes() {
        return new ArrayList<>(buildingTypes);
    }

    /**
     * Gets the details of a specific building type.
     *
     * @param type The building type to get details for
     * @return The building type details
     */
    public BuildingType getBuildingTypeDetails(BuildingType type) {
        return type;
    }
}