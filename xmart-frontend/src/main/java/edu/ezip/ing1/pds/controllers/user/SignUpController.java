package edu.ezip.ing1.pds.controllers.user;

import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.UserService;
import edu.ezip.ing1.pds.utils.DialogBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SignUpController {

    private final static String LoggingLabel = "S I G N U P - U S E R -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    private DialogBox dialogBox = new DialogBox();


    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;


    public void GoToLogin(MouseEvent mouseEvent) throws IOException {
        MainView.setRoot("login");
    }

    public void createUser(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();

        if(!handleError(username,email,password)){
            User user = new User(username,password,email);
            UserService userService = new UserService(networkConfig);
            try {
                User newUser = userService.insertUser(user);
                logger.debug("User added : {}", newUser);
                if(!newUser.getUsername().isEmpty()){
                    MainView.setRoot("mainView");
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }


    boolean handleError(String username, String email, String password){
        boolean error = false;
        if(username.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez un nom d'utilisateur");
            dialogBox.showAndWait();
            error = true;
        }else if(email.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une address mail");
            dialogBox.showAndWait();
            error = true;
        }else if(password.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez un mot de pass");
            dialogBox.showAndWait();
            error = true;
        }
        return error;
    }

}
