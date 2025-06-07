package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Industry;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing industries.
 *
 * The {@code IndustryRepository} class provides methods to store,
 * validate, and manage industries using object equality.
 */
public class IndustryRepository {

    private final List<Industry> industries = new ArrayList<>();

    /**
     * Checks whether a given industry already exists in the repository.
     *
     * @param industry The industry to check.
     * @return {@code true} if an identical industry exists; {@code false} otherwise.
     */
    public boolean contains(Industry industry) {
        return industries.contains(industry);
    }

    /**
     * Saves the given industry to the repository if it doesn't already exist.
     *
     * @param industry The {@code Industry} to be saved.
     * @throws IllegalArgumentException if the industry already exists.
     */
    public void save(Industry industry) {
        if (contains(industry)) {
            throw new IllegalArgumentException("Industry already exists.");
        }
        industries.add(industry);
    }

    /**
     * Retrieves all industries in the repository.
     *
     * @return List of all {@code Industry} instances.
     */
    public List<Industry> findAll() {
        return new ArrayList<>(industries);
    }
}
