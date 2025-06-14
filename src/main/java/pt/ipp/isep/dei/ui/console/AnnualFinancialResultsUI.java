/**
package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AnnualFinancialResultsController;
import pt.ipp.isep.dei.domain.AnnualFinancialResults;

public class AnnualFinancialResultsUI implements Runnable {
    private final AnnualFinancialResultsController controller = new AnnualFinancialResultsController();

    @Override
    public void run() {
        AnnualFinancialResults report = controller.generateAnnualReport();
        System.out.println("\n" + report.toString());

        // Opcional: Salvar relatório em arquivo
        saveReportToFile(report);
    }

    private void saveReportToFile(AnnualFinancialResults report) {
        // Implementação para persistência (opcional)
    }
}
*/