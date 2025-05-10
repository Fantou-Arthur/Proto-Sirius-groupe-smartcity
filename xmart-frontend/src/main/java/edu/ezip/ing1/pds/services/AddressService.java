package edu.ezip.ing1.pds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.address.Address;
import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.requests.address.DeleteAddressClientRequest;
import edu.ezip.ing1.pds.requests.address.InsertAddressClientRequest;
import edu.ezip.ing1.pds.requests.address.SelectAllAddressClientRequest;
import edu.ezip.ing1.pds.requests.address.UpdateAddressClientRequest;
import edu.ezip.ing1.pds.requests.place.DeletePlaceClientRequest;
import edu.ezip.ing1.pds.requests.place.InsertPlaceClientRequest;
import edu.ezip.ing1.pds.requests.place.SelectAllPlacesClientRequest;
import edu.ezip.ing1.pds.requests.place.UpdatePlaceClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class AddressService {

    private final static String LoggingLabel = "FrontEnd - AddressService";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);

    enum RequestOrder {
        INSERT_ADDRESS, SELECT_ALL_ADDRESS, UPDATE_ADDRESS,DELETE_ADDRESS
    };

    private NetworkConfig networkConfig;

    public AddressService(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    int birthdate = 0;

    public Address insertAddress(Address address) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));
        request.setRequestOrder(RequestOrder.INSERT_ADDRESS.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final InsertAddressClientRequest insertAddressClientRequest = new InsertAddressClientRequest(
                    networkConfig,
                    birthdate++, request, address, requestBytes);

            insertAddressClientRequest.join();
            Address newAddress = (Address) insertAddressClientRequest.getResult();
            return newAddress;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }

    public Address updateAddress(Address address) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));
        request.setRequestOrder(RequestOrder.UPDATE_ADDRESS.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final UpdateAddressClientRequest updateAddressClientRequest = new UpdateAddressClientRequest(
                    networkConfig,
                    birthdate++, request, address, requestBytes);

            updateAddressClientRequest.join();
            // Get the place and log
            Address updatedAddress = (Address) updateAddressClientRequest.getInfo();
            return updatedAddress;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }

    public Address deleteAddress(Address address) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestContent(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));
        request.setRequestOrder(RequestOrder.DELETE_ADDRESS.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final DeleteAddressClientRequest deleteAddressClientRequest = new DeleteAddressClientRequest(
                    networkConfig,
                    birthdate++, request, address, requestBytes);

            deleteAddressClientRequest.join();
            Address deletedAddress = (Address) deleteAddressClientRequest.getInfo();
            return deletedAddress;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return  null;
    }



    public Addresses selectAllAddress() throws IOException {
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();

        request.setRequestId(requestId);
        request.setRequestOrder(RequestOrder.SELECT_ALL_ADDRESS.toString());
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        try {
            final SelectAllAddressClientRequest selectAllAddressClientRequest = new SelectAllAddressClientRequest(
                    networkConfig,
                    birthdate++, request, null, requestBytes);
            selectAllAddressClientRequest.join();
            Addresses addresses = selectAllAddressClientRequest.getResult();
            return addresses;
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Address getAddressById(int id) throws IOException {
        Addresses addresses = selectAllAddress();
        if (addresses != null) {
            for (Address address : addresses.getEntities()) {
                if (address.getId() == id) {
                    return address;
                }
            }
        }
        return null;
    }

}
