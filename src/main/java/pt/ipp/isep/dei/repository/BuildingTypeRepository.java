package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.BuildingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Repository for managing building types.
 *
 * <p>The {@code BuildingTypeRepository} class provides functionality to store,
 * retrieve, and manage the available building types in the system. It acts as
 * an access layer to handle building-related operations efficiently.</p>
 */
public class BuildingTypeRepository {
    private List<BuildingType> buildingTypes;

    /**
     * Constructs a new {@code BuildingTypeRepository} and initializes it
     * with all available building types.
     */
    public BuildingTypeRepository() {
        buildingTypes = new ArrayList<>(Arrays.asList(BuildingType.values()));
    }

    /**
     * Retrieves all the building types available in the repository.
     *
     * @return A new {@code List} containing all building types.
     */
    public List<BuildingType> getAllBuildingTypes() {
        return new ArrayList<>(buildingTypes);
    }

    /**
     * Retrieves the details of a specific building type.
     *
     * @param type The {@code BuildingType} whose details are to be retrieved.
     * @return The {@code BuildingType} provided as the parameter.
     */
    public BuildingType getBuildingTypeDetails(BuildingType type) {
        return type;
    }
}