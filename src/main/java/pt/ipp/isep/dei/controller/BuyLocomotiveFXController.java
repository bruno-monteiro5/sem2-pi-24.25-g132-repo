package pt.ipp.isep.dei.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Locomotive;
import pt.ipp.isep.dei.domain.LocomotiveType;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.fuelType;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.repository.ScenarioRepository;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyLocomotiveFXController {

    @FXML private ComboBox<fuelType> fuelTypeComboBox;
    @FXML private ComboBox<LocomotiveType> locomotiveComboBox;
    @FXML private TextArea locomotiveDetailsArea;
    @FXML private Label feedbackLabel;

    private final ScenarioRepository scenarioRepository = Repositories.getInstance().getScenarioRepository();

    @FXML
    public void initialize() {
        fuelTypeComboBox.setItems(FXCollections.observableArrayList(fuelType.values()));
        fuelTypeComboBox.setOnAction(e -> updateLocomotiveOptions());
        locomotiveComboBox.setOnAction(e -> showLocomotiveDetails());
    }

    private void updateLocomotiveOptions() {
        fuelType selectedFuel = fuelTypeComboBox.getValue();
        Scenario scenario = scenarioRepository.getActiveScenario();

        if (selectedFuel != null && scenario != null) {
            int currentYear = scenario.getPeriodTime().getStartYear();

            List<LocomotiveType> filtered = Arrays.stream(LocomotiveType.values())
                    .filter(type -> type.getFuelType().equalsIgnoreCase(selectedFuel.name()))
                    .filter(type -> currentYear >= type.getStartYear() && currentYear <= type.getEndYear())
                    .collect(Collectors.toList());

            locomotiveComboBox.setItems(FXCollections.observableArrayList(filtered));
            locomotiveDetailsArea.clear();
            feedbackLabel.setText("");
        }
    }

    private void showLocomotiveDetails() {
        LocomotiveType selected = locomotiveComboBox.getValue();
        if (selected != null) {
            StringBuilder details = new StringBuilder();
            details.append("Name: ").append(selected.getName()).append("\n")
                    .append("Start Year: ").append(selected.getStartYear()).append("\n")
                    .append("End Year: ").append(selected.getEndYear()).append("\n")
                    .append("Fuel Type: ").append(selected.getFuelType()).append("\n")
                    .append("Speed: ").append(selected.getTopSpeed()).append(" km/h\n")
                    .append("Price: $").append((int) selected.getPrice()).append("\n")
                    .append("Acceleration: ").append(selected.getAcceleration()).append("\n")
                    .append("Reliability: ").append(selected.getReliability());

            locomotiveDetailsArea.setText(details.toString());
        }
    }

    @FXML
    public void handleBuy() {
        LocomotiveType selected = locomotiveComboBox.getValue();
        if (selected == null) {
            feedbackLabel.setText("Please select a locomotive.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Scenario scenario = ApplicationSession.getInstance().getCurrentScenario();
        if (scenario == null) {
            feedbackLabel.setText("No active scenario.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Locomotive loco = new Locomotive(scenario.getLocomotives().size() + 1, selected);
        scenario.getLocomotives().add(loco);

        feedbackLabel.setText("Locomotive purchased successfully!");
        feedbackLabel.setStyle("-fx-text-fill: green;");
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

