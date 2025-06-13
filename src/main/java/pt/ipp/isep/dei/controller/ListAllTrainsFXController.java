package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import pt.ipp.isep.dei.domain.Train;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.List;

public class ListAllTrainsFXController {

    @FXML
    private TextArea trainListArea;

    private final ListTrainController controller = new ListTrainController();

    @FXML
    public void initialize() {
        List<Train> trains = ApplicationSession.getInstance().getCreatedTrains();
        StringBuilder sb = new StringBuilder();

        sb.append("ID | Locomotive Type | Cargos\n");
        sb.append("-------------------------------\n");

        for (Train train : trains) {
            sb.append(String.format(
                    "%d | %-16s | %s\n",
                    train.getId(),
                    train.getLocomotive().getType().getFuelType(),
                    train.getCargos().isEmpty() ? "None" : train.getCargos()
            ));
        }

        if (trains.isEmpty()) {
            sb.append("No trains created yet.");
        }

        trainListArea.setText(sb.toString());
    }

    @FXML
    private void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            MainFX.setRootScene(root, "Main Menu", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
