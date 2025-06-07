package pt.ipp.isep.dei.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Scenario;

public class MainMenuController {

    @FXML private Label mapLabel;
    @FXML private Label scenarioLabel;
    @FXML private Label yearLabel;
    @FXML private Label budgetLabel;
    @FXML private Pane mapArea;

    @FXML
    public void initialize() {
        Map map = ApplicationSession.getInstance().getCurrentMap();
        Scenario scenario = ApplicationSession.getInstance().getCurrentScenario();

        if (map != null) {
            mapLabel.setText("Map: " + map.getName());
        }

        if (scenario != null && scenario.getPeriodTime() != null) {
            scenarioLabel.setText("Scenario: " + scenario.getName());
            yearLabel.setText("Year: " + scenario.getPeriodTime().getStartYear());

            // ⚠️ Budget is not yet implemented
            // budgetLabel.setText("Budget: $" + scenario.getPeriodTime().getInitialBudget());
        }
    }

    @FXML
    public void handleBuildStation() {
    }

    @FXML
    public void handleUpgradeStation() {
    }

    @FXML
    public void handleStationDetails() {
    }

    @FXML
    public void handleStartSimulation() {
    }
}
