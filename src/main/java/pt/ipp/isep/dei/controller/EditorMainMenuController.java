package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pt.ipp.isep.dei.ui.MainFX;


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
    public void handleAddCity() {
        loadScene("/fxml/AddCity.fxml", "Add City");
    }

    @FXML
    public void handleAddIndustry() {
        loadScene("/fxml/AddIndustry.fxml", "Add Industry");
    }

    @FXML
    public void handleBack() {
        loadScene("/fxml/Login.fxml", "Login");
    }

    private void loadScene(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            MainFX.setRootScene(root, title, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
