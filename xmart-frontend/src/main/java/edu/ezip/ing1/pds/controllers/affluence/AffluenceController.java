package edu.ezip.ing1.pds.controllers.affluence;


import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import edu.ezip.ing1.pds.business.dto.affluence.TreeViewData;
import edu.ezip.ing1.pds.business.dto.affluence.Sensor;
import edu.ezip.ing1.pds.business.dto.affluence.SensorInfos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.models.affluence.AffluenceModel;
import edu.ezip.ing1.pds.services.AffluenceService;
import javafx.scene.layout.Region;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import java.net.URL;
import java.util.ResourceBundle;


public class AffluenceController implements Initializable{

	private final static String LoggingLabel = "A F F L U E N C E - C O N T R O L L E R";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private Affluences affluences;

    private AffluenceService affluenceService;

	@FXML
    private TextField idPlaceTextField;
    @FXML
    private TextField NbrDensTextField;
    @FXML
    private TextField PeakStatTextField;
    @FXML
    private TextField NbrPersTextField;
    @FXML
    private TextField NbrDepartTextField;
    @FXML
    private TextField NbrArriveTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TableView<Affluence> displayTable;
    @FXML
    private TableColumn<Affluence, Integer> IdColumn;
    @FXML
    private TableColumn<Affluence, Integer> NbrPersColumn;
    @FXML
    private TableColumn<Affluence, Integer> NbrArrColumn;
    @FXML
    private TableColumn<Affluence, Integer> NbrDepColumn;
    @FXML
    private TableColumn<Affluence, Integer> IdPlaceColumn;
    @FXML
    private TreeView<TreeItem> treeView;

    private Set<TreeItem<TreeItem>> selectedItems = new HashSet<>();

    private TreeViewData treeData = new TreeViewData();

	public AffluenceController(){
		this.affluenceService = new AffluenceService(networkConfig);
        try {
            affluences = this.affluenceService.selectAllAffluences();
            logger.info("Affluences list (controller): {}", affluences );
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
	}

	@FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }
    @FXML
    private void GoToCapteurView() throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IdColumn.setCellValueFactory(new PropertyValueFactory<Affluence, Integer>("id"));
        NbrPersColumn.setCellValueFactory(new PropertyValueFactory<Affluence, Integer>("nbrPers"));
        NbrArrColumn.setCellValueFactory(new PropertyValueFactory<Affluence, Integer>("nbrDepart"));
        NbrDepColumn.setCellValueFactory(new PropertyValueFactory<Affluence, Integer>("nbrArriver"));
        IdPlaceColumn.setCellValueFactory(new PropertyValueFactory<Affluence, Integer>("idPlace"));
        logger.info("---------");
        logger.info("---------");
        logger.info("---------");
        
        chargerTree();

        logger.info("---------");
        logger.info("---------");
        logger.info("---------");
        logger.info("Affluence list : {}", affluences );

        //treeView.setRoot(new TreeItem<Node>(new Node("test de node !")).addChildren(new TreeItem<Node>(new Node("test de node de node !"))));
        updateList();

        displayTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                logger.info("selected row : {}", newSelection);

