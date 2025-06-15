package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.File;
import java.io.IOException;

public class SelectScenarioFXController {

    @FXML
    private Label feedbackLabel;

    @FXML
    private void handleLoadScenario() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Scenario File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Scenario Files", "*.ser"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Scenario scenario = ScenarioFileHandler.loadScenarioFromFile(file.getAbsolutePath());
                Repositories.getInstance().getScenarioRepository().setActiveScenario(scenario);
                feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
                feedbackLabel.setText("✔ Scenario loaded: " + scenario.getName());
            } catch (IOException | ClassNotFoundException e) {
                feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
                feedbackLabel.setText("⚠ Error loading scenario: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleBack() {
        MainFX.goToEditorMenu();
    }
}

