package edu.ezip.ing1.pds.controllers;

import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MainViewController implements Initializable {

    private Places places;
    private final static String LoggingLabel = " M a i n V i e w - C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);


    @FXML
    private ListView <String> placeListView;
    @FXML
    private Text NumberSensorText;
    @FXML
    private Text NumberAlertText;

    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }

    @FXML
    private void GoToCapteurView() throws IOException {
        System.out.println("GoToCapteurView");
        MainView.setRoot("CapteurView");
    }

    @FXML
    private void  listPlaces() throws IOException {
        MainView.setRoot("listPlaces");
    }

    @FXML
    private void  viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }

    @FXML
    private void showDataRelatedToSelectedItem() throws IOException {
        String selectedPlace = placeListView.getSelectionModel().getSelectedItem();
        if (selectedPlace != null) {
            for (int i = 0; i < places.getPlaces().size(); i++) {
                if (places.getPlaces().get(i).getName().equals(selectedPlace)) {
                    int countAlert = Utils.getNumberOfAffluenceRelatedTo(places.getPlaces().get(i));
                    int countSensor = Utils.getNumberOfSensorRelatedTo(places.getPlaces().get(i));
                    NumberAlertText.setText(String.valueOf(countAlert));
                    NumberSensorText.setText(String.valueOf(countSensor));
                    break;
                }
            }
        }
    }

    public void getAllPlaces(){
        PlaceService placeService = new PlaceService(networkConfig);
        try {
            places = placeService.selectAllPlaces();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        // Get the list of places
        getAllPlaces();
        ArrayList<String> placesList = new ArrayList<>();
        for (int i = 0; i < places.getPlaces().size(); i++) {
            placesList.add(places.getPlaces().get(i).getName());
        }
        placeListView.getItems().addAll(placesList);

        placeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                showDataRelatedToSelectedItem();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }


}
