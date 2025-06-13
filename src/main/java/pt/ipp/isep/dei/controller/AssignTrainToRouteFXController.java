package pt.ipp.isep.dei.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.ArrayList;
import java.util.List;

public class AssignTrainToRouteFXController {

    @FXML
    private ComboBox<Train> trainComboBox;

    @FXML
    private TextField routeNameField;

    @FXML
    private ComboBox<Station> stationComboBox;

    @FXML
    private TextField cargoField;

    @FXML
    private ComboBox<CargoMode> modeComboBox;

    @FXML
    private Label statusLabel;

    private final AssignTrainToRouteController controller = new AssignTrainToRouteController();
    private final Route route = new Route();

    @FXML
    public void initialize() {
        trainComboBox.getItems().addAll(controller.getAvailableTrains());
        stationComboBox.getItems().addAll(controller.getAllStations());
        modeComboBox.getItems().addAll(CargoMode.values());
    }

    @FXML
    public void handleAddPoint() {
        Station selectedStation = stationComboBox.getValue();
        String cargoInput = cargoField.getText().trim();
        CargoMode selectedMode = modeComboBox.getValue();

        if (selectedStation == null || cargoInput.isEmpty() || selectedMode == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        List<PointOfRoute> existingPoints = route.getPointsOfRoute();
        if (!existingPoints.isEmpty()) {
            Station lastStation = existingPoints.get(existingPoints.size() - 1).getStation();
            if (lastStation.getName().equalsIgnoreCase(selectedStation.getName())) {
                statusLabel.setText("Cannot add the same station twice in a row.");
                return;
            }
        }

        List<String> cargoList = new ArrayList<>();
        for (String cargoStr : cargoInput.split(",")) {
            try {
                CargoType.valueOf(cargoStr.trim().toUpperCase());
                cargoList.add(cargoStr.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                statusLabel.setText("Invalid cargo type: " + cargoStr.trim());
                return;
            }
        }

        PointOfRoute point = new PointOfRoute(selectedStation, cargoList, selectedMode);
        route.addPointOfRoute(point);

        statusLabel.setText("Point added to route.");
        cargoField.clear();
    }

    @FXML
    public void handleAssignRoute() {
        Train selectedTrain = trainComboBox.getValue();
        String routeName = routeNameField.getText().trim();

        if (selectedTrain == null || routeName.isEmpty() || route.getPointsOfRoute().isEmpty()) {
            statusLabel.setText("Select a train, set a name and add at least one point.");
            return;
        }

        route.setName(routeName);
        controller.assignRouteToTrain(selectedTrain, route);
        statusLabel.setText("Train successfully assigned to route: " + routeName);
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

