package edu.ezip.ing1.pds.requests.capteur;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectCapteursClientRequest extends ClientRequest<Object, Capteurs> {

    public SelectCapteursClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Capteurs readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Capteurs capteurs = mapper.readValue(body, Capteurs.class);
        return capteurs;
    }
}
