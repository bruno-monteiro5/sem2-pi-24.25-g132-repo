package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryData;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.IndustryRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;

public class AddIndustryController {

    private final IndustryRepository repository;

    public AddIndustryController() {
        this.repository = Repositories.getInstance().getIndustryRepository();
    }

    /**
     * Adds a new industry using predefined {@link IndustryData}.
     *
     * @param data The industry data enum selected.
     * @param position The map position for the industry.
     * @return The created {@link Industry} instance.
     *
     * @throws IllegalArgumentException if:
     * <ul>
     *   <li>An identical industry already exists (same name, type and position).</li>
     * </ul>
     */
    public Industry addIndustry(IndustryData data, Position position) {
        Industry newIndustry = new Industry(data, position);

        if (repository.contains(newIndustry)) {
            throw new IllegalArgumentException("This industry already exists at that position.");
        }

        repository.save(newIndustry);
        return newIndustry;
    }

    public List<Industry> getAllIndustries() {
        return repository.findAll();
    }

}
