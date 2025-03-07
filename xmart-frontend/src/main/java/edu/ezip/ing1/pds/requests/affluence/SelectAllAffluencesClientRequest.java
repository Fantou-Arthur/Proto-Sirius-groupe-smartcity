package edu.ezip.ing1.pds.requests.affluence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllAffluencesClientRequest extends ClientRequest<Object, Affluences> {

    public SelectAllAffluencesClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Affluences readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluences affluences = mapper.readValue(body, Affluences.class);
        return affluences;
    }
}
