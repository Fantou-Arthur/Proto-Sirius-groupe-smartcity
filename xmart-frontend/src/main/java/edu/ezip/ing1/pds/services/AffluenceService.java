package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.affluence.InsertAffluenceClientRequest;
import edu.ezip.ing1.pds.requests.affluence.SelectAllAffluencesClientRequest;
import edu.ezip.ing1.pds.requests.affluence.DeleteAffluenceClientRequest;
import edu.ezip.ing1.pds.requests.affluence.EditAffluenceClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class AffluenceService {

    private final static String LoggingLabel = "FrontEnd - AffluenceService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        INSERT_AFFLUENCE, SELECT_ALL_AFFLUENCES, DELETE_AFFLUENCE, EDIT_AFFLUENCE,
    };

    private NetworkConfig networkConfig;

    public  AffluenceService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public void insertAffluence(Affluence affluence) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(affluence));
        request.setRequestOrder(RequestOrder.INSERT_AFFLUENCE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final InsertAffluenceClientRequest insertAffluenceClientRequest = new InsertAffluenceClientRequest(
                    networkConfig,
                    birthdate++, request, affluence, requestBytes);

            insertAffluenceClientRequest.join();
            Affluence newAffluence = (Affluence) insertAffluenceClientRequest.getInfo();
            logger.info("New affluence inserted : " + newAffluence);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public void editAffluence(Affluence affluence) throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(affluence));
        request.setRequestOrder(RequestOrder.EDIT_AFFLUENCE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final EditAffluenceClientRequest editAffluenceClientRequest = new EditAffluenceClientRequest(
                    networkConfig,
                    birthdate++, request, affluence, requestBytes);
            editAffluenceClientRequest.join();
            
            Affluence new_affluence = (Affluence) editAffluenceClientRequest.getResult();
            logger.info("edited {} with {}", affluence, new_affluence);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteAffluence(Affluence affluence) throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(affluence));
        request.setRequestOrder(RequestOrder.DELETE_AFFLUENCE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final DeleteAffluenceClientRequest deleteAffluenceClientRequest = new DeleteAffluenceClientRequest(
                    networkConfig,
                    birthdate++, request, affluence, requestBytes);
            deleteAffluenceClientRequest.join();
            
            Affluence rep = deleteAffluenceClientRequest.getResult();
            logger.info("deleted {}", rep);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public Affluences selectAllAffluences() throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestOrder(RequestOrder.SELECT_ALL_AFFLUENCES.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectAllAffluencesClientRequest selectAllAffluenceClientRequest = new SelectAllAffluencesClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectAllAffluenceClientRequest.join();
            
            Affluences affluences = selectAllAffluenceClientRequest.getResult();
            return affluences;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
