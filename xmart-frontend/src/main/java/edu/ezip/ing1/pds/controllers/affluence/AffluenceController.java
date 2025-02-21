package edu.ezip.ing1.pds.controllers.affluence;


import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
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


public class AffluenceController{

	private final static String LoggingLabel = "A F F L U E N C E - C O N T R O L L E R";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

	@FXML
    private TextField idPlaceTextField;
    @FXML
    private TextField nbrPersTextField;
    @FXML
    private TextField nbrDepartTextField;
    @FXML
    private TextField nbrArriveTextField;
    @FXML
    private TextField idTextField;

	public AffluenceController(){
		AffluenceModel affluenceViewModel = new AffluenceModel();
		//affluenceViewModel.
		//AffluenceInteractor affluenceInteractor = new AffluenceInteractor(affluenceViewModel);
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

    public void editAffluence(){
    	try{
	    	int id_place = Integer.parseInt(idPlaceTextField.getText());
	        int nbrPersones = Integer.parseInt(nbrPersTextField.getText());
	        int nbrDepart = Integer.parseInt(nbrDepartTextField.getText());
	        int nbrArriver = Integer.parseInt(nbrArriveTextField.getText());
	    }catch(NumberFormatException e){
	    	logger.error(e.getMessage());
	    }


    }
    public void deleteAffluence(){}
    public void addAffluence(){
        try{
            int id_place = Integer.parseInt(idPlaceTextField.getText());
            int nbrPersones = Integer.parseInt(nbrPersTextField.getText());
            int nbrDepart = Integer.parseInt(nbrDepartTextField.getText());
            int nbrArriver = Integer.parseInt(nbrArriveTextField.getText());

            Affluence affluence = new Affluence(id_place, nbrPersones, nbrDepart, nbrArriver);
            AffluenceService affluenceService = new AffluenceService(networkConfig);

            try {
                affluenceService.insertAffluence(affluence);
                logger.info("Affluence added successfully");
            } catch (JsonProcessingException e) {
                logger.debug(e.getMessage());
            }
        }catch(NumberFormatException e){
            logger.error(e.getMessage());
        }

        
    }
}
