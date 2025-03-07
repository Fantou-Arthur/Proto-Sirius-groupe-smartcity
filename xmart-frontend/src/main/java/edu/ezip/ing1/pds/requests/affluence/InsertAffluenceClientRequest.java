package edu.ezip.ing1.pds.requests.affluence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class InsertAffluenceClientRequest extends ClientRequest<Affluence, String > {

    public InsertAffluenceClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Affluence info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Integer> affluence = mapper.readValue(body, Map.class);
        final String result  = affluence.get("id").toString();
        return result;
    }

}
