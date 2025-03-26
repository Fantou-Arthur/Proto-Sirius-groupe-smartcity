package edu.ezip.ing1.pds.controllers.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.utils.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddNewPlaceController {

    private final static String LoggingLabel = "A d d -  N e w - P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    private DialogBox dialogBox = new DialogBox();

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField capacitySpinner;

    public void addNewPlace(){
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        int capacity = 0;

        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        if(!handleError(name,address,capacity)){
            Place place = new Place(name,address,capacity);
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

    public void resetFields(){
        nameTextField.setText("");
        addressTextField.setText("");
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

}
