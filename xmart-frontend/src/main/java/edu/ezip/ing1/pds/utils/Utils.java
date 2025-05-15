package edu.ezip.ing1.pds.utils;

import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.business.dto.entity.Entity;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.AddressService;
import edu.ezip.ing1.pds.services.AffluenceService;
import edu.ezip.ing1.pds.services.CapteurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private final static String LoggingLabel = "A d d -  N e w - P l a c e -  C o n t r o l l e r";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    final static NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    private static AddressService addressService = new AddressService(networkConfig);


    private static DialogBox dialogBox = new DialogBox();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    static String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"'<>?,./]).{8,}$";
    private static Pattern passwordPattern = Pattern.compile(regex);

    public static boolean checkEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean checkLoginData(String email, String password) {
        boolean error = false;
        if (email.isEmpty() || !checkEmail(email)) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer une addresse mail valide");
            dialogBox.showAndWait();
            error = true;
        } else if (password.isEmpty()) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer un mot de passe");
            dialogBox.showAndWait();
        }
        return error;
    }

    public static boolean checkSignUpData(String username, String email, String password, Entity entity){
        boolean error = false;
        if(username.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrer un nom d'utilisateur");
            dialogBox.showAndWait();
            error = true;
        } else if (passwordPattern.matcher(password).matches() == false) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial");
            dialogBox.showAndWait();
            error = true;
        }
        else if(checkLoginData(email,password)){
            error = true;
        }else if(entity == null){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez selectionnez une entité");
            dialogBox.showAndWait();
            error = true;
        }
        return error;
    }

    public static int getAssociatedAddressId(String addressName){
        int id = 0;
        try {
            Addresses addresses = addressService.selectAllAddress();
            if (addresses != null) {
                for (int i = 0; i < addresses.getEntities().size(); i++) {
                    if(addresses.getEntities().get(i).getName().equals(addressName)){
                        id = addresses.getEntities().get(i).getId();
                    }
                }
            } else {
                logger.debug("No address found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static String getAddressNameById(int id){
        String name = "";
        try {
            Addresses addresses = addressService.selectAllAddress();
            if (addresses != null) {
                for (int i = 0; i < addresses.getEntities().size(); i++) {
                    if(addresses.getEntities().get(i).getId() == id){
                        name = addresses.getEntities().get(i).getName();
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public static int getNumberOfAffluenceRelatedTo(Place place) throws  IOException{
        int count = 0;
        AffluenceService affluenceService = new AffluenceService(networkConfig);
        Affluences affluences = affluenceService.selectAllAffluences();
        if (affluences != null) {
            for (int i = 0; i < affluences.getAffluences().size(); i++) {
                if(affluences.getAffluences().get(i).getIdPlace() == place.getId()){
                    count++;
                }
            }
        } else {
            logger.debug("No affluence found");
        }
        return count;
    }

    public static int getNumberOfSensorRelatedTo(Place place) throws IOException {
        CapteurService capteurService = new CapteurService(networkConfig);
        Capteurs capteurs = capteurService.selectAllCapteurs();
        int count = 0;
        if (capteurs != null) {

            for (int i = 0; i < capteurs.getCapteurs().size(); i++) {
                if(capteurs.getCapteurs().get(i).getId_lieu() == place.getId()){
                    count++;
                }
            }
        } else {
            logger.debug("No capteur found");
        }
        return count;
    }
}
