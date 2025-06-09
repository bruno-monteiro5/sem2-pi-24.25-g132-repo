package pt.ipp.isep.dei.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    private static Stage primaryStage;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle("Login - Railroad Simulator");
        stage.setScene(scene);
        stage.show();
    }


    public static void setRootScene(Parent root, String title, int width, int height) {
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void goToEditorMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("/fxml/EditorMainMenu.fxml"));
            Parent root = loader.load();
            setRootScene(root, "Editor Main Menu", 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
