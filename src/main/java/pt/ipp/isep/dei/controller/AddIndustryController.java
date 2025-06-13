package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryData;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.IndustryRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

/**
 * Controller responsible for adding new industries to the map.
 * <p>
 * Uses predefined {@link IndustryData} values and ensures no duplicates are inserted.
 * Interacts with the {@link IndustryRepository}.
 */
public class AddIndustryController {

    /**
     * The repository that manages industry entities.
     */
    private final IndustryRepository repository;

    /**
     * Creates a new instance of the controller and accesses the industry repository.
     */
    public AddIndustryController() {
        this.repository = Repositories.getInstance().getIndustryRepository();
    }

    /**
     * Adds a new industry using predefined {@link IndustryData}.
     *
     * @param data     the selected industry data definition
     * @param position the map position where the industry will be placed
     * @return the newly created {@link Industry} instance
     * @throws IllegalArgumentException if an identical industry already exists at the same position
     */
    public Industry addIndustry(IndustryData data, Position position) {
        Industry newIndustry = new Industry(data, position);

        if (repository.contains(newIndustry)) {
            throw new IllegalArgumentException("This industry already exists at that position.");
        }

        repository.save(newIndustry);
        return newIndustry;
    }

    /**
     * Retrieves all industries currently stored in the repository.
     *
     * @return a list of all {@link Industry} objects
     */
    public List<Industry> getAllIndustries() {
        return repository.findAll();
    }

}
