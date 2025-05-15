package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.place.DeletePlaceClientRequest;
import edu.ezip.ing1.pds.requests.user.LoginClientRequest;
import edu.ezip.ing1.pds.requests.user.SignUpClientRequest;
import edu.ezip.ing1.pds.requests.place.SelectAllPlacesClientRequest;
import edu.ezip.ing1.pds.requests.place.UpdatePlaceClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class UserService {

    private final static String LoggingLabel = "FrontEnd - UserService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        INSERT_USER, LOGIN_USER, SELECT_ALL_USERS, UPDATE_USER, DELETE_USER,
    };

    private NetworkConfig networkConfig;

    public  UserService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public User insertUser(User user) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
        request.setRequestOrder(RequestOrder.INSERT_USER.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SignUpClientRequest signUpClientRequest = new SignUpClientRequest(
                    networkConfig,
                    birthdate++, request, user, requestBytes);

            signUpClientRequest.join();
            User newUser = (User) signUpClientRequest.getResult();
            logger.debug("New user inserted : {}", newUser);
            UserSession.getInstance().setEntityId(newUser.getEntityId());
            return newUser;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }

    public User loginUser(User user) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
        request.setRequestOrder(RequestOrder.LOGIN_USER.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try{
            final LoginClientRequest loginClientRequest = new LoginClientRequest(
                    networkConfig,
                    birthdate++, request, user, requestBytes);
            loginClientRequest.join();
            User connectedUser = (User) loginClientRequest.getResult();
            if(connectedUser == null) {
                logger.error("Login failed");
                return null;
            }
            logger.debug("User connected : {}", connectedUser);
            UserSession.getInstance().setEntityId(connectedUser.getEntityId());
            return connectedUser;
        }catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }



}
