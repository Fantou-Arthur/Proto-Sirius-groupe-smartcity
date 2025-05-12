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
import edu.ezip.ing1.pds.utils.DialogBox;
import edu.ezip.ing1.pds.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPlaceController implements Initializable {

    private final static String LoggingLabel = "E d i t- P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Dialog<String> dialog = new Dialog<String>();
    private AddressService addressService = new AddressService(networkConfig);
    private Place place;

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

    private TimePicker timePicker = new TimePicker();


    public void setData(Object data){
        if(data instanceof Place){
            logger.debug("Place data received : {}", data);
            this.place = (Place) data;
            this.nameTextField.setText(this.place.getName());
            this.descriptionTextArea.setText(this.place.getDescription());
            this.capacitySpinner.setText(String.valueOf(this.place.getMaxCapacity()));
            this.longitudeSpinner.setText(String.valueOf(this.place.getLongitude()));
            this.latitudeSpinner.setText(String.valueOf(this.place.getLatitude()));
            this.typeComboBox.setValue(this.place.getType().toString());
            this.timePicker.setTime(this.place.getPeakHour().toLocalTime());
            this.addressComboBox.setValue(Utils.getAddressNameById(this.place.getAddress()));
        }
    }

    public void updatePlace(){
        String name = nameTextField.getText();
        String description  = descriptionTextArea.getText();
        double longitude = 0;
        double latitude = 0;
        int capacity = 0;
        String type = (String) typeComboBox.getValue();


        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
            longitude = Double.parseDouble(longitudeSpinner.getText());
            latitude = Double.parseDouble(latitudeSpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        if(!handleError(name,description,capacity,type, longitude,latitude, addressComboBox.getValue())){
            Place updateplace = new Place();
            updateplace.setId(place.getId());
            updateplace.setName(name);
            updateplace.setDescription(description);
            updateplace.setType(Type.valueOf(type));
            updateplace.setMaxCapacity(capacity);
            updateplace.setLongitude(longitude);
            updateplace.setLatitude(latitude);
            updateplace.setPeakHour(Time.valueOf(timePicker.getTime()));
            updateplace.setAddress(Utils.getAssociatedAddressId(addressComboBox.getValue()));
            PlaceService placeService = new PlaceService(networkConfig);
            try {
                Place placeAdded = placeService.updatePlace(updateplace);
                logger.debug("Place updated : {}", placeAdded);
                if (placeAdded != null) {
                    dialogBox.setTitle("Modification Place");
                    dialogBox.setContentText("Place "+ placeAdded.getName() +" modifier avec succès");
                    dialogBox.showAndWait();
                }else{
                    dialogBox.setTitle("Modification Place");
                    dialogBox.setContentText("Une erreur est survenue. Veuillez réessayer");
                    dialogBox.showAndWait();
                }
                MainView.setRoot("listPlaces");
            } catch (JsonProcessingException e) {
                logger.debug(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    boolean handleError(String name, String description, int capacity, String type, Double longitude, Double latitude, String address ){
        boolean error = false;
        if(name.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez le nom de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(address.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez selectionnez une address");
            dialogBox.showAndWait();
            error = true;
        }else if(capacity <= 0){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une capacity superieur à 0");
            dialogBox.showAndWait();
            error = true;
        }else if(type.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez le type de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(longitude == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez la longitude de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(latitude == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez la latitude de la place");
            dialogBox.showAndWait();
            error = true;
        }else if(description.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une description");
            dialogBox.showAndWait();
            error = true;
        }
        else if(timePicker.getTime() == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une heure de pointe");
            dialogBox.showAndWait();
            error = true;
        }
        else if(longitude < -180 || longitude > 180){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une longitude valide");
            dialogBox.showAndWait();
            error = true;
        }else if(latitude < -90 || latitude > 90){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une latitude valide");
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
        timerPane.getChildren().add(timePicker);
    }
}
