package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.List;

public class StationDetailsFXController {

    @FXML private ComboBox<Station> stationComboBox;
    @FXML private TextArea detailsArea;
    @FXML private Label warningLabel;

    private final BuildStationController controller = new BuildStationController();

    @FXML
    public void initialize() {
        List<Station> stations = controller.listAllStations();
        stationComboBox.getItems().setAll(stations);

        stationComboBox.setOnAction(e -> showDetails());
    }

    private void showDetails() {
        Station selected = stationComboBox.getValue();
        if (selected == null) return;

        detailsArea.setText(selected.getFullDetails());

        StringBuilder warnings = new StringBuilder();
        if (selected.getBuildings().isEmpty()) {
            warnings.append(" This station has no buildings.\n");
        }
        if (selected.getSupplyCargo().isEmpty()) {
            warnings.append(" No supply cargo available at this station.\n");
        }
        if (selected.getDemandCargo().isEmpty()) {
            warnings.append(" No demand cargo required at this station.\n");
        }

        warningLabel.setText(warnings.toString());
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
}

