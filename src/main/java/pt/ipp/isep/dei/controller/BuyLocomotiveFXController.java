package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.FuelType;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyLocomotiveFXController {

    @FXML private ComboBox<FuelType> fuelTypeComboBox;
    @FXML private ComboBox<LocomotiveType> locomotiveComboBox;
    @FXML private TextArea locomotiveDetailsArea;
    @FXML private Label feedbackLabel;

    @FXML
    public void initialize() {
        fuelTypeComboBox.getItems().setAll(FuelType.availableFuelTypes());
        fuelTypeComboBox.setOnAction(e -> updateLocomotiveOptions());
        locomotiveComboBox.setOnAction(e -> showLocomotiveDetails());
    }

    private void updateLocomotiveOptions() {
        FuelType selectedFuel = fuelTypeComboBox.getValue();
        if (selectedFuel == null) return;

        Scenario currentScenario = ApplicationSession.getInstance().getCurrentScenario();
        if (currentScenario == null) {
            feedbackLabel.setText("No active scenario.");
            return;
        }
        int currentYear = currentScenario.getPeriodTime().getStartYear();

        List<LocomotiveType> filtered = Arrays.stream(LocomotiveType.values())
                .filter(l -> l.getFuelType().getClass().equals(selectedFuel.getClass()) &&
                        l.getStartYear() <= currentYear &&
                        l.getEndYear() >= currentYear)
                .collect(Collectors.toList());

        locomotiveComboBox.getItems().setAll(filtered);
        locomotiveDetailsArea.clear();
        feedbackLabel.setText("");
    }

    private void showLocomotiveDetails() {
        LocomotiveType loco = locomotiveComboBox.getValue();
        if (loco == null) return;

        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(loco.getName()).append("\n")
                .append("Price: $").append(loco.getPrice()).append("\n")
                .append("Maintenance: $").append(loco.getMaintenance()).append("\n")
                .append("Fuel Cost: $").append(loco.getFuelCost()).append("\n")
                .append("Top Speed: ").append(loco.getTopSpeed()).append(" km/h\n")
                .append("Acceleration: ").append(loco.getAcceleration()).append("\n")
                .append("Reliability: ").append(loco.getReliability());

        locomotiveDetailsArea.setText(details.toString());
    }

    @FXML
    public void handleBuyLocomotive() {
        LocomotiveType selected = locomotiveComboBox.getValue();
        if (selected == null) {
            feedbackLabel.setText("Please select a locomotive.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();
        double price = selected.getPrice();

        if (!player.canAfford(price)) {
            feedbackLabel.setText("Not enough budget to buy this locomotive.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        player.deductFunds(price);

        feedbackLabel.setStyle("-fx-text-fill: green;");
        feedbackLabel.setText("Locomotive '" + selected.getName() + "' bought successfully!");
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
