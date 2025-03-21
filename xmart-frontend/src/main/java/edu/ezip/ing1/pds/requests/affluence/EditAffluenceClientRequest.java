package edu.ezip.ing1.pds.requests.affluence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class EditAffluenceClientRequest extends ClientRequest<Affluence, Affluence> {

    public EditAffluenceClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Affluence info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Affluence readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence result = mapper.readValue(body, Affluence.class);
        return result;
    }

}
