package edu.ezip.ing1.pds.controllers.Capteur;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.CapteurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private void GoToEditCapteurView() throws IOException {
        TitlePaneEditCapteur .setVisible(true);
    }

    @FXML
    private void LeaveEditCapteurView() throws IOException {
        TitlePaneEditCapteur .setVisible(false);
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
    private final static String LoggingLabel = "FrontEnd - CapteurController";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    @FXML
    private TextField Edit_Name = new TextField();
    @FXML
    private TextField Edit_State = new TextField();
    @FXML
    private TextField Edit_Id_lieu = new TextField();
    @FXML
    private TextField Edit_Id = new TextField();

    public void confirmEditSensor() throws JsonProcessingException {
        String Edit_name = Edit_Name.getText();
        String state = Edit_State.getText();
        String id_lieu = Edit_Id_lieu.getText();
        String id = Edit_Id.getText();
        logger.info(Edit_name+" "+state+" "+ id_lieu + " " + id);
        boolean statebool = Boolean.parseBoolean(state);
        Capteur capteur = new Capteur(id,Edit_name,statebool,id_lieu);
        TitlePaneEditCapteur .setVisible(false);
        NetworkConfig networkConfig = new NetworkConfig();
        CapteurService capteurService = new CapteurService(networkConfig);
        capteurService.insertCapteur(capteur);


    }

    @FXML
    private TextField Adder_Name = new TextField();
    @FXML
    private TextField Adder_State = new TextField();
    @FXML
    private TextField Adder_Id_lieu = new TextField();
    @FXML
    private TextField Adder_Id = new TextField();

    public void confirmAddSensor() throws JsonProcessingException {
        String Edit_name = Adder_Name.getText();
        String state = Adder_State.getText();
        String id_lieu = Adder_Id_lieu.getText();
        String id = Adder_Id.getText();
        logger.info(Edit_name+" "+state+" "+ id_lieu + " " + id);
        boolean statebool = Boolean.parseBoolean(state);
        Capteur capteur = new Capteur(id,Edit_name,statebool,id_lieu);
        TitlePaneAddCapteur .setVisible(false);
        NetworkConfig networkConfig = new NetworkConfig();
        CapteurService capteurService = new CapteurService(networkConfig);
        capteurService.insertCapteur(capteur);
    }
    @FXML
    public void viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }
}
