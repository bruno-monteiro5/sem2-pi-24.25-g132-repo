package pt.ipp.isep.dei.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
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
}
