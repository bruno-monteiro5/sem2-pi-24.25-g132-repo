package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

public class EditorMainMenuController {

    @FXML
    public void handleCreateMap() {
        loadScene("/fxml/CreateMap.fxml", "Create Map");
    }

    @FXML
    public void handleCreateScenario() {
        loadScene("/fxml/CreateScenario.fxml", "Create Scenario");
    }

    @FXML
    public void handleCreateCity() {
        loadScene("/fxml/CreateCity.fxml", "Create City");
    }

    @FXML
    public void handleAddIndustry() {
        loadScene("/fxml/AddIndustry.fxml", "Add Industry");
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Login - Railroad Simulator", 400, 300);
        } catch (IOException e) {
            showError("Error loading Login screen.");
        }
    }


    private void loadScene(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            MainFX.setRootScene(root, title, 800, 600);
        } catch (Exception e) {
            showError("Could not load " + title + " screen.\nDetails: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}