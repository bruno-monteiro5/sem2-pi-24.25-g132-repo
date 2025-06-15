package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.File;
import java.io.IOException;

public class LoadScenarioFXController {

    @FXML
    private TextField filenameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private void handleLoad() {
        String filename = filenameField.getText().trim();

        if (filename.isEmpty()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Please enter a filename.");
            return;
        }

        File file = new File("scenarios/" + filename);
        if (!file.exists()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("File not found: " + filename);
            return;
        }

        try {
            Scenario scenario = ScenarioFileHandler.loadScenarioFromFile(file.getPath());

            ApplicationSession.getInstance().setCurrentScenario(scenario);

            Repositories.getInstance().getScenarioRepository().add(scenario);

            feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
            feedbackLabel.setText("✔ Scenario loaded: " + scenario.getName());
        } catch (IOException | ClassNotFoundException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Error loading scenario: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        MainFX.goToEditorMenu();
    }
}
