package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.List;

public class UpgradeStationFXController {

    @FXML private ComboBox<Station> stationComboBox;
    @FXML private ComboBox<BuildingType> upgradeComboBox;
    @FXML private TextArea upgradeDetailsArea;
    @FXML private Label feedbackLabel;

    private final UpgradeStationController controller = new UpgradeStationController();

    @FXML
    public void initialize() {
        loadStations();
        stationComboBox.setOnAction(e -> loadUpgrades());
        upgradeComboBox.setOnAction(e -> showUpgradeDetails());
    }

    private void loadStations() {
        Object[] mapAndStations = controller.loadMapAndStations();
        if (mapAndStations != null) {
            List<Station> stations = (List<Station>) mapAndStations[1];
            stationComboBox.getItems().setAll(stations);
        }
    }

    private void loadUpgrades() {
        Station selected = stationComboBox.getValue();
        upgradeComboBox.getItems().clear();
        upgradeDetailsArea.clear();

        if (selected != null) {
            List<BuildingType> upgrades = controller.getAvailableUpgrades(selected);
            upgradeComboBox.getItems().setAll(upgrades);
        }
    }

    private void showUpgradeDetails() {
        BuildingType upgrade = upgradeComboBox.getValue();
        if (upgrade != null) {
            String details = String.format(
                    "Upgrade: %s\nCost: %.2f\nEfficiency Bonus: +%.0f%%\nRevenue Multiplier: x%.2f\nExtra Storage: +%d units",
                    upgrade.name(),
                    upgrade.getCost(),
                    upgrade.getEfficiencyBonus() * 100,
                    upgrade.getRevenueMultiplier(),
                    upgrade.getExtraStorageCapacity()
            );
            upgradeDetailsArea.setText(details);
        } else {
            upgradeDetailsArea.clear();
        }
    }

    @FXML
    public void handleUpgrade() {
        Station station = stationComboBox.getValue();
        BuildingType upgrade = upgradeComboBox.getValue();

        if (station == null || upgrade == null) {
            feedbackLabel.setText("Please select both a station and an upgrade.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean success = controller.executeUpgrade(station, upgrade);
        if (success) {
            feedbackLabel.setStyle("-fx-text-fill: green;");
            feedbackLabel.setText("Upgrade applied successfully.");
            loadUpgrades();
        } else {
            feedbackLabel.setStyle("-fx-text-fill: red;");
            feedbackLabel.setText("Upgrade failed. Not enough funds or conflict.");
        }
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
