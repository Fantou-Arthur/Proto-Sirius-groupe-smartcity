package edu.ezip.ing1.pds.controllers.place;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.utils.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EditPlaceController {

    private final static String LoggingLabel = "E d i t- P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Dialog<String> dialog = new Dialog<String>();
    private Place place;

    private DialogBox dialogBox = new DialogBox();


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField capacitySpinner;

    public void setData(Object data){
        if(data instanceof Place){
            this.place = (Place) data;
            this.nameTextField.setText(this.place.getName());
            this.addressTextField.setText(this.place.getAddress());
            this.capacitySpinner.setText(String.valueOf(this.place.getMaxCapacity()));
        }
    }

    public void updatePlace(){
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        int capacity = 0;
        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        if(!handleError(name,address,capacity)){
            place.setName(name);
            place.setAddress(address);
            place.setMaxCapacity(capacity);
            PlaceService placeService = new PlaceService(networkConfig);

            try {
                Place placeAdded = placeService.updatePlace(place);
                logger.debug("Place updated : {}", placeAdded);
                if (placeAdded != null) {
                    dialogBox.setTitle("Modification Place");
                    dialogBox.setContentText("Place "+ placeAdded.getName() +" modifier avec succès");
                    dialogBox.showAndWait();
                    this.resetFields();
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


    public void resetFields(){
        nameTextField.setText("");
        addressTextField.setText("");
        capacitySpinner.setText("");
    }

    boolean handleError(String name, String address, int capacity){
        System.out.println(capacity);
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

}
