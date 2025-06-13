package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

public class CreateTrainFXController {

    @FXML
    private ComboBox<Locomotive> locomotiveComboBox;

    @FXML
    private Label statusLabel;

    private static int trainCounter = 1;

    @FXML
    public void initialize() {
        locomotiveComboBox.getItems().addAll(ApplicationSession.getInstance().getOwnedLocomotives());
    }

    @FXML
    private void onCreateTrain() {
        Locomotive loco = locomotiveComboBox.getValue();

        if (loco == null) {
            statusLabel.setText("Select a locomotive.");
            return;
        }

        Train train = new Train(trainCounter++, loco);
        ApplicationSession.getInstance().registerTrain(train);
        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setText("Train created successfully!");
        locomotiveComboBox.setValue(null);
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
