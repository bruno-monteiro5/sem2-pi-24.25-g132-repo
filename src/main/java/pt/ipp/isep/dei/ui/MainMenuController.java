package pt.ipp.isep.dei.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MainMenuController {

    @FXML
    public void handleBuildStation(ActionEvent event) {
        showMessage("Criar Estação", "Aqui será aberta a interface para a US05.");
    }

    @FXML
    public void handleUpgradeStation(ActionEvent event) {
        showMessage("Fazer Upgrade", "Aqui será aberta a interface para a US06.");
    }

    @FXML
    public void handleStationDetails(ActionEvent event) {
        showMessage("Ver Detalhes", "Aqui será aberta a interface para a US07.");
    }

    @FXML
    public void handleStartSimulation(ActionEvent event) {
        showMessage("Simulação", "A simulação será iniciada.");
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
