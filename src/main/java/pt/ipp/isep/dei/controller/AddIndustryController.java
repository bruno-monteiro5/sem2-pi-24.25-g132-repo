package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.IndustryData;
import pt.ipp.isep.dei.domain.IndustryType;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.IndustryRepository;
import pt.ipp.isep.dei.repository.Repositories;


public class AddIndustryController {

    private IndustryRepository repository;

    public AddIndustryController() {
        this.repository = Repositories.getInstance().getIndustryRepository();
    }

    public Industry addIndustry(String name, Position position, IndustryType type) {
        if (repository.industryExists(name)) {
            throw new IllegalArgumentException("An industry with that name already exists.");
        }

        if (repository.isPositionInvalid(position)) {
            throw new IllegalArgumentException("Position is already occupied by another industry.");
        }

        Industry industry = new Industry(name, position, type);
        repository.save(industry);
        return industry;
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
     *   <li>An industry with the same name already exists.</li>
     *   <li>The position is already occupied.</li>
     * </ul>
     */
    public Industry addIndustry(IndustryData data, Position position) {
        if (repository.industryExists(data.getName())) {
            throw new IllegalArgumentException("An industry with that name already exists.");
        }

        if (repository.isPositionInvalid(position)) {
            throw new IllegalArgumentException("Position is already occupied by another industry.");
        }

        Industry industry = new Industry(data, position);
        repository.save(industry);
        return industry;
    }
}
