package edu.ezip.ing1.pds.controllers.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ezip.ing1.pds.MainView;
import edu.ezip.ing1.pds.business.dto.address.Address;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.AddressService;
import edu.ezip.ing1.pds.services.PlaceService;
import edu.ezip.ing1.pds.utils.DialogBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

public class PlaceCell extends Place {
    private HBox actions;
    public Button  editButton;
    public Button  deleteButton;
    public Button  detailsButton;
    private Address address;
    final String networkConfigFile = "network.yaml";
    final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
    PlaceService placeService = new PlaceService(networkConfig);

    DialogBox dialogBox = new DialogBox();

    public PlaceCell(Place place) {
        this.setId(place.getId());
        this.setName(place.getName());
        this.setAddress(place.getAddress());
        this.setMaxCapacity(place.getMaxCapacity());
        this.setLatitude(place.getLatitude());
        this.setLongitude(place.getLongitude());
        this.setDescription(place.getDescription());
        this.setPeakHour(place.getPeakHour());
        this.setType(place.getType());

        File editIconFIle = new File("src/main/resources/icons/editIcon.png");
        File removeIconFIle = new File("src/main/resources/icons/removeIcon.png");
        ImageView editIcon = new ImageView(editIconFIle.toURI().toString());
        ImageView removeIcon = new ImageView(removeIconFIle.toURI().toString());

        editIcon.setFitHeight(20);
        editIcon.setFitWidth(20);
        removeIcon.setFitHeight(20);
        removeIcon.setFitWidth(20);

        editButton = new Button("", editIcon);
        editButton.setOnAction(e -> {
            try {
                MainView.setRoot("editPlace",place);
            } catch (IOException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        });

        deleteButton = new Button("", removeIcon);
        deleteButton.setOnAction(e -> {
            try {

                Place deletePlace = placeService.deletePlace(place);
                dialogBox.setTitle("Supprimer Place");
                dialogBox.setContentText("Place " + deletePlace.getName()+ " supprimer avec succès");
                dialogBox.showAndWait();
                MainView.setRoot("listPlaces");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        detailsButton = new Button("Details");
        detailsButton.setOnAction(e -> {
            AddressService addressService = new AddressService(networkConfig);
            try {
                address = addressService.getAddressById(place.getAddress());
            } catch (IOException ex) {
                dialogBox.setTitle("Erreur");
                dialogBox.setContentText("Go fix your erreur");
                dialogBox.showAndWait();
                throw new RuntimeException(ex);

            }
            dialogBox.setTitle("Details");
            dialogBox.setContentText("Place : " + place.getName() + "\n" +
                    "Description : " + place.getDescription() + "\n" +
                    "Type : " + place.getType() + "\n" +
                    "Capacité Maximale : " + place.getMaxCapacity() + "\n" +
                    "Latitude : " + place.getLatitude() + "\n" +
                    "Longitude : " + place.getLongitude() + "\n" +
                    "Heure de pointe : " + place.getPeakHour() + "\n" +
                    "Addresse : " + address.getName() + "\n" +
                    "Rue : " + address.getStreetName() + "\n" +
                    "Numéro : " + address.getStreetNumber() + "\n"+
                    "Ville : " + address.getCity() + "\n" +
                    "Code Postal : " + address.getPostalCode() + "\n" +
                    "Pays : " + address.getCountry() + "\n"
            );
            dialogBox.showAndWait();
        });

        this.actions = new HBox();
        actions.setAlignment(Pos.BASELINE_CENTER);
        actions.setSpacing(10);
        actions.getChildren().addAll(detailsButton, editButton, deleteButton);
    }

    public HBox getActions() {
        return actions;
    }
    public void setActions(HBox actions) {
        this.actions = actions;
    }


    @Override
    public String toString() {
        return "PlaceCell{" +
                "actions=" + actions +
                ", editButton=" + editButton +
                ", deleteButton=" + deleteButton +
                ", detailsButton=" + detailsButton +
                '}';
    }



}
