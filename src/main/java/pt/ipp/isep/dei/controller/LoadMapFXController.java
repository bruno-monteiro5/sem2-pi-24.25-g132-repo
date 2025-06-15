package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.File;
import java.io.IOException;

public class LoadMapFXController {

    @FXML
    private TextField filenameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    public void handleLoad() {
        String filename = filenameField.getText().trim();

        if (filename.isEmpty()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Please enter a filename.");
            return;
        }

        File file = new File(filename);
        if (!file.exists()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("File not found: " + filename);
            return;
        }

        try {
            Map loadedMap = MapFileHandler.loadMapFromFile(filename);
            ApplicationSession.getInstance().setCurrentMap(loadedMap); // ✅ correto
            feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
            feedbackLabel.setText("✔ Map loaded: " + loadedMap.getName());
        } catch (IOException | ClassNotFoundException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Error loading map: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
