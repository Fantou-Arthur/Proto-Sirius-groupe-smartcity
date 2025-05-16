package edu.ezip.ing1.pds.controllers.user;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.entity.Entities;
import edu.ezip.ing1.pds.business.dto.entity.Entity;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.EntityService;
import edu.ezip.ing1.pds.services.UserService;
import edu.ezip.ing1.pds.utils.DialogBox;
import edu.ezip.ing1.pds.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private final static String LoggingLabel = "S I G N U P - U S E R -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private DialogBox dialogBox = new DialogBox();
    private Entities entities;
    private Entity selectedEntity = null;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private ComboBox entityComboBox;

    public SignUpController(){
        EntityService entityService = new EntityService(networkConfig);
        try{
            entities = entityService.selectAllEntity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void GoToLogin(MouseEvent mouseEvent) throws IOException {
        MainView.setRoot("login");
    }

    public void createUser(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();

        if(!Utils.checkSignUpData(username,email,password,selectedEntity)){
            User user = new User(username,password,email,selectedEntity.getId());
            UserService userService = new UserService(networkConfig);
            try {
                User newUser = userService.insertUser(user);
                logger.debug("User added : {}", newUser);
                if(newUser == null) {
                    dialogBox.setTitle("User error");
                    dialogBox.setContentText("Addresse "+ email +" déjà utiliser veuillez vous connectez");
                    dialogBox.showAndWait();
                }else if(!newUser.getUsername().isEmpty()){
                    MainView.setRoot("mainView");
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void onSelectedChanged(){
        String selectItem = (String) entityComboBox.getValue();
        logger.info("SELECTED ENTITY {}", selectItem);
        for(Entity entity: entities.getEntities()){
            if(entity.getName() == selectItem){
                selectedEntity = entity;
                logger.info("id :{}", selectedEntity.getId());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> entitiesOptions = new ArrayList<>();
       for(Entity entity : entities.getEntities()){
            entitiesOptions.add(entity.getName());
        }
       Collections.sort(entitiesOptions);
       entityComboBox.getItems().addAll(entitiesOptions);
    }

}
