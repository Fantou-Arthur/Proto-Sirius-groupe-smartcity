package edu.ezip.ing1.pds.utils;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogBox extends Dialog<String> {
    public DialogBox() {
        super();
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(type);
    }
    public DialogBox(String title, String message) {
        super();
        setTitle(title);
        setContentText(message);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(type);
    }


}
