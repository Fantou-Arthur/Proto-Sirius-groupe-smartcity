package edu.ezip.ing1.pds.business.server.address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.address.Address;
import edu.ezip.ing1.pds.business.dto.address.Addresses;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.business.server.place.PlaceQueries;
import edu.ezip.ing1.pds.business.server.user.UserQueries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AddressService {
    private final static String LoggingLabel = "U s e r - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public AddressService() {}

    public Response InsertAddress(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Address address = objectMapper.readValue(request.getRequestBody(), Address.class);
        if(checkIfAddressExist(address.getName(), connection)) return new Response(request.getRequestId(), objectMapper.writeValueAsString(null));
        final PreparedStatement statement = connection.prepareStatement(AddressQueries.INSERT_ADDRESS.getQuery());
        statement.setString(1, address.getName());
        statement.setInt(2, address.getStreetNumber());
        statement.setString(3, address.getStreetName());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getCity());
        statement.executeUpdate();
        return  new Response(request.getRequestId(), objectMapper.writeValueAsString(address));

    }

    public Address SelectAddressByName(String name, Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(AddressQueries.SELECT_ADDRESS_BY_NAME.getQuery());
        statement.setString(1,name);
        final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            return resultSetToAddress(resultSet);
        }
        return null;
    }

    public Response UpdateAddress(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        final Address address = objectMapper.readValue(request.getRequestBody(), Address.class);
        final PreparedStatement statement = connection.prepareStatement(AddressQueries.UPDATE_ADDRESS.getQuery());
        statement.setString(1, address.getName());
        statement.setInt(2, address.getStreetNumber());
        statement.setString(3, address.getStreetName());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getCity());
        statement.setString(6, address.getCountry());
        statement.setInt(7, address.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from Places where id = "+address.getId());
        resultSet.next();
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(resultSetToAddress(resultSet)));
    }

    public Response DeleteAddress(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        final Address address = objectMapper.readValue(request.getRequestBody(), Address.class);
        final PreparedStatement statement = connection.prepareStatement(AddressQueries.DELETE_ADDRESS.getQuery());
        statement.setInt(1, address.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select count(*) from Address where id = "+address.getId());
        resultSet.next();
        int result = resultSet.getInt(1);
        logger.info("Total of place with the id {} is {}", address.getId(), result);
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(address));
    }

    public Response SelectAllAddress(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(AddressQueries.SELECT_ALL_ADDRESS.getQuery());
        Addresses addresses = new Addresses();
        while (resultSet.next()) {
            addresses.add(resultSetToAddress(resultSet));
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(addresses));
    }

    private Address resultSetToAddress(final ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getInt(1));
        address.setName(resultSet.getString(2));
        address.setStreetNumber(resultSet.getInt(3));
        address.setStreetName(resultSet.getString(4));
        address.setPostalCode(resultSet.getString(5));
        address.setCity(resultSet.getString(6));
        address.setCountry(resultSet.getString(7));
        return address;
    }

    private boolean checkIfAddressExist(String name, Connection connection) throws SQLException{
        Address address = SelectAddressByName(name, connection);
        if(address == null) return false;
        return true;
    }

}
