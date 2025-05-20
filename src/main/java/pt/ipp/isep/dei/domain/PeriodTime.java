package pt.ipp.isep.dei.domain;

import java.io.Serializable;

/**
 * Represents a period of time defined by a start year and an end year.
 *
 * <p>The {@code PeriodTime} class ensures that a time period is valid by verifying
 * that the start year comes before or is equal to the end year. It provides methods
 * to retrieve the start and end years, as well as a validation method to check
 * the period's integrity.</p>
 *
 * <p>Instances of this class are immutable, meaning their state cannot be changed
 * after instantiation.</p>
 */
public class PeriodTime implements Serializable {

    private final int startYear;
    private final int endYear;


    /**
     * Constructs a {@code PeriodTime} instance with the specified start year and end year.
     *
     * @param startYear The year the period begins. Must be less than or equal to {@code endYear}.
     * @param endYear The year the period ends.
     */
    public PeriodTime(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Retrieves the start year of the time period.
     *
     * @return An integer representing the year the period starts.
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * Retrieves the end year of the time period.
     *
     * @return An integer representing the year the period ends.
     */
    public int getEndYear() {
        return endYear;
    }


    /**
     * Validates if the time period is valid.
     *
     * <p>A time period is considered valid if the start year is less than
     * or equal to the end year.</p>
     *
     * @return {@code true} if the period is valid, {@code false} otherwise.
     */

    public boolean isValid() {
        return startYear <= endYear;
    }
}
