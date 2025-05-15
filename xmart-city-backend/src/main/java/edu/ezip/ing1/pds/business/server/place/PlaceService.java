package edu.ezip.ing1.pds.business.server.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.business.dto.place.Type;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class PlaceService {

    private final static String LoggingLabel = "P l a c e - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public PlaceService() {}

    public Response InsertPlace(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        final Place place = objectMapper.readValue(request.getRequestBody(), Place.class);
        final PreparedStatement statement = connection.prepareStatement(PlaceQueries.INSERT_PLACE.getQuery());
        statement.setString(1, place.getName());
        statement.setString(2, place.getType().toString());
        statement.setString(3, place.getDescription());
        statement.setDouble(4, place.getLatitude());
        statement.setDouble(5, place.getLongitude());
        statement.setInt(6,place.getMaxCapacity());
        statement.setTime(7, place.getPeakHour());
        statement.setInt(8, place.getId_entity());
        statement.setInt(9,place.getId_address());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from Places ");
        resultSet.next();
        return  new Response(request.getRequestId(), objectMapper.writeValueAsString(place));

    }


    public Response SelectAllPlaces(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(request.getRequestBody(), User.class);
        final PreparedStatement statement = connection.prepareStatement(PlaceQueries.SELECT_ALL_PLACES.getQuery());
        statement.setInt(1, user.getEntityId());
        final ResultSet resultSet = statement.executeQuery();

        Places places = new Places();
        while (resultSet.next()) {
            places.add(resultSetToPlace(resultSet));
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(places));
    }

    public Response UpdatePlace(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        final Place place = objectMapper.readValue(request.getRequestBody(), Place.class);
        final PreparedStatement statement = connection.prepareStatement(PlaceQueries.UPDATE_PLACE.getQuery());
        statement.setString(1, place.getName());
        statement.setString(2, place.getType().toString());
        statement.setString(3, place.getDescription());
        statement.setDouble(4, place.getLatitude());
        statement.setDouble(5, place.getLongitude());
        statement.setInt(6,place.getMaxCapacity());
        statement.setTime(7, place.getPeakHour());
        statement.setInt(8, place.getId_address());
        statement.setInt(9, place.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from Places where id = "+place.getId());
        resultSet.next();
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(resultSetToPlace(resultSet)));
    }

    public Response DeletePlace(final Request request, final Connection connection) throws SQLException, IOException {
        //TODO: Handle error and send to the front
        final ObjectMapper objectMapper = new ObjectMapper();
        final Place place = objectMapper.readValue(request.getRequestBody(), Place.class);
        final PreparedStatement statement = connection.prepareStatement(PlaceQueries.DELETE_PLACE.getQuery());
        statement.setInt(1, place.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select count(*) from Places where id = "+place.getId());
        resultSet.next();
        int result = resultSet.getInt(1);
        logger.info("Total of place with the id {} is {}", place.getId(), result);
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(place));
    }


    private Place resultSetToPlace(final ResultSet resultSet) throws SQLException {
        Place place = new Place();
        place.setId(resultSet.getInt(1));
        place.setName(resultSet.getString(2));
        place.setType(Type.valueOf(resultSet.getString(3)));
        place.setDescription(resultSet.getString(4));
        place.setLatitude(resultSet.getDouble(5));
        place.setLongitude(resultSet.getDouble(6));
        place.setMaxCapacity(resultSet.getInt(7));
        place.setPeakHour(resultSet.getTime(8));
        place.setId_entity(resultSet.getInt(9));
        place.setId_address(resultSet.getInt(10));
        return place;
    }

}
