package pt.ipp.isep.dei.domain;

public class AnnualFinancialResults {
    private final int year;
    private final double totalRevenue;
    private final double trackMaintenanceCost;
    private final double trainMaintenanceCost;
    private final double fuelCost;
    private final double profit;

    public AnnualFinancialResults(int year, double totalRevenue, double trackMaintenanceCost,
                                 double trainMaintenanceCost, double fuelCost) {
        this.year = year;
        this.totalRevenue = totalRevenue;
        this.trackMaintenanceCost = trackMaintenanceCost;
        this.trainMaintenanceCost = trainMaintenanceCost;
        this.fuelCost = fuelCost;
        this.profit = totalRevenue - (trackMaintenanceCost + trainMaintenanceCost + fuelCost);
    }


    public int getYear() { return year; }
    public double getTotalRevenue() { return totalRevenue; }
    public double getTrackMaintenanceCost() { return trackMaintenanceCost; }
    public double getTrainMaintenanceCost() { return trainMaintenanceCost; }
    public double getFuelCost() { return fuelCost; }
    public double getProfit() { return profit; }

    @Override
    public String toString() {
        return String.format(
                "=== Financial Year %d ===\n" +
                        "Revenue: %.2f\n" +
                        "Expenses:\n" +
                        "  - Track Maintenance: %.2f\n" +
                        "  - Train Maintenance: %.2f\n" +
                        "  - Fuel: %.2f\n" +
                        "Total Profit: %.2f",
                year, totalRevenue, trackMaintenanceCost,
                trainMaintenanceCost, fuelCost, profit
        );
    }
}