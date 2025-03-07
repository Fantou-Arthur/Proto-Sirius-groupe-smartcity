package edu.ezip.ing1.pds.controllers.place;

import edu.ezip.ing1.pds.business.dto.place.Place;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class PlaceCell extends Place {
    private HBox actions;
    public Button  editButton;
    public Button  deleteButton;
    public PlaceCell(Place place) {
        this.setName(place.getName());
        this.setAddress(place.getAddress());
        this.setMaxCapacity(place.getMaxCapacity());

        File editIconFIle = new File("src/main/resources/icons/editIcon.png");
        File removeIconFIle = new File("src/main/resources/icons/removeIcon.png");
        ImageView editIcon = new ImageView(editIconFIle.toURI().toString());
        ImageView removeIcon = new ImageView(removeIconFIle.toURI().toString());

        editIcon.setFitHeight(20);
        editIcon.setFitWidth(20);
        removeIcon.setFitHeight(20);
        removeIcon.setFitWidth(20);

        editButton = new Button("", editIcon);
        deleteButton = new Button("", removeIcon);

        this.actions = new HBox();
        actions.getChildren().addAll(editButton, deleteButton);
    }

    public HBox getActions() {
        return actions;
    }
    public void setActions(HBox actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "PlaceCell [ name=" + this.getName() + ", address=" + this.getAddress() + ", maxCapacity=" + this.getMaxCapacity() + ", actions=" + this.actions +" ]";
    }
}
