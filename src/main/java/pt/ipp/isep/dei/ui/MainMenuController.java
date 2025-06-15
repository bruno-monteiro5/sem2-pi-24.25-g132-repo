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
    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private ListView<String> eventListView;

    private SimulatorEngine simulatorEngine;

    private final ObservableList<String> eventMessages = FXCollections.observableArrayList();
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


            int mapWidth = map.getWidth();
            int mapHeight = map.getHeight();

            Image background = new Image("/images/map.png");
            mapImageView.setImage(background);

            List<Station> stations = Repositories.getInstance().getStationRepository().getAll();

            for (Station station : stations) {
                Position pos = station.getLocation();
                double visualX = pos.getX() * cellSize;
                double visualY = pos.getY() * cellSize;

                Circle dot = new Circle(visualX, visualY, cellSize / 2.5);
                dot.setFill(Color.DARKBLUE);
                dot.setStroke(Color.WHITE);
                dot.setStrokeWidth(1.5);

                Tooltip tooltip = new Tooltip(station.getName());
                Tooltip.install(dot, tooltip);

                mapArea.getChildren().add(dot);
            }

            int mapWidthCells = map.getWidth();
            int mapHeightCells = map.getHeight();

            mapImageView.setFitWidth(mapWidthCells);
            mapImageView.setFitHeight(mapHeightCells);

            mapImageView.setFitWidth(650);
            mapImageView.setFitHeight(640);

            cellSize = (int) Math.min(
                    650.0 / mapWidthCells,
                    640.0 / mapHeightCells
            );
            mapArea.setPrefSize(mapWidthCells * cellSize, mapHeightCells * cellSize);


            mapImageView.setVisible(true);
            noMapLabel.setVisible(false);
        }

        if (scenario != null && scenario.getPeriodTime() != null) {
            scenarioLabel.setText("Scenario: " + scenario.getName());
            yearLabel.setText("Year: " + scenario.getPeriodTime().getStartYear());

            // INICIALIZAR O SIMULADOR
            simulatorEngine = new SimulatorEngine(scenario, Repositories.getInstance().getPlayerRepository().getCurrentPlayer());
            eventListView.setItems(eventMessages); // Bind à ListView

            simulatorEngine.setEventListener(event -> {
                Platform.runLater(() -> {
                    String msg = String.format("[Tick %d] %s", event.getScheduledTick(), event.getClass().getSimpleName());
                    eventMessages.add(msg);
                });
            });
        } else {
            startButton.setDisable(true);
            pauseButton.setDisable(true);
        }

        updateBudget();
        startBudgetAutoUpdate();
    }

    private void setupZoomOnScroll() {
        mapScrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.isControlDown()) {
                if (event.getDeltaY() > 0) {
                    zoomFactor += ZOOM_DELTA;
                } else {
                    zoomFactor -= ZOOM_DELTA;
                    if (zoomFactor < ZOOM_DELTA) zoomFactor = ZOOM_DELTA;
                }

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
            if (simulationLoop == null) {
                simulationLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> simulatorEngine.runNextEvent()));
                simulationLoop.setCycleCount(Timeline.INDEFINITE);
            }

            simulationLoop.play();
            eventMessages.add("▶ Simulação iniciada");
        }
    }


    @FXML
    private void handlePauseSimulation() {
        if (simulationLoop != null) {
            simulationLoop.pause();
            eventMessages.add("⏸ Simulação pausada");
        }
    }

}
