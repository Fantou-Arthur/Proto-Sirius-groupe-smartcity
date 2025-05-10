package edu.ezip.ing1.pds.requests.address;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllAddressClientRequest extends ClientRequest<Object, Addresses> {

    public SelectAllAddressClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Addresses readResult(String body) throws IOException {
        //TODO: When back handle error sending then do it here also
        final ObjectMapper mapper = new ObjectMapper();
        final Addresses addresses = mapper.readValue(body, Addresses.class);
        return addresses;
    }
}
