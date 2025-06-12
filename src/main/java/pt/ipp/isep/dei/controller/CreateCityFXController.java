package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.Position;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.ArrayList;
import java.util.List;

public class CreateCityFXController {

    @FXML private TextField nameField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField houseBlocksField;
    @FXML private RadioButton autoRadio;
    @FXML private RadioButton manualRadio;
    @FXML private TextArea manualTextArea;
    @FXML private Label feedbackLabel;

    private final CreateCityController ctrl = new CreateCityController();

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        autoRadio.setToggleGroup(group);
        manualRadio.setToggleGroup(group);
        autoRadio.setSelected(true);

        manualTextArea.setVisible(false);

        manualRadio.setOnAction(e -> manualTextArea.setVisible(true));
        autoRadio.setOnAction(e -> manualTextArea.setVisible(false));
    }

    @FXML
    public void handleCreateCity() {
        String name = nameField.getText().trim();
        String xStr = xField.getText().trim();
        String yStr = yField.getText().trim();
        String blocksStr = houseBlocksField.getText().trim();

        try {
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);
            int blocks = Integer.parseInt(blocksStr);
            boolean manual = manualRadio.isSelected();
            Position pos = new Position(x, y);
            List<Position> manualPositions = null;

            if (manual) {
                String[] entries = manualTextArea.getText().trim().split(";");
                if (entries.length != blocks) {
                    throw new IllegalArgumentException("Number of manual positions must match the number of house blocks.");
                }

                manualPositions = new ArrayList<>();
                for (String entry : entries) {
                    String[] coords = entry.trim().split(",");
                    if (coords.length != 2)
                        throw new IllegalArgumentException("Invalid manual position format. Use: x,y;x,y");

                    int hx = Integer.parseInt(coords[0].trim());
                    int hy = Integer.parseInt(coords[1].trim());
                    manualPositions.add(new Position(hx, hy));
                }
            }

            City city = ctrl.createCity(name, pos, blocks, manual, manualPositions);
            ApplicationSession.getInstance().getCurrentMap().addCity(city);
            Repositories.getInstance().getMapRepository().setActiveMap(ApplicationSession.getInstance().getCurrentMap());

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

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
