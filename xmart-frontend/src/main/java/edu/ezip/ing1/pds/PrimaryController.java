package edu.ezip.ing1.pds;

import javafx.fxml.FXML;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        MainView.setRoot("secondary");
    }
}
