package edu.ezip.ing1.pds.requests.place;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllPlacesClientRequest extends ClientRequest<Object, Places> {

    public SelectAllPlacesClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Places readResult(String body) throws IOException {
        //TODO: When back handle error sending then do it here also
        final ObjectMapper mapper = new ObjectMapper();
        final Places places = mapper.readValue(body, Places.class);
        return places;
    }
}
