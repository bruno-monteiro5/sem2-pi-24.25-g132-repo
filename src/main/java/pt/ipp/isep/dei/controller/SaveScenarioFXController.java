package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

public class SaveScenarioFXController {

    @FXML
    private TextField filenameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label scenarioNameLabel;

    @FXML
    public void initialize() {
        try {
            Scenario scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
            scenarioNameLabel.setText("Current Scenario: " + scenario.getName());
            feedbackLabel.setText("");
        } catch (IllegalStateException e) {
            scenarioNameLabel.setText("No scenario loaded.");
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("⚠ You must create a scenario before saving.");
        }
    }

    @FXML
    public void handleSave() {
        String filename = filenameField.getText().trim();
        Scenario scenario;

        try {
            scenario = Repositories.getInstance().getScenarioRepository().getActiveScenario();
        } catch (IllegalStateException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("No scenario to save. Please create a scenario first.");
            return;
        }

        if (filename.isEmpty()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Please enter a filename.");
            return;
        }

        try {
            ScenarioFileHandler.saveScenarioToFile(scenario, filename);
            feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
            feedbackLabel.setText("✔ Scenario saved successfully.");
        } catch (IOException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Error saving scenario: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
