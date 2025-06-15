package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AnnualFinancialReportTest {

    /**
     * Tests the constructor and getter methods with valid values.
     * Ensures that all values are stored and returned correctly.
     */
    @Test
    void testConstructorAndGetters() {
        // Arrange
        int year = 2023;
        double revenue = 100000.0;
        double trackCost = 20000.0;
        double trainCost = 15000.0;
        double fuelCost = 10000.0;

        AnnualFinancialResults report = new AnnualFinancialResults(
                year, revenue, trackCost, trainCost, fuelCost
        );

        assertEquals(year, report.getYear(), "Year should match the constructor value");
        assertEquals(revenue, report.getTotalRevenue(), 0.001, "Revenue should match the constructor value");
        assertEquals(trackCost, report.getTrackMaintenanceCost(), 0.001, "Track cost should match the constructor value");
        assertEquals(trainCost, report.getTrainMaintenanceCost(), 0.001, "Train cost should match the constructor value");
        assertEquals(fuelCost, report.getFuelCost(), 0.001, "Fuel cost should match the constructor value");
    }

    /**
     * Tests the profit calculation with positive revenue and costs.
     * Ensures the profit is calculated correctly (revenue - total costs).
     */
    @Test
    void testProfitCalculation_PositiveProfit() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 150000.0, 30000.0, 20000.0, 10000.0
        );

        double profit = report.getProfit();

        assertEquals(90000.0, profit, 0.001, "Profit should be revenue minus all costs");
    }

    /**
     * Tests the profit calculation when costs exceed revenue.
     * Ensures the profit is negative in this case.
     */
    @Test
    void testProfitCalculation_NegativeProfit() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 50000.0, 30000.0, 25000.0, 15000.0
        );

        assertEquals(-20000.0, report.getProfit(), 0.001,
                "Profit should be negative when costs exceed revenue");
    }

    /**
     * Tests the profit calculation with zero values.
     * Ensures the profit is zero when all values are zero.
     */
    @Test
    void testProfitCalculation_ZeroValues() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 0.0, 0.0, 0.0, 0.0
        );

        assertEquals(0.0, report.getProfit(), 0.0,
                "Profit should be zero when all values are zero");
    }

    /**
     * Tests the toString() method.
     * Ensures the string representation contains all relevant information.
     */
    @Test
    void testToString() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );

        String result = report.toString();

        assertTrue(result.contains("2023"), "Year should be in toString output");
        assertTrue(result.contains("100000.00"), "Revenue should be in toString output");
        assertTrue(result.contains("Track Maintenance: 20000.00"),
                "Track cost should be in toString output");
        assertTrue(result.contains("Total Profit: 55000.00"),
                "Calculated profit should be in toString output");
    }

    /**
     * Tests the equals() method with two identical reports.
     * Ensures they are considered equal.
     */
    @Test
    void testEquals_SameValues() {
        AnnualFinancialResults report1 = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );
        AnnualFinancialResults report2 = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );

        assertEquals(report1, report2,
                "Reports with same values should be equal");
    }

    /**
     * Tests the equals() method with different reports.
     * Ensures they are not considered equal.
     */
    @Test
    void testEquals_DifferentValues() {
        AnnualFinancialResults report1 = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );
        AnnualFinancialResults report2 = new AnnualFinancialResults(
                2024, 100000.0, 20000.0, 15000.0, 10000.0
        );

        assertNotEquals(report1, report2,
                "Reports with different years should not be equal");
    }

    /**
     * Tests the equals() method with null.
     * Ensures the result is false.
     */
    @Test
    void testEquals_Null() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );

        assertNotEquals(null, report,
                "Report should not be equal to null");
    }

    /**
     * Tests the equals() method with different class.
     * Ensures the result is false.
     */
    @Test
    void testEquals_DifferentClass() {
        AnnualFinancialResults report = new AnnualFinancialResults(
                2023, 100000.0, 20000.0, 15000.0, 10000.0
        );

        assertNotEquals("Not a report", report,
                "Report should not be equal to different class");
    }
}