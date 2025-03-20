package edu.ezip.ing1.pds.controllers.Capteur;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.CapteurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class CapteurController implements Initializable {
    private Capteurs capteurs;
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
    private TableColumn<Capteur, Integer> IdColumn;
    @FXML
    private TableColumn<Capteur, String> NameColumn;
    @FXML
    private TableColumn<Capteur, Boolean> StateColumn;
    @FXML
    private TableColumn<Capteur, Integer> Id_lieuColumn;
    @FXML
    private TableView<Capteur> tableauCapteurs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        IdColumn.setCellValueFactory(new PropertyValueFactory<Capteur, Integer>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Capteur, String>("name"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<Capteur, Boolean>("state"));
        Id_lieuColumn.setCellValueFactory(new PropertyValueFactory<Capteur, Integer>("id_lieu"));

    };

    @FXML
    public void ShowSensorList(){
        try {
            CapteurService capteurService = new CapteurService(networkConfig);
            Capteurs capteurs = capteurService.selectAllCapteurs();
            logger.info("Capteur list : {}", capteurs );
            ArrayList<Capteur> listeCapteur = new ArrayList<>(capteurs.getCapteurs());
            ObservableList<Capteur> capteurs_ol = FXCollections.observableArrayList(listeCapteur);
            tableauCapteurs.setItems(capteurs_ol);
            System.out.println(listeCapteur);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println("aaah");
        }
    }

    @FXML
    public void ResetTextFields(){
        Adder_Name.setText("");
        Adder_State.setText("");
        Adder_Id_lieu.setText("");
        Adder_Id.setText("");
        Edit_Id.setText("");
        Edit_Name.setText("");
        Edit_State.setText("");
        Edit_Id_lieu.setText("");
    }


    @FXML
    private void GoToCapteurView() throws IOException {
        MainView.setRoot("CapteurView");
        System.out.println("Show sensor list");
        ShowSensorList();
    }

    @FXML
    private void GoToAddCapteurView() throws IOException {
        TitlePaneAddCapteur .setVisible(true);
    }
    @FXML
    private void LeaveAddCapteurView() throws IOException {
        ShowSensorList();
        ResetTextFields();
        TitlePaneAddCapteur .setVisible(false);
    }



    @FXML
    private void GoToEditCapteurView()throws IOException {
        TitlePaneEditCapteur .setVisible(true);
    }

    @FXML
    private void LeaveEditCapteurView() throws IOException {
        ShowSensorList();
        ResetTextFields();
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
        ShowSensorList();
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
        int int_id = Integer.parseInt(id);;
        int int_id_lieu = Integer.parseInt(id_lieu);
        boolean statebool = Boolean.parseBoolean(state);
        Capteur capteur = new Capteur(int_id,Edit_name,statebool,int_id_lieu);
        TitlePaneEditCapteur.setVisible(false);
        NetworkConfig networkConfig = new NetworkConfig();


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
        int int_id = Integer.parseInt(id);;
        int int_id_lieu = Integer.parseInt(id_lieu);
        Capteur capteur = new Capteur(int_id,Edit_name,statebool,int_id_lieu);
        TitlePaneAddCapteur .setVisible(false);
        final String networkConfigFile = "network.yaml";
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        CapteurService capteurService = new CapteurService(networkConfig);
        capteurService.insertCapteur(capteur);
    }
    @FXML
    public void viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }


}
