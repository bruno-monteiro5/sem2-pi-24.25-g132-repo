package pt.ipp.isep.dei.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

/**
 * JavaFX controller for building a railway line between stations.
 * <p>
 * Handles user input, validation and interface feedback when configuring a new line.
 */
public class BuildRailwayLineFXController {

    /** Text field for entering the name of the line. */
    @FXML private TextField lineNameField;

    /** ComboBox for selecting the start station. */
    @FXML private ComboBox<String> startStationComboBox;

    /** ComboBox for selecting the end station. */
    @FXML private ComboBox<String> endStationComboBox;

    /** Text field for entering the total distance of the line. */
    @FXML private TextField distanceField;

    /** ComboBox for selecting intermediate stations. */
    @FXML private ComboBox<String> intermediateStationComboBox;

    /** ListView showing the list of selected intermediate stations. */
    @FXML private ListView<String> intermediateStationsListView;

    /** Label to show status messages to the user. */
    @FXML private Label statusLabel;

    /** Observable list of intermediate stations. */
    private final ObservableList<String> intermediateStations = FXCollections.observableArrayList();

    /**
     * Initializes the interface components.
     * Called automatically by JavaFX after loading the FXML.
     */
    @FXML
    public void initialize() {
        intermediateStationsListView.setItems(intermediateStations);
        loadStations();
    }

    /**
     * Loads the list of stations into the ComboBoxes.
     * <p>
     * This method is currently a placeholder and should be replaced by integration
     * with the domain layer (e.g., via controller and repository).
     */
    private void loadStations() {
        ObservableList<String> stations = FXCollections.observableArrayList();

        startStationComboBox.setItems(stations);
        endStationComboBox.setItems(stations);
        intermediateStationComboBox.setItems(stations);
    }

    /**
     * Handles the addition of an intermediate station to the list.
     *
     * @param actionEvent the triggered event (not used)
     */
    @FXML
    public void handleAddIntermediate(ActionEvent actionEvent) {
        String selectedStation = intermediateStationComboBox.getValue();

        if (selectedStation == null || selectedStation.isEmpty()) {
            showStatus("Please select a station to add.", "red");
            return;
        }

        if (intermediateStations.contains(selectedStation)) {
            showStatus("Station already added to the route.", "orange");
            return;
        }

        if (selectedStation.equals(startStationComboBox.getValue())) {
            showStatus("Cannot add start station as intermediate.", "orange");
            return;
        }

        if (selectedStation.equals(endStationComboBox.getValue())) {
            showStatus("Cannot add end station as intermediate.", "orange");
            return;
        }

        intermediateStations.add(selectedStation);
        intermediateStationComboBox.getSelectionModel().clearSelection();
        showStatus("Station added: " + selectedStation, "green");
    }

    /**
     * Clears all input fields and selections in the form.
     *
     * @param actionEvent the triggered event (can be null)
     */
    @FXML
    public void handleClearForm(ActionEvent actionEvent) {
        lineNameField.clear();
        startStationComboBox.getSelectionModel().clearSelection();
        endStationComboBox.getSelectionModel().clearSelection();
        distanceField.clear();
        intermediateStationComboBox.getSelectionModel().clearSelection();
        intermediateStations.clear();
        statusLabel.setText("");
    }

    /**
     * Handles the creation of the railway line after validating all input fields.
     *
     * @param actionEvent the triggered event
     */
    @FXML
    public void handleBuildLine(ActionEvent actionEvent) {
        if (lineNameField.getText().trim().isEmpty()) {
            showStatus("Please enter a line name.", "red");
            return;
        }

        if (startStationComboBox.getValue() == null) {
            showStatus("Please select a start station.", "red");
            return;
        }

        if (endStationComboBox.getValue() == null) {
            showStatus("Please select an end station.", "red");
            return;
        }

        if (startStationComboBox.getValue().equals(endStationComboBox.getValue())) {
            showStatus("Start and end stations cannot be the same.", "red");
            return;
        }

        try {
            double distance = Double.parseDouble(distanceField.getText());
            if (distance <= 0) {
                showStatus("Distance must be greater than zero.", "red");
                return;
            }

            // (Aqui faltaria integração com o domínio para criar efetivamente a linha)
            showStatus("Railway line created successfully!", "green");
            handleClearForm(null);
        } catch (NumberFormatException e) {
            showStatus("Invalid distance value. Please enter a valid number.", "red");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a status message to the user in the specified color.
     *
     * @param message the message to display
     * @param color   the CSS color (e.g., "red", "green")
     */
    private void showStatus(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-style: italic;");
    }
}
