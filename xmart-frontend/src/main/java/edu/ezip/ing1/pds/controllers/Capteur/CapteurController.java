package edu.ezip.ing1.pds.controllers.Capteur;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.business.dto.place.IdNamePlace;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.CapteurService;
import edu.ezip.ing1.pds.services.PlaceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
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
    private ArrayList<Integer> listeId = new ArrayList<>();
    private Capteurs capteurs;
    @FXML
    Button Error_Empty_TextField = new Button();
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
    Button WarnIdNotUnique = new Button();
    @FXML
    TitledPane TitlePaneEditCapteur = new TitledPane();
    @FXML
    TitledPane TitlePaneAddCapteur = new TitledPane();
    @FXML
    TitledPane TitlePaneDeleteCapteur = new TitledPane();

    @FXML
    private ComboBox<IdNamePlace> ComboBoxIdLieu = new ComboBox<>();
    @FXML
    private ComboBox<Integer> ComboBoxIdAffluence = new ComboBox<>();
    @FXML
    private ComboBox<String> ComboBoxStatus = new ComboBox<>();

    @FXML
    private DatePicker Adder_Installed = new DatePicker();
    @FXML
    private DatePicker Adder_Maintenance = new DatePicker();


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
        ShowSensorList();
        UpdatePlaceIdName();

    };

    @FXML
    public void UpdatePlaceIdName(){
        try{
            PlaceService placeService = new PlaceService(networkConfig);
            Places places = placeService.selectIdNamePlaces();
            ComboBoxIdLieu.getItems().clear();
            logger.info("Liste Lieux et Id_Lieux : {}",places);
            for (Place place : places.getPlaces()){
                IdNamePlace idNamePlace = new IdNamePlace(place.getId(),place.getName());
                ComboBoxIdLieu.getItems().add(idNamePlace);
            }
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @FXML
    public void ShowSensorList(){
        try {
            CapteurService capteurService = new CapteurService(networkConfig);
            Capteurs capteurs = capteurService.selectAllCapteurs();
            logger.info("Capteur list : {}", capteurs );
            ArrayList<Capteur> listeCapteur = new ArrayList<>(capteurs.getCapteurs());
            listeId.clear();
            for (Capteur capteur : listeCapteur){
                listeId.add(capteur.getId());
            }
            ObservableList<Capteur> capteurs_ol = FXCollections.observableArrayList(listeCapteur);
            tableauCapteurs.setItems(capteurs_ol);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @FXML
    public void ResetTextFields(){
        Adder_Name.setText("");
        Adder_State.setText("");
        Adder_Id.setText("");
        Edit_Id.setText("");
        Edit_Name.setText("");
        Edit_State.setText("");
        Edit_Id_lieu.setText("");
    }


    @FXML
    private void GoToCapteurView() throws IOException {
        MainView.setRoot("CapteurView");
        ShowSensorList();
    }

    @FXML
    private void GoToAddCapteurView() throws IOException {
        UpdatePlaceIdName();
        TitlePaneAddCapteur .setVisible(true);

    }
    @FXML
    private void LeaveAddCapteurView() throws IOException {
        ShowSensorList();
        ResetTextFields();
        WarnIdNotUnique.setVisible(false);
        TitlePaneAddCapteur .setVisible(false);
    }



    @FXML
    private void GoToEditCapteurView()throws IOException {
        TitlePaneEditCapteur .setVisible(true);
        Capteur capteur = tableauCapteurs.getSelectionModel().getSelectedItem();
        int id = capteur.getId();
        String name =capteur.getName();
        Boolean state = capteur.getState();
        int id_lieu = capteur.getId_lieu();
        String str_id = String.valueOf(id);
        String str_state = String.valueOf(state);
        String str_lieu = String.valueOf(id_lieu);
        Edit_Id.setText(str_id);
        Edit_Name.setText(name);
        Edit_State.setText(str_state);
        Edit_Id_lieu.setText(str_lieu);

    }

    @FXML
    private void LeaveEditCapteurView() throws IOException {
        ShowSensorList();
        Error_Empty_TextField.setVisible(false);
        ResetTextFields();
        TitlePaneEditCapteur .setVisible(false);
    }

    @FXML
    private void GoToDeleteCapteurView() throws IOException {
        TitlePaneDeleteCapteur .setVisible(true);
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

    @FXML
    private TextField Edit_Manufacturer = new TextField();
    @FXML
    private TextField Edit_Model = new TextField();
    @FXML
    private TextField Edit_Description = new TextField();


    @FXML
    private ComboBox<Integer> Edit_Affluence = new ComboBox<>();
    @FXML
    private ComboBox<String> Edit_Status = new ComboBox<>();

    @FXML
    private DatePicker Edit_Installed = new DatePicker();
    @FXML
    private DatePicker Edit_Last_Maintenance = new DatePicker();

    public boolean IdEstUnique(int id){
        for (int les_id : listeId){
            if (les_id == id){
                return true;
            }
        }
        return false;
    }

    public static boolean EstConvertibleInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean EstConvertibleBool(String str) {
        if (str.equals("true") || str.equals("false")) {
        return true;}
        return false;
    }

    public void confirmEditSensor() throws JsonProcessingException {
        Capteur capteur1 = tableauCapteurs.getSelectionModel().getSelectedItem();
        int int_id = capteur1.getId();
        String id = String.valueOf(int_id);
        String Edit_name = Edit_Name.getText();
        String state = Edit_State.getText();
        String id_lieu = Edit_Id_lieu.getText();
        String Edit_manufacturer = Edit_Manufacturer.getText();
        String Edit_model = Edit_Model.getText();
        String Edit_description = Edit_Description.getText();
        int Edit_id_affluence = Edit_Affluence.getValue();
        String Edit_status = Edit_Status.getValue();
        String Edit_installed = String.valueOf(Edit_Installed.getValue());
        String Edit_last_maintenance = String.valueOf(Edit_Last_Maintenance.getValue());

        if ((Edit_name == "") || (state == "") || (id_lieu == "") || !EstConvertibleInt(id_lieu) || !EstConvertibleBool(state)) {
            Error_Empty_TextField.setVisible(true);
        }
        else{
            logger.info(Edit_name+" "+state+" "+ id_lieu + " " + id);
            int int_id_lieu = Integer.parseInt(id_lieu);
            boolean statebool = Boolean.parseBoolean(state);
            Capteur capteur = new Capteur(int_id,Edit_name,statebool,int_id_lieu, Edit_description, Edit_manufacturer, Edit_model, Edit_status, Edit_id_affluence, Edit_installed, Edit_last_maintenance);

            final String networkConfigFile = "network.yaml";
            final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
            CapteurService capteurService = new CapteurService(networkConfig);
            capteurService.editCapteur(capteur);
            ShowSensorList();
            Error_Empty_TextField.setVisible(false);
            logger.info("Capteur "+capteur.getId()+" "+capteur.getName());
            TitlePaneEditCapteur. setVisible(false);
        }
    }

    @FXML
    private TextField Adder_Name = new TextField();
    @FXML
    private TextField Adder_State = new TextField();
    @FXML
    private TextField Adder_Id = new TextField();
    @FXML
    private TextField Adder_Manufacturer = new TextField();
    @FXML
    private TextField Adder_Model = new TextField();
    @FXML
    private TextField Adder_Description = new TextField();







    public void confirmAddSensor() throws IOException {
        String add_name = Adder_Name.getText();
        String state = Adder_State.getText();
        IdNamePlace selectedPlace = ComboBoxIdLieu.getValue();
        int intid_lieu = selectedPlace.getId();
        String id_lieu = Integer.toString(intid_lieu);
        String id = Adder_Id.getText();
        String manufacturer = Adder_Manufacturer.getText();
        String model = Adder_Model.getText();
        String description = Adder_Description.getText();
        int id_affluence =ComboBoxIdAffluence.getValue();
        String status = ComboBoxStatus.getValue();
        String installed = String.valueOf(Adder_Installed.getValue());
        String maintenance = String.valueOf(Adder_Maintenance.getValue());
        if ((add_name == "") || (state == "") || (id_lieu == "") || (id == "") || !EstConvertibleInt(id_lieu) || !EstConvertibleBool(state) || !EstConvertibleInt(id)) {
            Error_Empty_TextField.setVisible(true);
        }
        else{
            logger.info(add_name+" "+state+" "+ id_lieu + " " + id);
            boolean statebool = Boolean.parseBoolean(state);
            int int_id = Integer.parseInt(id);
            int int_id_lieu = Integer.parseInt(id_lieu);
            boolean IdUnique = !IdEstUnique(int_id);
            if (!IdUnique){
                WarnIdNotUnique.setVisible(true);
            }
            else{
                Capteur capteur = new Capteur(int_id,add_name,statebool,int_id_lieu,description,manufacturer,model,status,id_affluence,installed,maintenance);
                TitlePaneAddCapteur .setVisible(false);
                WarnIdNotUnique.setVisible(false);
                Error_Empty_TextField.setVisible(false);
                final String networkConfigFile = "network.yaml";
                final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
                CapteurService capteurService = new CapteurService(networkConfig);
                capteurService.insertCapteur(capteur);
                LeaveAddCapteurView();
            }
        }
    }

    @FXML
    public void LeaveErrorEmptyTextField(){
        Error_Empty_TextField.setVisible(false);
    }

    @FXML
    public void Warn_Id_Not_Unique(){
        WarnIdNotUnique.setVisible(false);
    }

    @FXML
    public void viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }

    @FXML
    private void confirmDeleteSensor() throws IOException {
        Capteur capteur = tableauCapteurs.getSelectionModel().getSelectedItem();
        CapteurService capteurService = new CapteurService(networkConfig);
        capteurService.deleteCapteur(capteur);
        ShowSensorList();
        TitlePaneDeleteCapteur .setVisible(false);
    }


}
