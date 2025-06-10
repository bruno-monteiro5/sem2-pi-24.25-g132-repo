package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

public class CreateCityFXController {

    @FXML private TextField nameField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField blocksField;
    @FXML private RadioButton autoRadio;
    @FXML private RadioButton manualRadio;
    @FXML private Label feedbackLabel;

    private final CreateCityController ctrl = new CreateCityController();

    /**
     * Initializes the controller. Sets up the ToggleGroup for the mode selection.
     */
    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        autoRadio.setToggleGroup(group);
        manualRadio.setToggleGroup(group);
        autoRadio.setSelected(true); // Default to automatic
    }

    /**
     * Handles the creation of a city.
     */
    @FXML
    public void handleCreateCity() {
        String name = nameField.getText().trim();
        String xStr = xField.getText().trim();
        String yStr = yField.getText().trim();
        String blocksStr = blocksField.getText().trim();

        try {
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);
            int blocks = Integer.parseInt(blocksStr);
            boolean manual = manualRadio.isSelected();
            Position pos = new Position(x, y);

            City city = ctrl.createCity(name, pos, blocks, manual, null);
            ApplicationSession.getInstance().getCurrentMap().addCity(city);

            feedbackLabel.setText("City created successfully!");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            feedbackLabel.setText("X, Y and Blocks must be integers.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Returns to the editor main menu.
     */
    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
