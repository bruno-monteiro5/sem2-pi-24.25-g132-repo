package pt.ipp.isep.dei.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaFX controller responsible for adding a new industry to the current map.
 * <p>
 * Allows the user to choose an industry type, a specific industry, define a position,
 * and confirm the addition.
 */
public class AddIndustryFXController {

    /**
     * ComboBox for selecting the industry type (PRIMARY, TRANSFORMING, etc.).
     */
    @FXML
    private ComboBox<IndustryType> typeComboBox;

    /**
     * ComboBox for selecting a specific industry from the selected type.
     */
    @FXML
    private ComboBox<IndustryData> industryComboBox;

    /**
     * TextField for the X coordinate.
     */
    @FXML
    private TextField xField;

    /**
     * TextField for the Y coordinate.
     */
    @FXML
    private TextField yField;

    /**
     * Label that shows a summary of the selected industry.
     */
    @FXML
    private Label summaryLabel;

    /**
     * Label used to give feedback to the user (success or error).
     */
    @FXML
    private Label feedbackLabel;

    /**
     * Controller that handles business logic for adding an industry.
     */
    private final AddIndustryController ctrl = new AddIndustryController();

    /**
     * Initializes the controller.
     * <p>
     * Sets up listeners and loads available industry types.
     */
    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(IndustryType.values()));
        typeComboBox.setOnAction(e -> updateIndustryOptions());
        industryComboBox.setOnAction(e -> updateSummary());
    }

    /**
     * Updates the list of available industries based on the selected type.
     */
    private void updateIndustryOptions() {
        IndustryType selectedType = typeComboBox.getValue();
        if (selectedType != null) {
            List<IndustryData> filtered = Arrays.stream(IndustryData.values())
                    .filter(data -> data.getType() == selectedType)
                    .collect(Collectors.toList());
            industryComboBox.setItems(FXCollections.observableArrayList(filtered));
            industryComboBox.getSelectionModel().clearSelection();
            summaryLabel.setText("");
        }
    }

    /**
     * Updates the summary label with details of the selected industry.
     */
    private void updateSummary() {
        IndustryData selected = industryComboBox.getValue();
        if (selected != null) {
            summaryLabel.setText("Name: " + selected.getName() +
                    " | Type: " + selected.getType().name() +
                    " | Year: " + selected.getStartYear());
        } else {
            summaryLabel.setText("");
        }
    }

    /**
     * Handles the confirmation of adding an industry to the map.
     * <p>
     * Validates input, creates the industry, and updates the map.
     */
    @FXML
    public void handleAddIndustry() {
        try {
            IndustryData data = industryComboBox.getValue();
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());
            Position pos = new Position(x, y);

            Industry added = ctrl.addIndustry(data, pos);
            ApplicationSession.getInstance().getCurrentMap().addIndustry(added);

            feedbackLabel.setText("Industry added successfully!");
            feedbackLabel.setStyle("-fx-text-fill: green;");

        } catch (NumberFormatException e) {
            feedbackLabel.setText("Coordinates must be integers.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Handles the "Back" button, returning to the editor menu.
     */
    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
