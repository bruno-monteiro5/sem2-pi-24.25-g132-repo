package pt.ipp.isep.dei.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

public class AddIndustryFXController {

    @FXML
    private ComboBox<IndustryData> industryComboBox;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private Label feedbackLabel;

    private final AddIndustryController controller = new AddIndustryController();

    @FXML
    public void initialize() {
        industryComboBox.setItems(FXCollections.observableArrayList(IndustryData.values()));
    }

    @FXML
    public void handleAddIndustry() {
        IndustryData selected = industryComboBox.getValue();
        String xStr = xField.getText().trim();
        String yStr = yField.getText().trim();

        if (selected == null || xStr.isEmpty() || yStr.isEmpty()) {
            setFeedback("Please fill all fields.", false);
            return;
        }

        try {
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);

            Map currentMap = ApplicationSession.getInstance().getCurrentMap();
            if (currentMap == null) {
                setFeedback("No map loaded in session.", false);
                return;
            }

            Position pos = new Position(x, y);

            if (!currentMap.validatePosition(pos)) {
                setFeedback("Invalid or occupied position.", false);
                return;
            }

            Industry industry = controller.addIndustry(selected, pos);
            currentMap.addIndustry(industry);

            setFeedback("Industry added successfully!", true);
        } catch (NumberFormatException e) {
            setFeedback("X and Y must be integers.", false);
        } catch (IllegalArgumentException e) {
            setFeedback(e.getMessage(), false);
        }
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }

    private void setFeedback(String message, boolean success) {
        feedbackLabel.setText(message);
        feedbackLabel.setStyle(success ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
    }
}

