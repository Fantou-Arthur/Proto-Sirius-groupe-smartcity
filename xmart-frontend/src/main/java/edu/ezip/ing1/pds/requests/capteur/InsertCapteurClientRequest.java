package edu.ezip.ing1.pds.requests.capteur;

import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class InsertCapteurClientRequest extends ClientRequest<Capteur, String > {

    public InsertCapteurClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Capteur info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        //TODO : implement this function action
        return "";
    }

}
