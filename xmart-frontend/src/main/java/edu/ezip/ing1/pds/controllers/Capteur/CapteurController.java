package edu.ezip.ing1.pds.controllers.Capteur;

import edu.ezip.ing1.pds.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

import java.io.IOException;



public class CapteurController {
    @FXML
    Button editSensorButton = new Button();
    @FXML
    Button AboartEditSensorButton = new Button();
    @FXML
    Button confirmEditSensorButton = new Button();
    @FXML
    Button addSensorButton = new Button();
    @FXML
    Button confirmAddSensorButton = new Button();
    @FXML
    Button AboartAddSensorButton = new Button();
    @FXML
    Button deleteSensorButton = new Button();
    @FXML
    Button confirmDeleteSensorButton = new Button();
    @FXML
    Button AboartDeletCapteur = new Button();
    @FXML
    TitledPane TitlePaneEditCapteur = new TitledPane();
    @FXML
    TitledPane TitlePaneAddCapteur = new TitledPane();
    @FXML
    TitledPane TitlePaneDeleteCapteur = new TitledPane();


    @FXML
    private void GoToCapteurView() throws IOException {
        MainView.setRoot("CapteurView");
    }

    @FXML
    private void GoToAddCapteurView() throws IOException {
        TitlePaneAddCapteur .setVisible(true);
    }
    @FXML
    private void LeaveAddCapteurView() throws IOException {
        TitlePaneAddCapteur .setVisible(false);
    }
    @FXML
    private void confirmAddSensor() throws IOException {
        TitlePaneAddCapteur .setVisible(false);
    }

    @FXML
    private void GoToEditCapteurView() throws IOException {
        TitlePaneEditCapteur .setVisible(true);
    }

    @FXML
    private void LeaveEditCapteurView() throws IOException {
        TitlePaneEditCapteur .setVisible(false);
    }

    @FXML
    private void confirmEditSensor() throws IOException {
        TitlePaneEditCapteur .setVisible(false);
        //gérer requête sql ?
    }

    @FXML
    private void GoToDeleteCapteurView() throws IOException {
        TitlePaneDeleteCapteur .setVisible(true);
    }

    @FXML
    private void confirmDeleteSensor() throws IOException {
        TitlePaneDeleteCapteur .setVisible(false);
    }

    @FXML
    private void LeaveDeleteCapteurView() throws IOException {
        TitlePaneDeleteCapteur .setVisible(false);
    }
    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }
}
