package pt.ipp.isep.dei.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.repository.Repositories;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class MainMenuController {

    @FXML private Label mapLabel;
    @FXML private Label scenarioLabel;
    @FXML private Label yearLabel;
    @FXML private Label budgetLabel;
    @FXML private Label scaleLabel;
    @FXML private Pane mapArea;
    @FXML private ImageView mapImageView;

    private Timeline budgetUpdater;
    private int cellSize;

    private static MainMenuController instance;

    public MainMenuController() {
        instance = this;
    }

    public static MainMenuController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        Map map = ApplicationSession.getInstance().getCurrentMap();
        Scenario scenario = ApplicationSession.getInstance().getCurrentScenario();

        if (map != null) {
            mapLabel.setText("Map: " + map.getName());
            scaleLabel.setText("Scale: " + map.getScale());

            Image background = new Image("/images/map.png");
            mapImageView.setImage(background);

            int mapWidthCells = map.getWidth();
            int mapHeightCells = map.getHeight();

            cellSize = (int) Math.min(
                    background.getWidth() / mapWidthCells,
                    background.getHeight() / mapHeightCells
            );

            mapImageView.setFitWidth(mapWidthCells * cellSize);
            mapImageView.setFitHeight(mapHeightCells * cellSize);
            mapArea.setPrefSize(mapWidthCells * cellSize, mapHeightCells * cellSize);
        }

        if (scenario != null && scenario.getPeriodTime() != null) {
            scenarioLabel.setText("Scenario: " + scenario.getName());
            yearLabel.setText("Year: " + scenario.getPeriodTime().getStartYear());
        }

        updateBudget();
        startBudgetAutoUpdate();
    }

    private void updateBudget() {
        Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();
        if (player != null) {
            double balance = player.getBalance();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            String formattedBudget = formatter.format(balance);
            budgetLabel.setText("Budget: " + formattedBudget);
        } else {
            budgetLabel.setText("Budget: -");
        }
    }

    private void startBudgetAutoUpdate() {
        budgetUpdater = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateBudget()));
        budgetUpdater.setCycleCount(Timeline.INDEFINITE);
        budgetUpdater.play();
    }

    @FXML
    public void handleBuildStation() {
        loadFXML("/fxml/BuildStation.fxml", "Build Station", 600, 400);
    }


    @FXML
    public void handleUpgradeStation() {
        loadFXML("/fxml/UpgradeStation.fxml", "Upgrade Station", 600, 400);
    }

    @FXML
    public void handleStationDetails() {
        loadFXML("/fxml/StationDetails.fxml", "Station Details", 600, 400);
    }

    @FXML
    public void handleBuyLocomotive() {
        loadFXML("/fxml/BuyLocomotive.fxml", "Buy Locomotive", 600, 400);
    }

    @FXML
    public void handleBuildRailwayLine() {
    }

    @FXML
    public void handleCreateTrain() {
        loadFXML("/fxml/CreateTrain.fxml", "Create Train", 600, 400);


    }

    @FXML
    public void handleAssignTrainToARoute() {
    }

    @FXML
    public void handleListAllTrains() {
        loadFXML("/fxml/ListAllTrains.fxml", "List All Trains", 600, 400);

    }

    @FXML
    public void handleStartSimulation() {
    }

    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Login - Railroad Simulator", 400, 300);
        } catch (IOException e) {
            showError("Error loading Login screen.");
        }
    }

    private void loadFXML(String path, String title, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            MainFX.setRootScene(root, title, width, height);
        } catch (IOException e) {
            showError("Failed to load " + title + " screen.");
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
