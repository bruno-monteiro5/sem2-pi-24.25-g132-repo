package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import pt.ipp.isep.dei.ui.MainFX;

public class LoginFXController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private final AuthenticationController ctrl = new AuthenticationController();

    @FXML
    public void handleLogin() {
        String email = emailField.getText().trim();
        String pwd = passwordField.getText().trim();

        if (email.equals("player@this.app") && pwd.equals("player")) {
            openMainMenu("PLAYER");
        } else if (email.equals("editor@this.app") && pwd.equals("editor")) {
            openMainMenu("EDITOR");
        } else {
            showAlert("Login failed", "Invalid credentials.");
        }
    }

    private void openMainMenu(String role) {
        try {
            FXMLLoader loader;
            Parent root;

            if ("PLAYER".equals(role)) {
                loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
                root = loader.load();
                MainFX.setRootScene(root, "Railroad Simulator - Main Menu", 800, 600);

            } else if ("EDITOR".equals(role)) {
                loader = new FXMLLoader(getClass().getResource("/fxml/EditorMainMenu.fxml"));
                root = loader.load();
                MainFX.setRootScene(root, "Editor - Railroad Editor Menu", 800, 600);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "It was not possible to open the interface for role: " + role);
        }
    }


    private void showAlert(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
