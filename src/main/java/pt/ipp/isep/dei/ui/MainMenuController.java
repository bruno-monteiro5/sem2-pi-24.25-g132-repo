package pt.ipp.isep.dei.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Scenario;

import java.io.IOException;

public class MainMenuController {

    @FXML private Label mapLabel;
    @FXML private Label scenarioLabel;
    @FXML private Label yearLabel;
    @FXML private Label budgetLabel;
    @FXML private Label scaleLabel;
    @FXML private Pane mapArea;

    @FXML
    public void initialize() {
        Map map = ApplicationSession.getInstance().getCurrentMap();
        Scenario scenario = ApplicationSession.getInstance().getCurrentScenario();

        if (map != null) {
            mapLabel.setText("Map: " + map.getName());
            scaleLabel.setText("Scale: " + map.getScale());
        }

        if (scenario != null && scenario.getPeriodTime() != null) {
            scenarioLabel.setText("Scenario: " + scenario.getName());
            yearLabel.setText("Year: " + scenario.getPeriodTime().getStartYear());
            // budgetLabel.setText("Budget: $" + scenario.getPeriodTime().getInitialBudget());
        }
    }

    @FXML
    public void handleBuildStation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BuildStation.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Build Station", 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load Build Station screen.");
        }
    }

    @FXML
    public void handleUpgradeStation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UpgradeStation.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Upgrade Station", 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load Upgrade Station screen.");
        }
    }

    @FXML
    public void handleStationDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StationDetails.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Station Details", 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load Station Details screen.");
        }
    }


    @FXML
    public void handleBuildRailwayLine() {
    }

    @FXML
    public void handleBuyLocomotive() {
    }

    @FXML
    public void handleAssignTrainToARoute() {
    }

    @FXML
    public void handleListAllTrains() {
    }

    @FXML
    public void handleStartSimulation() {
    }

    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Login - Railroad Simulator", 400, 300);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading Login screen.");
            alert.showAndWait();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
