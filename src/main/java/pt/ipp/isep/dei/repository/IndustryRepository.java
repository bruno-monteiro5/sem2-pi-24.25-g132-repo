package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Industry;
import pt.ipp.isep.dei.domain.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing industries.
 *
 * <p>The {@code IndustryRepository} class provides methods to store, validate,
 * and manage industries. It allows checking the existence of industries by name,
 * validating the uniqueness of their positions, and storing them in a collection.</p>
 */
public class IndustryRepository {

    private List<Industry> industries = new ArrayList<>();

    /**
     * Checks whether an industry with the given name exists in the repository.
     *
     * @param name The name of the industry to check.
     * @return {@code true} if an industry with the given name exists; {@code false} otherwise.
     */
    public boolean industryExists(String name) {
        for (Industry i : industries) {
            if (i.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates whether a given position is already occupied by another industry.
     *
     * @param position The position to validate.
     * @return {@code true} if the position is occupied by an existing industry; {@code false} otherwise.
     */
    public boolean isPositionInvalid(Position position) {
        for (Industry i : industries) {
            if (i.getPosition().equals(position))
                return true;
        }
        return false;
    }

    /**
     * Saves the given industry to the repository.
     *
     * @param industry The {@code Industry} to be saved.
     */
    public void save(Industry industry) {
        industries.add(industry);
    }
}
