package edu.ezip.ing1.pds.requests.capteur;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class DeleteCapteurClientRequest extends ClientRequest<Capteur, Capteur > {

    public DeleteCapteurClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Capteur info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Capteur readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Capteur capteur = mapper.readValue(body, Capteur.class);
        return capteur;
    }

}
