package edu.ezip.ing1.pds;

import edu.ezip.ing1.pds.controllers.place.EditPlaceController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("mainView").load();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Smart-City Board");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml).load();
        scene.setRoot(root);
    }

    public static void setRoot(String fxml, Object data) throws IOException {
        FXMLLoader fxmlLoader = loadFXML(fxml);
        Parent root = fxmlLoader.load();

        Object controller = fxmlLoader.getController();

        if (controller instanceof EditPlaceController) {
            ((EditPlaceController) controller).setData(data);
        }

        scene.setRoot(root);
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource( "/views/" + fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
