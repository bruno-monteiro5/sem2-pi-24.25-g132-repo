/**
package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.MainFX;

public class CreateMapFXController {

    @FXML private TextField mapNameField;
    @FXML private TextField widthField;
    @FXML private TextField heightField;
    @FXML private Label feedbackLabel;

    private final CreateMapController ctrl = new CreateMapController();

    @FXML
    public void handleCreateMap() {
        String name = mapNameField.getText().trim();
        String widthStr = widthField.getText().trim();
        String heightStr = heightField.getText().trim();

        try {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);

            Map newMap = new Map(name, width, height);
            Map saved = ctrl.saveMap(newMap);

            if (saved != null) {
                feedbackLabel.setText(" Map created successfully!");
                feedbackLabel.setStyle("-fx-text-fill: green;");
            } else {
                feedbackLabel.setText(" Failed to create map.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText(" Width and height must be integers.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
*/