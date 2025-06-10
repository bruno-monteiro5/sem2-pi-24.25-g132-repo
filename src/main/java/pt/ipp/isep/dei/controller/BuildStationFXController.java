package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.StationType;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

public class BuildStationFXController {

    @FXML private ComboBox<StationType> typeComboBox;
    @FXML private ComboBox<String> directionComboBox;
    @FXML private TextField nameField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private Label messageLabel;

    private final BuildStationController controller = new BuildStationController();

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll(StationType.values());
        directionComboBox.getItems().addAll("NW", "NE", "SE", "SW");
        directionComboBox.setDisable(true);

        typeComboBox.setOnAction(e -> {
            StationType selected = typeComboBox.getValue();
            directionComboBox.setDisable(selected != StationType.STATION);
        });
    }

    @FXML
    public void handleCreateStation() {
        messageLabel.setText("");

        try {
            StationType type = typeComboBox.getValue();
            if (type == null) throw new IllegalArgumentException("Select a station type.");

            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());

            Position pos = new Position(x, y);
            String name = nameField.getText().trim();
            if (name.isEmpty()) name = controller.getSuggestedName(pos, type);

            String dir = directionComboBox.getValue();
            if (type == StationType.STATION && dir == null) {
                throw new IllegalArgumentException("Select a direction for STATION.");
            }

            boolean created = controller.createStation(pos, type, name, dir);
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText(created ? "Station created!" : "Failed to create station.");

        } catch (NumberFormatException e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Invalid coordinates.");
        } catch (IllegalArgumentException e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText(e.getMessage());
        } catch (Exception e) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Railroad Simulator - Main Menu", 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not return to Main Menu.");
            alert.showAndWait();
        }
    }
}