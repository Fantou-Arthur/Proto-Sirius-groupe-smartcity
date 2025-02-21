package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.capteur.InsertCapteurClientRequest;
import edu.ezip.ing1.pds.requests.capteur.SelectCapteursClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class CapteurService {

    private final static String LoggingLabel = "FrontEnd - CapteurService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        INSERT_CAPTEUR, SELECT_ALL_CAPTEUR, EDIT_CAPTEUR,
    };

    private NetworkConfig networkConfig;

    public  CapteurService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public void insertCapteur(Capteur capteur) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(capteur));
        request.setRequestOrder(CapteurService.RequestOrder.INSERT_CAPTEUR.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final InsertCapteurClientRequest insertCapteurClientRequest = new InsertCapteurClientRequest(
                    networkConfig,
                    birthdate++, request, capteur, requestBytes);

            insertCapteurClientRequest.join();
            // Get the capteur and log
            Capteur newCapteur = (Capteur) insertCapteurClientRequest.getInfo();
            logger.info("New capteur inserted : " + newCapteur);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public void selectAllCapteur() throws JsonProcessingException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestOrder(CapteurService.RequestOrder.SELECT_ALL_CAPTEUR.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectCapteursClientRequest selectCapteurClientRequest = new SelectCapteursClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectCapteurClientRequest.join();
            // Get the capteurs and log
            Capteurs capteurs = (Capteurs) selectCapteurClientRequest.getInfo();
            logger.info("List of capteurs: " + capteurs);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
