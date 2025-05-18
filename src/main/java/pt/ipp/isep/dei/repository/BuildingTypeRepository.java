package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.BuildingType;

import java.util.List;
import java.util.Optional;

public class BuildingTypeRepository {
    private final List<BuildingType> buildingTypeList;

    /**
     * Initializes the repository with a given list of building types.
     * @param buildingTypeList A list of all building types in the system.
     */
    public BuildingTypeRepository(List<BuildingType> buildingTypeList) {
        this.buildingTypeList = buildingTypeList;
    }

    /**
     * Retrieves the details of a specified building type.
     *
     * @param buildingType The building type to find details for.
     * @return The details of the building type if found, null otherwise.
     */
    public BuildingType getBuildingTypeDetails(BuildingType buildingType) {
        Optional<BuildingType> result = buildingTypeList.stream()
                .filter(bt -> bt.equals(buildingType))
                .findFirst();
        return result.orElse(null);
    }

    /**
     * Retrieves all building types in the repository.
     *
     * @return A list of all building types.
     */
    public List<BuildingType> getAllBuildingTypes() {
        return buildingTypeList;
    }
}

