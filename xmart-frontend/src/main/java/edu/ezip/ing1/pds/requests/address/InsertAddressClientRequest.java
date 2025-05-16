package edu.ezip.ing1.pds.requests.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.address.Address;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class InsertAddressClientRequest extends ClientRequest<Address, Address > {

    public InsertAddressClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Address info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Address readResult(String body) throws IOException {
        //TODO: When back handle error sending then do it here also
        final ObjectMapper mapper = new ObjectMapper();
        final Address address = mapper.readValue(body, Address.class);
        return address;
    }

}
