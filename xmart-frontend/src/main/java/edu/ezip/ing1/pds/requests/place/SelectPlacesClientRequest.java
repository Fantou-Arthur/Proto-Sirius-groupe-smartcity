package edu.ezip.ing1.pds.requests.place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectPlacesClientRequest extends ClientRequest<Object, Places> {

    public SelectPlacesClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Places readResult(String body) throws IOException {
        //TODO: implement this function
        return null;
    }
}
