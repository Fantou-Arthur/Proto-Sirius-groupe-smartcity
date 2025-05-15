package edu.ezip.ing1.pds.controllers.place;

import com.dlsc.gemsfx.TimePicker;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Type;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.AddressService;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.services.UserSession;
import edu.ezip.ing1.pds.utils.DialogBox;
import edu.ezip.ing1.pds.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewPlaceController implements Initializable {

    private final static String LoggingLabel = "A d d -  N e w - P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private PlaceService placeService = new PlaceService(networkConfig);
    private AddressService addressService = new AddressService(networkConfig);

    private DialogBox dialogBox = new DialogBox();

    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField capacitySpinner;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField longitudeSpinner;
    @FXML
    private TextField latitudeSpinner;
    @FXML
    private Pane timerPane;
    @FXML
    private ComboBox<String> addressComboBox;

    private TimePicker timePicker;

    public void addNewPlace(){
        String name = nameTextField.getText();
        String description  = descriptionTextArea.getText();
        double longitude = 0;
        double latitude = 0;
        int capacity = 0;
        String type = (String) typeComboBox.getValue();

        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            dialogBox.setTitle("Ajouter une nouvelle Place");
            dialogBox.setContentText("Veuillez entrer un entier pour la capacity ");
            dialogBox.showAndWait();
            throw null;
        }

        try{
            longitude = Double.parseDouble(longitudeSpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            dialogBox.setTitle("Ajouter une nouvelle Place");
            dialogBox.setContentText("Veuillez entrer un nombre décimal pour la longitude ");
            dialogBox.showAndWait();
            throw null;
        }

        try{
            latitude = Double.parseDouble(latitudeSpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            dialogBox.setTitle("Ajouter une nouvelle Place");
            dialogBox.setContentText("Veuillez entrer un nombre decimal pour la latitude ");
            dialogBox.showAndWait();
            throw null;
        }

        if(!handleError(name,description,capacity,type, longitude,latitude, addressComboBox.getValue())){
            Place place = new Place();
            place.setName(name);
            place.setDescription(description);
            place.setType(Type.valueOf(type));
            place.setMaxCapacity(capacity);
            place.setLongitude(longitude);
            place.setLatitude(latitude);
            place.setAddress(Utils.getAssociatedAddressId(addressComboBox.getValue()));
            place.setPeakHour(Time.valueOf(timePicker.getTime()));
            place.setId_entity(UserSession.getInstance().getEntityId());
            logger.debug("Place to add : {}", place);
            try {
                Place placeAdded = placeService.insertPlace(place);
                logger.debug("Place added : {}", placeAdded);
                if (placeAdded != null) {
                    dialogBox.setTitle("Ajouter une nouvelle Place");
                    dialogBox.setContentText("Place "+ placeAdded.getName() +" ajouter avec succès");
                    dialogBox.showAndWait();
                    this.resetFields();
                }else{
                    dialogBox.setTitle("Ajouter une nouvelle Place");
                    dialogBox.setContentText("Something went wrong, please try again");
                    dialogBox.showAndWait();
                }
            } catch (JsonProcessingException e) {
                logger.debug(e.getMessage());
            }
        }
    }

    public void goToAddNewAddress(){
        try {
            MainView.setRoot("createAddress");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void resetFields(){
        nameTextField.setText("");
        descriptionTextArea.setText("");
        capacitySpinner.setText("");
        longitudeSpinner.setText("");
        latitudeSpinner.setText("");
        typeComboBox.setValue("");
        addressComboBox.setValue("");
    }

    boolean handleError(String name, String description, int capacity, String type, Double longitude, Double latitude, String address ){
        boolean error = false;
        if(name.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer le nom de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(address.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez sélectionner  une adresse");
            dialogBox.showAndWait();
            error = true;
        }else if(capacity <= 0){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une capacité supérieure à 0");
            dialogBox.showAndWait();
            error = true;
        }else if(type.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez sélectionner le type de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(longitude == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer la longitude de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(latitude == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer la latitude de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(description.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une description");
            dialogBox.showAndWait();
            error = true;
        }
        else if(timePicker.getTime() == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une heure de pointe");
            dialogBox.showAndWait();
            error = true;
        }
        else if(longitude < -180 || longitude > 180){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une longitude valide, entre -180 et 180");
            dialogBox.showAndWait();
            error = true;
        }else if(latitude < -90 || latitude > 90){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une latitude valide, entre -90 et 90");
            dialogBox.showAndWait();
            error = true;
        }
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> types = new ArrayList<>();
        for (Type type : Type.values()) {
            types.add(type.toString());
        }
        typeComboBox.getItems().addAll(types);
        ArrayList<String> addressPlaceList = new ArrayList<>();
        try {
            Addresses addresses = addressService.selectAllAddress();
            if (addresses != null) {
                for (int i = 0; i < addresses.getEntities().size(); i++) {
                    addressPlaceList.add(addresses.getEntities().get(i).getName());
                }
            } else {
                logger.debug("No address found");
            }
            addressComboBox.getItems().addAll(addressPlaceList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        timePicker = new TimePicker();
        timerPane.getChildren().add(timePicker);
    }
}
