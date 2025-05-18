package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.IndustryTypes;
import pt.ipp.isep.dei.repository.IndustryRepository;
import pt.ipp.isep.dei.repository.Repositories;


public class AddIndustryController {

    private IndustryRepository repository;

    /**
     * Initializes an instance of {@code AddIndustryController}.
     * <p>
     * Fetches the {@link IndustryRepository} from the {@link Repositories} singleton
     * to manage industry-related operations.
     */
    public AddIndustryController() {
        this.repository = Repositories.getInstance().getIndustryRepository();
    }

    /**
     * Adds a new industry to the system.
     * <p>
     * The method ensures that the industry being added does not violate the following constraints:
     * <ul>
     *   <li>There is no existing industry with the same name.</li>
     *   <li>The specified position is not occupied by another industry.</li>
     * </ul>
     * After validation, the industry is created and saved in the {@link IndustryRepository}.
     *
     * @param name The name of the industry to add.
     * @param position The XY position of the industry on the map.
     * @param type The type of the industry (Primary, Transforming, or Mixed).
     * @return The newly created {@link Industry} object.
     *
     * @throws IllegalArgumentException if:
     * <ul>
     *   <li>An industry with the provided name already exists.</li>
     *   <li>The specified position is already occupied.</li>
     * </ul>
     */

    public Industry addIndustry(String name, Position position, IndustryTypes.IndustryType type) {
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




}
