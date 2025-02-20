package edu.ezip.ing1.pds.controllers.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddNewController {

    private final static String LoggingLabel = "FrontEnd - PlaceService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

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

    public void addNewPlace(){
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        int capacity = 0;
        try{
            capacity = Integer.parseInt(capacitySpinner.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        Place place = new Place("1",name,address,capacity);
        PlaceService placeService = new PlaceService(networkConfig);
        try {
            placeService.insertPlace(place);
        } catch (JsonProcessingException e) {
            logger.debug(e.getMessage());
        }

        logger.info(name+" "+address+" "+ capacity);

    }
    
}
