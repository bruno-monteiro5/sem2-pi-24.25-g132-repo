package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

public class SaveMapFXController {

    @FXML
    private TextField filenameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label mapNameLabel;

    private final CreateMapController controller = new CreateMapController();

    @FXML
    public void initialize() {
        try {
            Map currentMap = controller.getMapRepository().getActiveMap();
            mapNameLabel.setText("Current Map: " + currentMap.getName());
            feedbackLabel.setText("");
        } catch (IllegalStateException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("⚠ You must create a map before saving.");
        }

    }

    @FXML
    public void handleSave() {
        String filename = filenameField.getText().trim();
        Map map = controller.getMapRepository().getActiveMap();

        if (map == null) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("No map to save. Please create a map first.");
            return;
        }

        if (filename.isEmpty()) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Please enter a filename.");
            return;
        }

        try {
            MapFileHandler.saveMapToFile(map, filename);
            feedbackLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
            feedbackLabel.setText("Map saved successfully.");
        } catch (IOException e) {
            feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            feedbackLabel.setText("Error saving the map: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
