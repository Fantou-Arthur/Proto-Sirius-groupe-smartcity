package edu.ezip.ing1.pds.controllers.place;

import com.dlsc.gemsfx.TimePicker;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Type;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.AddressService;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.services.UserSession;
import edu.ezip.ing1.pds.utils.DialogBox;
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
    private AddressService addressService = new AddressService(networkConfig);
    private PlaceService placeService = new PlaceService(networkConfig);

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
        int longitude = 0;
        int latitude = 0;
        int capacity = 0;
        String type = (String) typeComboBox.getValue();


        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
            longitude = Integer.parseInt(longitudeSpinner.getText());
            latitude = Integer.parseInt(latitudeSpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        if(!handleError(name,description,capacity)){
            Place place = new Place();
            place.setName(name);
            place.setDescription(description);
            place.setType(Type.valueOf(type));
            place.setMaxCapacity(capacity);
            place.setLongitude(longitude);
            place.setLatitude(latitude);
            place.setAddress(getAssociatedAddressId(addressComboBox.getValue()));
            place.setPeakHour(Time.valueOf(timePicker.getTime()));
            place.setId_entity(UserSession.getInstance().getEntityId());
            logger.debug("Place to add : {}", place);
            PlaceService placeService = new PlaceService(networkConfig);

            try {
                Place placeAdded = placeService.insertPlace(place);
                logger.debug("Place added : {}", placeAdded);
                if (placeAdded != null) {
                    dialogBox.setTitle("Ajout nouvelle Place");
                    dialogBox.setContentText("Place "+ placeAdded.getName() +" ajouter avec succès");
                    dialogBox.showAndWait();
                    this.resetFields();
                }else{
                    dialogBox.setTitle("Ajout nouvelle Place");
                    dialogBox.setContentText("Something went wrong, please try again");
                    dialogBox.showAndWait();
                }
            } catch (JsonProcessingException e) {
                logger.debug(e.getMessage());
            }
        }
    }

    public int getAssociatedAddressId(String addressName){
        int id = 0;
        try {
            Addresses addresses = addressService.selectAllAddress();
            if (addresses != null) {
                for (int i = 0; i < addresses.getEntities().size(); i++) {
                    if(addresses.getEntities().get(i).getName().equals(addressName)){
                        id = addresses.getEntities().get(i).getId();
                    }
                }
            } else {
                logger.debug("No address found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void resetFields(){
        nameTextField.setText("");
        descriptionTextArea.setText("");
        capacitySpinner.setText("");
    }

    boolean handleError(String name, String address, int capacity){
        boolean error = false;
        if(name.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez le nom de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(address.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une address");
            dialogBox.showAndWait();
            error = true;
        }else if(capacity <= 0){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une capacity superieur à 0");
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
