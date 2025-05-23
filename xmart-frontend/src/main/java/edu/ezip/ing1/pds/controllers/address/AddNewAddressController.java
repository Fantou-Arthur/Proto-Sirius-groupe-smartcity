package edu.ezip.ing1.pds.controllers.address;

import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.address.Address;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.AddressService;
import edu.ezip.ing1.pds.utils.DialogBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddNewAddressController {
    private final static String LoggingLabel = "A d d -  N e w - A d d r e s s -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField streetNumberTextField;
    @FXML
    private TextField streetNameTextField;
    @FXML
    private TextField codePostalTextField;
    @FXML
    private TextField cityTextField;

    private DialogBox dialogBox = new DialogBox();
    private AddressService addressService = new AddressService(networkConfig);

    public void resetFields(ActionEvent actionEvent) {
        nameTextField.setText("");
        streetNumberTextField.setText("");
        streetNameTextField.setText("");
        codePostalTextField.setText("");
        cityTextField.setText("");
    }

    public void addNewAddress(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String streetName = streetNameTextField.getText();
        String postalCode = codePostalTextField.getText();
        String city = cityTextField.getText();
        String streetNumberText = streetNumberTextField.getText();
        String regex = "^(0[1-9]|[1-8][0-9]|9[0-8])[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        int streetNumber = 0;

        try{
            streetNumber = Integer.parseInt(streetNumberText);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            dialogBox.setTitle("Ajouter une nouvelle Adresse0");
            dialogBox.setContentText("Veuillez entrer un nombre entier pour le numéro de rue");
            dialogBox.showAndWait();
            throw null;
        }

        if (!pattern.matcher(postalCode).matches()) {
            logger.error("Postal code must be in the format 12345");
            dialogBox.setTitle("Ajouter une nouvelle Adresse");
            dialogBox.setContentText("Veuillez entrer un code postal valide");
            dialogBox.showAndWait();
            return;
        }

        if (handleError(name, streetName, postalCode, city, streetNumber)) {
            logger.error("Please fill all the fields");
            return;
        }

        try {
            streetNumber = Integer.parseInt(streetNumberText);
        } catch (NumberFormatException e) {
            logger.error("Street number must be a number");
            return;
        }

        try {
            Address address = new Address();
            address.setName(name);
            address.setStreetNumber(streetNumber);
            address.setStreetName(streetName);
            address.setPostalCode(postalCode);
            address.setCity(city);
            address.setCountry("France");
            addressService.insertAddress(address);
            dialogBox.setTitle("Success");
            dialogBox.setContentText("Address added successfully");
            dialogBox.showAndWait();
        } catch (IOException e) {
            logger.error("Error while adding address: {}", e.getMessage());
            dialogBox.setTitle("Error");
            dialogBox.setContentText("Error while adding address: " + e.getMessage());
            dialogBox.showAndWait();
        }


    }


    boolean handleError(String name, String streetName, String codePostal, String ville, int streetNumber) {
        boolean error = false;
        if(name.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer le nom de la l'adresse");
            dialogBox.showAndWait();
            error = true;
        }
        else if (streetName.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer le nom de la rue");
            dialogBox.showAndWait();
            error = true;
        }
        else if (codePostal.isEmpty()) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer le code postal ");
            dialogBox.showAndWait();
            error = true;
        }
        else if (ville.isEmpty()) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer la ville");
            dialogBox.showAndWait();
            error = true;
        }
        else if (streetNumber <= 0) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer un numéro de rue supérieur à 0");
            dialogBox.showAndWait();
            error = true;
        }
        return error;
    }

}
