package edu.ezip.ing1.pds.controllers.user;

import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.UserService;
import edu.ezip.ing1.pds.utils.DialogBox;
import edu.ezip.ing1.pds.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginController {


    private final static String LoggingLabel = "L O G I N - U S E R -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    private DialogBox dialogBox = new DialogBox();

    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;



    public void GoToSignUp(MouseEvent mouseEvent) throws IOException {
        MainView.setRoot("signup");
    }

    public void connectUser(ActionEvent actionEvent) throws IOException {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        if(!Utils.checkLoginData(email,password)){
            UserService userService = new UserService(networkConfig);
            User user = new User(email,password);
            User connectedUser = userService.loginUser(user);
            if(connectedUser == null){
                dialogBox.setTitle("Erreur");
                dialogBox.setContentText("Veuillez verifier vos identifiants");
                dialogBox.showAndWait();
            } else {
                MainView.setRoot("mainView");
            }
        }
    }

}
