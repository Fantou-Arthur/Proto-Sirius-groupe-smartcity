package edu.ezip.ing1.pds.controllers.place;

import edu.ezip.ing1.pds.MainView;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddNewController {

    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }
    
}
