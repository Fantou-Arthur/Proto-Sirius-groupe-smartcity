package edu.ezip.ing1.pds.requests.entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.entity.Entities;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllEntitiesClientRequest extends ClientRequest<Object, Entities> {

    public SelectAllEntitiesClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Entities readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Entities entities = mapper.readValue(body, Entities.class);
        return entities;
    }
}