                idTextField.setText(String.valueOf(newSelection.getId()));
                NbrPersTextField.setText(String.valueOf(newSelection.getNbrPers()));
                NbrDepartTextField.setText(String.valueOf(newSelection.getNbrDepart()));
                NbrArriveTextField.setText(String.valueOf(newSelection.getNbrArriver()));
                idPlaceTextField.setText(String.valueOf(newSelection.getIdPlace()));
            }
        });
    }

    public void selectionAffluenceCapteur(){

    }

    public void chargerTree(){
        try{
            treeData =  this.affluenceService.getTreeView();
            logger.info("treeViewData : {}", treeData);

            TreeItem root = new TreeItem("root");
            root.setExpanded(true);
            treeView.setShowRoot(false);

            Map<String, TreeItem<String>> nodeMap = new HashMap<>();
            
            for ( SensorInfos sensorinfos : treeData.getData().values()){
                TreeItem<String> curNode = root;
                for(String node : sensorinfos.getPath()){
                    
                    if(!nodeMap.containsKey(node)){

                        TreeItem<String> newElem = new TreeItem<>(node);
                        curNode.getChildren().add(newElem);
                        nodeMap.put(node, newElem);

                    }

                    curNode = nodeMap.get(node);
                }
                curNode.getChildren().add(new TreeItem<String>(sensorinfos.getSensor().getName()));

            }

            treeView.setRoot(root);
            treeView.setOnMouseClicked(event -> handleMouseClick(event, treeView));
        }catch(IOException e){
            logger.debug(e.getMessage());
        }
    }

    private void handleMouseClick(MouseEvent event, TreeView<TreeItem> treeView) {
        if (event.getButton() == MouseButton.PRIMARY) {
            TreeItem<TreeItem> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if ( ( selectedItem != null ) && selectedItem.isLeaf() ) {
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else {
                    selectedItems.add(selectedItem);
                }
                logger.info("Selected items: {}", selectedItems);
                for ( TreeItem<TreeItem> s :  selectedItems){
                    
                    logger.info("truc truc truc : {}", s);
                    /*
                    SensorInfos sensInf = treeData.getSensorData(s.getValue());
                    logger.info("sensId : {}", sensInf);
                    */
                }
            }
        }
    }

    public void editAffluence(){

    	try{
	    	int idPlace = Integer.parseInt(idPlaceTextField.getText());
            boolean peak = Boolean.parseBoolean(PeakStatTextField.getText());
            double density = Double.parseDouble(NbrDensTextField.getText());
	        int nbrPersones = Integer.parseInt(NbrPersTextField.getText());
	        int nbrDepart = Integer.parseInt(NbrDepartTextField.getText());
	        int nbrArriver = Integer.parseInt(NbrArriveTextField.getText());
            int id = Integer.parseInt(idTextField.getText());

            Affluence affluence = new Affluence(id, idPlace, peak, density, nbrPersones, nbrDepart, nbrArriver);

            try {
                this.affluenceService.editAffluence(affluence);
                logger.info("Affluence edited successfully");
            } catch (IOException e) {
                logger.debug(e.getMessage());
            }

	    }catch(NumberFormatException e){
	    	logger.error(e.getMessage());
	    }

        updateList();

    }

    public void updateList(){
        logger.info("updating tableview !!!!!");
        try{
            affluences = this.affluenceService.selectAllAffluences();
        }catch(IOException ioe){
            logger.info(ioe.getMessage());
        }
        ArrayList<Affluence> listeAffluence = new ArrayList<>(affluences.getAffluences());
        ObservableList<Affluence> affluences_ol = FXCollections.observableArrayList(listeAffluence);
        logger.info("viewtable : {}", displayTable);
        displayTable.setItems(affluences_ol);
        displayTable.refresh();
    }

    public void deleteAffluence(){
        try{
            int id = Integer.parseInt(idTextField.getText());
            int idPlace = Integer.parseInt(idPlaceTextField.getText());

            Affluence affluence = new Affluence();
            affluence.setId(id);
            affluence.setIdPlace(idPlace);

            try {
                this.affluenceService.deleteAffluence(affluence);
                logger.info("Affluence deleted successfully");
            } catch (IOException e ) {
                logger.debug(e.getMessage());
            }
        }catch(NumberFormatException e){
            logger.error(e.getMessage());
        }

        updateList();
    }


    public void addAffluence(){

        try{
            int idPlace = Integer.parseInt(idPlaceTextField.getText());
            boolean peak = Boolean.parseBoolean(PeakStatTextField.getText());
            double density = Double.parseDouble(NbrDensTextField.getText());
            int nbrPersones = Integer.parseInt(NbrPersTextField.getText());
            int nbrDepart = Integer.parseInt(NbrDepartTextField.getText());
            int nbrArriver = Integer.parseInt(NbrArriveTextField.getText());

            Affluence affluence = new Affluence(idPlace, peak, density, nbrPersones, nbrDepart, nbrArriver);

            try {
                this.affluenceService.insertAffluence(affluence);
                logger.info("Affluence added successfully");
            } catch (JsonProcessingException e) {
                logger.debug(e.getMessage());
            }
        }catch(NumberFormatException e){
            logger.error(e.getMessage());
        }

        updateList();
        
    }
}
