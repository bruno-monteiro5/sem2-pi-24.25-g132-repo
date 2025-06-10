package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

public class CreateMapFXController {

    @FXML private TextField mapNameField;
    @FXML private TextField widthField;
    @FXML private TextField heightField;
    @FXML private TextField scaleField;
    @FXML private Label feedbackLabel;

    private final CreateMapController ctrl = new CreateMapController();

    @FXML
    public void handleCreateMap() {
        String name = mapNameField.getText().trim();
        String widthStr = widthField.getText().trim();
        String heightStr = heightField.getText().trim();
        String scaleStr = scaleField.getText().trim();

        try {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            int scale = Integer.parseInt(scaleStr);

            Map newMap = new Map(name, width, height, scale);
            Map saved = ctrl.saveMap(newMap);

            if (saved != null) {
                ApplicationSession.getInstance().setCurrentMap(saved);

                feedbackLabel.setText(" Map created successfully!");
                feedbackLabel.setStyle("-fx-text-fill: green;");
            } else {
                feedbackLabel.setText(" Failed to create map.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Width, height, and scale must be integers.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
