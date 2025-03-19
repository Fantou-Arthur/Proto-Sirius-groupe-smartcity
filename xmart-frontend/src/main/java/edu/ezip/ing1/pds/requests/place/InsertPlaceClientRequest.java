package edu.ezip.ing1.pds.requests.place;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class InsertPlaceClientRequest extends ClientRequest<Place, Place > {

    public InsertPlaceClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Place info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Place readResult(String body) throws IOException {
        //TODO: When back handle error sending then do it here also
        final ObjectMapper mapper = new ObjectMapper();
        final Place place = mapper.readValue(body, Place.class);
        return place;
    }

}
