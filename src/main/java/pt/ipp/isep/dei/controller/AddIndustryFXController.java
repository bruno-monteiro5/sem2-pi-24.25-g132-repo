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

public class AddIndustryFXController {

    @FXML
    private ComboBox<IndustryType> typeComboBox;
    @FXML
    private ComboBox<IndustryData> industryComboBox;
    @FXML
    private TextField xField;
    @FXML
    private TextField yField;
    @FXML
    private Label summaryLabel;
    @FXML
    private Label feedbackLabel;

    private final AddIndustryController ctrl = new AddIndustryController();

    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(IndustryType.values()));
        typeComboBox.setOnAction(e -> updateIndustryOptions());
        industryComboBox.setOnAction(e -> updateSummary());
    }

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

    @FXML
    public void handleAddIndustry() {
        try {
            IndustryData data = industryComboBox.getValue();
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());
            Position pos = new Position(x, y);

            Industry added = ctrl.addIndustry(data, pos);
            ApplicationSession.getInstance().getCurrentMap().addIndustry(added);

            feedbackLabel.setText("\u2705 Industry added successfully!");
            feedbackLabel.setStyle("-fx-text-fill: green;");

        } catch (NumberFormatException e) {
            feedbackLabel.setText("Coordinates must be integers.");
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
