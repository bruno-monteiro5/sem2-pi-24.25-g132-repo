package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Building objects.
 */
public class BuildingRepository {
    private final List<Building> buildingList;

    /**
     * Initializes the repository with a given list of buildings.
     * @param buildingList A list of all buildings in the system.
     */
    public BuildingRepository(List<Building> buildingList) {
        // Create a defensive copy to avoid outside modifications
        this.buildingList = new ArrayList<>(buildingList);
    }

    /**
     * Retrieves the details of a specified building.
     *
     * @param building The building to find details for.
     * @return An Optional containing the building if found, empty otherwise.
     */
    public Optional<Building> getBuildingDetails(Building building) {
        return buildingList.stream()
                .filter(b -> b.equals(building))
                .findFirst();
    }

    /**
     * Retrieves all buildings in the repository.
     *
     * @return An unmodifiable list of all buildings.
     */
    public List<Building> getAllBuildings() {
        return List.copyOf(buildingList);
    }

    /**
     * Adds a new building to the repository.
     *
     * @param building The building to be added.
     */
    public void addBuilding(Building building) {
        buildingList.add(building);
    }

    /**
     * Removes an existing building from the repository.
     *
     * @param building The building to be removed.
     * @return true if the building was successfully removed, false otherwise.
     */
    public boolean removeBuilding(Building building) {
        return buildingList.remove(building);
    }

    /**
     * Updates an existing building in the repository.
     * If the building does not exist, it will be added.
     *
     * @param building The building to update.
     */
    public void updateBuilding(Building building) {
        // Check if the building exists
        int index = buildingList.indexOf(building);
        if (index != -1) {
            // Update the existing building
            buildingList.set(index, building);
        } else {
            // Add as a new building
            addBuilding(building);
        }
    }
}
