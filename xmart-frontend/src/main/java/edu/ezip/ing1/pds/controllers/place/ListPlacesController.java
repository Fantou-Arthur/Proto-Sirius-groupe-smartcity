package edu.ezip.ing1.pds.controllers.place;

import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.PlaceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListPlacesController implements Initializable {

    private final static String LoggingLabel = " L i s t - P l a c e s - C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Places places;

    @FXML
    private TableColumn<PlaceCell, String> addressColumn;

    @FXML
    private TableColumn<PlaceCell, Integer> capacityColumn;

    @FXML
    private TableColumn<PlaceCell, String> nameColumn;

    @FXML
    private TableColumn actionColumn;

    @FXML
    private TableView<PlaceCell> placeListTable;

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
    public void GoToCapteurView() throws IOException {
        MainView.setRoot("CapteurView");
    }

    @FXML
    public void listPlaces() throws IOException {
        logger.debug(  "Places list: " + places);

    }
    @FXML
    public void viewAffluence() throws IOException {
        MainView.setRoot("Affluence");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<PlaceCell, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PlaceCell, String>("address"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<PlaceCell, Integer>("maxCapacity"));

        actionColumn.setCellValueFactory(new PropertyValueFactory<PlaceCell, HBox>("actions"));

        ArrayList<Place> placeList = places.getPlaces();
        ArrayList<PlaceCell> data = new ArrayList<>();
        for (int i=0; i<placeList.size(); i++) {
            data.add(new PlaceCell(placeList.get(i)));
        }

        ObservableList<PlaceCell> placesList = FXCollections.observableArrayList(data);
        logger.debug(  "Places list in ObservableList: " + placesList);
        placeListTable.setItems(placesList);
    }




}
