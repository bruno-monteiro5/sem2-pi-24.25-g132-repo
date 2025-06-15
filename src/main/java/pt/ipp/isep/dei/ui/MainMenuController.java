package pt.ipp.isep.dei.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.ipp.isep.dei.domain.Map;
import pt.ipp.isep.dei.domain.Player;
import pt.ipp.isep.dei.domain.Scenario;
import pt.ipp.isep.dei.domain.Station;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.simulator.SimulatorEngine;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import pt.ipp.isep.dei.domain.Position;
import javafx.scene.layout.StackPane;




import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainMenuController {

    @FXML private Label mapLabel;
    @FXML private Label scenarioLabel;
    @FXML private Label yearLabel;
    @FXML private Label budgetLabel;
    @FXML private Label scaleLabel;
    @FXML private StackPane mapArea;
    @FXML private ImageView mapImageView;
    @FXML private Label noMapLabel;
    @FXML private Button backToLoginButton;
    @FXML private Label monthLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private ListView<String> eventListView;

    private SimulatorEngine simulatorEngine;

    private static final ObservableList<String> sharedEventMessages = FXCollections.observableArrayList();

    private Timeline simulationLoop;


    @FXML private ScrollPane mapScrollPane;



    private Timeline budgetUpdater;
    private int cellSize;
    private double zoomFactor = 1.0;
    private final double ZOOM_DELTA = 0.1;


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

        if (map == null) {
            mapImageView.setVisible(false);
            noMapLabel.setVisible(true);
        } else {
            mapLabel.setText("Map: " + map.getName());
            scaleLabel.setText("Scale: " + map.getScale());
            setupZoomOnScroll();

            Image background = new Image("/images/map.png");
            mapImageView.setImage(background);
            mapImageView.setFitWidth(650);
            mapImageView.setFitHeight(640);

            cellSize = (int) Math.min(650.0 / map.getWidth(), 640.0 / map.getHeight());
            mapArea.setPrefSize(map.getWidth() * cellSize, map.getHeight() * cellSize);

            for (Station station : Repositories.getInstance().getStationRepository().getAll()) {
                Position pos = station.getLocation();
                double x = pos.getX() * cellSize;
                double y = pos.getY() * cellSize;

                Circle dot = new Circle(x, y, cellSize / 2.5);
                dot.setFill(Color.DARKBLUE);
                dot.setStroke(Color.WHITE);
                dot.setStrokeWidth(1.5);

                Tooltip.install(dot, new Tooltip(station.getName()));
                mapArea.getChildren().add(dot);
            }

            mapImageView.setVisible(true);
            noMapLabel.setVisible(false);
        }

        if (scenario != null && scenario.getPeriodTime() != null) {
            scenarioLabel.setText("Scenario: " + scenario.getName());
            yearLabel.setText("Year: " + scenario.getPeriodTime().getStartYear());
            monthLabel.setText("Month: 1");

            Player player = Repositories.getInstance().getPlayerRepository().getCurrentPlayer();
            simulatorEngine = ApplicationSession.getInstance().getSimulatorEngine();

            if (simulatorEngine == null) {
                simulatorEngine = new SimulatorEngine(scenario, player);
                simulatorEngine.setEventListener(); // mantém o que já tinhas
                ApplicationSession.getInstance().setSimulatorEngine(simulatorEngine);
            }

            eventListView.setItems(ApplicationSession.getInstance().getSimulatorEventMessages());

        } else {
            startButton.setDisable(true);
            pauseButton.setDisable(true);
        }

        startAutoUpdate();
    }

    private void setupZoomOnScroll() {
        mapScrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.isControlDown()) {
                zoomFactor += (event.getDeltaY() > 0) ? ZOOM_DELTA : -ZOOM_DELTA;
                if (zoomFactor < ZOOM_DELTA) zoomFactor = ZOOM_DELTA;
                mapArea.setScaleX(zoomFactor);
                mapArea.setScaleY(zoomFactor);
                event.consume();
            }
        });
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

    private void startAutoUpdate() {
        budgetUpdater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateBudget();
            updateTime();
        }));
        budgetUpdater.setCycleCount(Timeline.INDEFINITE);
        budgetUpdater.play();
    }

    private void updateTime() {
        if (simulatorEngine != null) {
            int year = simulatorEngine.getCurrentSimulatedYear();
            int month = simulatorEngine.getCurrentSimulatedMonth();
            yearLabel.setText("Year: " + year);
            monthLabel.setText("Month: " + month);
        }
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
        loadFXML("/fxml/AssignTrainToRoute.fxml", "Assign Train To A Route", 600, 400);

    }

    @FXML
    public void handleListAllTrains() {
        loadFXML("/fxml/ListAllTrains.fxml", "List All Trains", 600, 400);

    }

    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            MainFX.setRootScene(root, "Login - Railroad Simulator", 400, 300, false);
        } catch (IOException e) {
            showError("Error loading Login screen.");
        }
    }

    @FXML
    private void highlight(javafx.scene.input.MouseEvent e) {
        Button btn = (Button) e.getSource();
        if (btn == backToLoginButton) {
            btn.setStyle("-fx-background-color: #90a4ae; -fx-text-fill: #263238; -fx-font-weight: bold;");
        } else {
            btn.setStyle("-fx-background-color: #004d40; -fx-text-fill: white;");
        }
    }

    @FXML
    private void resetHighlight(javafx.scene.input.MouseEvent e) {
        Button btn = (Button) e.getSource();
        if (btn == backToLoginButton) {
            btn.setStyle("-fx-background-color: #b0bec5; -fx-text-fill: #263238; -fx-font-weight: bold;");
        } else {
            btn.setStyle("-fx-background-color: #00695c; -fx-text-fill: white;");
        }
    }



    private void loadFXML(String path, String title, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            MainFX.setRootScene(root, title, width, height, true);
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



    @FXML
    private void handleStartSimulation() {
        if (simulatorEngine != null) {
            // Limpa mensagens anteriores da lista partilhada
            ApplicationSession.getInstance().getSimulatorEventMessages().clear();

            // Cria o loop apenas uma vez
            if (simulationLoop == null) {
                simulationLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> simulatorEngine.runNextEvent()));
                simulationLoop.setCycleCount(Timeline.INDEFINITE);
            }

            // Inicia a simulação
            simulatorEngine.start();
            simulationLoop.play();

            ApplicationSession.getInstance().getSimulatorEventMessages().add("▶ Simulation started");
        }
    }






    @FXML
    private void handlePauseSimulation() {
        if (simulationLoop != null) {
            simulationLoop.pause();
            sharedEventMessages.add("⏸ Simulation paused");
        }
    }

    public void updateDateLabels(int year, int month) {
        yearLabel.setText("Year: " + year);
        monthLabel.setText("Month: " + month);
    }




}
