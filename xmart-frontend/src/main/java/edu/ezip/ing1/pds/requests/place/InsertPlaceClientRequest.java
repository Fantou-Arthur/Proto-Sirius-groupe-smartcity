package edu.ezip.ing1.pds.requests.place;

import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class InsertPlaceClientRequest extends ClientRequest<Place, String > {

    public InsertPlaceClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Place info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        //TODO : implement this function action
        return "";
    }

}
