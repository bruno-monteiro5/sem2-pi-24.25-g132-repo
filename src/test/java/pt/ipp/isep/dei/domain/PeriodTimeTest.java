package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PeriodTime}.
 * Ensures 100% test coverage of all methods.
 */
class PeriodTimeTest {

    /**
     * Tests the constructor, verifying that it correctly assigns startYear and endYear.
     */
    @Test
    void testConstructor() {
        PeriodTime periodTime = new PeriodTime(2000, 2023);

        assertEquals(2000, periodTime.getStartYear());
        assertEquals(2023, periodTime.getEndYear());
    }

    /**
     * Tests the {@link PeriodTime#isValid()} method when the start year is less than or equal to the end year (valid case).
     */
    @Test
    void testIsValidWithValidPeriod() {
        PeriodTime periodTime = new PeriodTime(2000, 2023);

        assertTrue(periodTime.isValid());
    }

    /**
     * Tests the {@link PeriodTime#isValid()} method when the start year is greater than the end year (invalid case).
     */
    @Test
    void testIsValidWithInvalidPeriod() {
        PeriodTime periodTime = new PeriodTime(2025, 2020);

        assertFalse(periodTime.isValid());
    }

    /**
     * Tests the {@link PeriodTime#getStartYear()} method, ensuring it returns the correct value.
     */
    @Test
    void testGetStartYear() {
        PeriodTime periodTime = new PeriodTime(1990, 2000);

        assertEquals(1990, periodTime.getStartYear());
    }

    /**
     * Tests the {@link PeriodTime#getEndYear()} method, ensuring it returns the correct value.
     */
    @Test
    void testGetEndYear() {
        PeriodTime periodTime = new PeriodTime(1990, 2000);

        assertEquals(2000, periodTime.getEndYear());
    }
}

