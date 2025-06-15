/**
package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AnnualFinancialResultsController;
import pt.ipp.isep.dei.domain.AnnualFinancialResults;

public class AnnualFinancialResultsUI {

    private final AnnualFinancialResultsController controller;

    public AnnualFinancialResultsUI() {
        this.controller = new AnnualFinancialResultsController();
    }

    public void showAnnualReport() {
        System.out.println("\n=== ANNUAL FINANCIAL REPORT ===");

        AnnualFinancialResults report = controller.generateAnnualReport();

        System.out.println(report);

        System.out.println("===============================");

        showReportAsTable(report);
    }

    private void showReportAsTable(AnnualFinancialResults report) {
        System.out.println("\n--- Detailed View ---");
        System.out.println("+-----------------------------+------------+");
        System.out.println("| Category                    | Amount     |");
        System.out.println("+-----------------------------+------------+");
        System.out.printf("| Year                        | %-10d |\n", report.getYear());
        System.out.printf("| Total Revenue               | €%-9.2f |\n", report.getTotalRevenue());
        System.out.printf("| Track Maintenance Costs    | €%-9.2f |\n", report.getTrackMaintenanceCost());
        System.out.printf("| Train Maintenance Costs    | €%-9.2f |\n", report.getTrainMaintenanceCost());
        System.out.printf("| Fuel Costs                 | €%-9.2f |\n", report.getFuelCost());
        System.out.println("+-----------------------------+------------+");
        System.out.printf("| TOTAL PROFIT               | €%-9.2f |\n", report.getProfit());
        System.out.println("+-----------------------------+------------+");
    }

    public static void main(String[] args) {
        AnnualFinancialResultsUI ui = new AnnualFinancialResultsUI();
        ui.showAnnualReport();
    }
}
 */