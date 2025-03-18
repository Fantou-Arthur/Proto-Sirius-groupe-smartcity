package edu.ezip.ing1.pds.controllers.place;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditPlaceController {

    private final static String LoggingLabel = "E d i t- P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Dialog<String> dialog = new Dialog<String>();
    private Place place;


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField capacitySpinner;


    public void updatePlace(){

    }

    public void setData(Object data){
        if(data instanceof Place){
            this.place = (Place) data;
            this.nameTextField.setText(this.place.getName());
            this.addressTextField.setText(this.place.getAddress());
            this.capacitySpinner.setText(String.valueOf(this.place.getMaxCapacity()));
        }
    }

}
