package edu.ezip.ing1.pds.controllers.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddNewPlaceController {

    private final static String LoggingLabel = "A d d -  N e w - P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Dialog<String> dialog = new Dialog<String>();

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField capacitySpinner;

    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }

    @FXML
    private void  listPlaces() throws IOException {
        MainView.setRoot("listPlaces");
    }

    @FXML
    private void GoToCapteurView() throws IOException {
        System.out.println("GoToCapteurView");
        MainView.setRoot("CapteurView");
    }

    public void addNewPlace(){
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        int capacity = 0;
        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        Place place = new Place(name,address,capacity);
        PlaceService placeService = new PlaceService(networkConfig);

        try {
            Place placeAdded = placeService.insertPlace(place);
            logger.debug("Place added : {}", placeAdded);
            dialog.setTitle("New Place insertion");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            if (placeAdded != null) {
                dialog.setContentText("Place "+ placeAdded.getName() +" added successfully");
                nameTextField.setText("");
                addressTextField.setText("");
                capacitySpinner.setText("");
            }else{
                dialog.setContentText("Something went wrong, please try again");
            }
            dialog.showAndWait();
        } catch (JsonProcessingException e) {
            logger.debug(e.getMessage());
        }

    }

    @FXML
    public void viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }
}
