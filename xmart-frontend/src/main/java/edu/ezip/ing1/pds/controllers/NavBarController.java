package edu.ezip.ing1.pds.controllers;

import edu.ezip.ing1.pds.MainView;
import javafx.fxml.FXML;

import java.io.IOException;

public class NavBarController {
    @FXML
    private void createNewPlace() throws IOException {
        MainView.setRoot("createPlace");
    }

    @FXML
    private void GoToCapteurView() throws IOException {
        System.out.println("GoToCapteurView");
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
}
