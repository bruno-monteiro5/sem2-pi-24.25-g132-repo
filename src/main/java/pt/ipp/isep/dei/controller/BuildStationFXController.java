package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.domain.StationType;
import pt.ipp.isep.dei.ui.MainFX;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX controller responsible for the "Build Station" interface.
 * <p>
 * Handles user input, validation, and interaction with the BuildStationController.
 */
public class BuildStationFXController {

    /** ComboBox for selecting the type of station. */
    @FXML private ComboBox<StationType> typeComboBox;

    /** ComboBox for selecting the direction (only applies to STATION). */
    @FXML private ComboBox<String> directionComboBox;

    /** TextField for entering the name of the station. */
    @FXML private TextField nameField;

    /** TextField for entering the X coordinate. */
    @FXML private TextField xField;

    /** TextField for entering the Y coordinate. */
    @FXML private TextField yField;

    /** Label for showing status messages to the user. */
    @FXML private Label messageLabel;

    /** Controller that manages the domain logic for station creation. */
    private final BuildStationController controller = new BuildStationController();

    /**
     * Initializes the JavaFX interface. Sets up combo box values and behavior.
     */
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

    /**
     * Handles the user action of creating a station.
     * Validates input and interacts with the controller.
     */
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
            if (created) {
                messageLabel.setText("Station created!");

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> handleBack());
                pause.play();

            } else {
                messageLabel.setText("Failed to create station.");
            }

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

    /**
     * Returns to the main menu screen.
     */
    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
