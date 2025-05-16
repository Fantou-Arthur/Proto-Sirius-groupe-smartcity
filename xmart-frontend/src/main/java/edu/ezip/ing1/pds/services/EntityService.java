package edu.ezip.ing1.pds.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.entity.Entities;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.entity.SelectAllEntitiesClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class EntityService {

    private final static String LoggingLabel = "FrontEnd - EntityService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        SELECT_ALL_ENTITY,
    };

    private NetworkConfig networkConfig;

    public EntityService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public Entities selectAllEntity() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestOrder(RequestOrder.SELECT_ALL_ENTITY.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectAllEntitiesClientRequest selectAllEntitiesClientRequest = new SelectAllEntitiesClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectAllEntitiesClientRequest.join();
            Entities entities = selectAllEntitiesClientRequest.getResult();
            return entities;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
