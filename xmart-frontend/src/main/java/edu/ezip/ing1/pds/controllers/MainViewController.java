package edu.ezip.ing1.pds.controllers;

import edu.ezip.ing1.pds.MainView;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController {

    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("addNewPlace");
    }
    @FXML
    private void GoToCapteurView() throws IOException {
        System.out.println("GoToCapteurView");
        MainView.setRoot("CapteurView");
    }

    
}
