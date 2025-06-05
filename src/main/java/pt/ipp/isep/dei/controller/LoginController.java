package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import pt.ipp.isep.dei.ui.MainFX;

public class LoginController {

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
        if ("PLAYER".equals(role)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
                Parent root = loader.load();
                MainFX.setRootScene(root, "Railroad Simulator - Main Menu", 400, 300);
            } catch (Exception e) {
                showAlert("Error", "It was not possible to open the main menu.");
            }
        } else if ("EDITOR".equals(role)) {
            showAlert("Login Ok", "Editor authenticated but interface not implemented yet.");
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
