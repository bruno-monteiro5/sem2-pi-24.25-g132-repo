package pt.ipp.isep.dei.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import pt.ipp.isep.dei.ui.MainFX;

import java.io.IOException;

public class BuildRailwayLineFXController {
    // Campos FXML
    @FXML private TextField lineNameField;
    @FXML private ComboBox<String> startStationComboBox;
    @FXML private ComboBox<String> endStationComboBox;
    @FXML private TextField distanceField;
    @FXML private ComboBox<String> intermediateStationComboBox;
    @FXML private ListView<String> intermediateStationsListView;
    @FXML private Label statusLabel;

    // Lista observável para estações intermediárias
    private final ObservableList<String> intermediateStations = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura a ListView para usar a lista observável
        intermediateStationsListView.setItems(intermediateStations);

        // Carrega estações nos ComboBox (exemplo - na prática viria de um serviço/repositório)
        loadStations();
    }

    private void loadStations() {
        ObservableList<String> stations = FXCollections.observableArrayList(
                "Lisboa Oriente", "Porto Campanhã", "Faro", "Coimbra B",
                "Aveiro", "Braga", "Guarda", "Évora", "Beja", "Viana do Castelo"
        );

        startStationComboBox.setItems(stations);
        endStationComboBox.setItems(stations);
        intermediateStationComboBox.setItems(stations);
    }

    @FXML
    public void handleAddIntermediate(ActionEvent actionEvent) {
        String selectedStation = intermediateStationComboBox.getValue();

        // Validações
        if (selectedStation == null || selectedStation.isEmpty()) {
            showStatus("Please select a station to add.", "red");
            return;
        }

        if (intermediateStations.contains(selectedStation)) {
            showStatus("Station already added to the route.", "orange");
            return;
        }

        if (selectedStation.equals(startStationComboBox.getValue())) {
            showStatus("Cannot add start station as intermediate.", "orange");
            return;
        }

        if (selectedStation.equals(endStationComboBox.getValue())) {
            showStatus("Cannot add end station as intermediate.", "orange");
            return;
        }

        // Adiciona a estação
        intermediateStations.add(selectedStation);
        intermediateStationComboBox.getSelectionModel().clearSelection();
        showStatus("Station added: " + selectedStation, "green");
    }

    @FXML
    public void handleClearForm(ActionEvent actionEvent) {
        // Limpa todos os campos
        lineNameField.clear();
        startStationComboBox.getSelectionModel().clearSelection();
        endStationComboBox.getSelectionModel().clearSelection();
        distanceField.clear();
        intermediateStationComboBox.getSelectionModel().clearSelection();
        intermediateStations.clear();
        statusLabel.setText("");
    }

    @FXML
    public void handleBuildLine(ActionEvent actionEvent) {
        // Validações dos campos obrigatórios
        if (lineNameField.getText().trim().isEmpty()) {
            showStatus("Please enter a line name.", "red");
            return;
        }

        if (startStationComboBox.getValue() == null) {
            showStatus("Please select a start station.", "red");
            return;
        }

        if (endStationComboBox.getValue() == null) {
            showStatus("Please select an end station.", "red");
            return;
        }

        if (startStationComboBox.getValue().equals(endStationComboBox.getValue())) {
            showStatus("Start and end stations cannot be the same.", "red");
            return;
        }

        try {
            double distance = Double.parseDouble(distanceField.getText());
            if (distance <= 0) {
                showStatus("Distance must be greater than zero.", "red");
                return;
            }

            // Aqui você chamaria o serviço para criar a linha ferroviária
            // railwayService.buildRailwayLine(...);

            showStatus("Railway line created successfully!", "green");
            handleClearForm(null); // Limpa o formulário após sucesso

        } catch (NumberFormatException e) {
            showStatus("Invalid distance value. Please enter a valid number.", "red");
        }
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para mostrar mensagens de status
    private void showStatus(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-style: italic;");
    }
}