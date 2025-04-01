package edu.ezip.ing1.pds.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static DialogBox dialogBox = new DialogBox();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean checkEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean checkLoginData(String email, String password) {
        boolean error = false;
        if (email.isEmpty() || !checkEmail(email)) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez une addresse mail valide");
            dialogBox.showAndWait();
            error = true;
        } else if (password.isEmpty()) {
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez un mot de passe");
            dialogBox.showAndWait();
        }
        return error;
    }

    public static boolean checkSignUpData(String username, String email, String password){
        boolean error = false;
        if(username.isEmpty()){
            dialogBox.setTitle("Erreur");
            dialogBox.setContentText("Veuillez entrez un nom d'utilisateur");
            dialogBox.showAndWait();
            error = true;
        }else if(checkLoginData(email,password)) error = true;
        return error;
    }
}
