package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.place.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class PlaceService {

    private final static String LoggingLabel = "FrontEnd - PlaceService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        INSERT_PLACE, SELECT_ALL_PLACES, UPDATE_PLACE,DELETE_PLACE, SELECT_ID_NAME_PLACES
    };

    private NetworkConfig networkConfig;

    public  PlaceService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public Place insertPlace(Place place) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(place));
        request.setRequestOrder(RequestOrder.INSERT_PLACE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final InsertPlaceClientRequest insertPlaceClientRequest = new InsertPlaceClientRequest(
                    networkConfig,
                    birthdate++, request, place, requestBytes);

            insertPlaceClientRequest.join();
            Place newPlace = (Place) insertPlaceClientRequest.getResult();
            logger.debug("New place inserted : {}", newPlace);
            return newPlace;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }

    public Place updatePlace(Place place) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(place));
        request.setRequestOrder(RequestOrder.UPDATE_PLACE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final UpdatePlaceClientRequest updatePlaceClientRequest = new UpdatePlaceClientRequest(
                    networkConfig,
                    birthdate++, request, place, requestBytes);

            updatePlaceClientRequest.join();
            // Get the place and log
            Place updatePlace = (Place) updatePlaceClientRequest.getInfo();
            logger.debug("New place updated : {}", updatePlace);
            return updatePlace;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }

    public Place deletePlace(Place place) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(place));
        request.setRequestOrder(RequestOrder.DELETE_PLACE.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final DeletePlaceClientRequest deletePlaceClientRequest = new DeletePlaceClientRequest(
                    networkConfig,
                    birthdate++, request, place, requestBytes);

            deletePlaceClientRequest.join();
            Place deletePlace = (Place) deletePlaceClientRequest.getInfo();
            logger.debug("Place deleted : {}", deletePlace);
            return deletePlace;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }



    public Places selectAllPlaces() throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestOrder(RequestOrder.SELECT_ALL_PLACES.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectAllPlacesClientRequest selectAllPlaceClientRequest = new SelectAllPlacesClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectAllPlaceClientRequest.join();
            Places places = selectAllPlaceClientRequest.getResult();
            return places;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public Places selectIdNamePlaces() throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(RequestOrder.SELECT_ID_NAME_PLACES.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectIdNamePlacesClientRequest selectIdNamePlacesClientRequest = new SelectIdNamePlacesClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectIdNamePlacesClientRequest.join();
            Places places = selectIdNamePlacesClientRequest.getResult();
            return places;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
