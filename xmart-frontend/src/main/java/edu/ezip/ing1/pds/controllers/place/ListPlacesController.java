package edu.ezip.ing1.pds.controllers.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ListPlacesController {

    private final static String LoggingLabel = " L i s t - P l a c e s - C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Places places;

    @FXML
    private TableColumn<Place, String> addressColumn;

    @FXML
    private TableColumn<Place, Integer> capacityColumn;

    @FXML
    private TableColumn<Place, String> nameColumn;

    @FXML
    private TableView<Place> placeListTable;

    public ListPlacesController() {
        PlaceService placeService = new PlaceService(networkConfig);
        try {
            places = placeService.selectAllPlaces();
            logger.info("Places list : {}", places );
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @FXML
    public void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }

    @FXML
    public void GoToCapteurView(ActionEvent actionEvent) throws IOException {
        MainView.setRoot("CapteurView");
    }

    @FXML
    public void listPlaces(ActionEvent actionEvent) throws IOException {
        MainView.setRoot("listPlaces");
        PlaceService placeService = new PlaceService(networkConfig);
        Places places = placeService.selectAllPlaces();
        logger.debug(  "Places list: " + places);
    }


    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Place, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Place, String>("address"));

    }
}
