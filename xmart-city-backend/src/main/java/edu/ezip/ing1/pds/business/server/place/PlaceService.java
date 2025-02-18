package edu.ezip.ing1.pds.business.server.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;

import java.io.IOException;
import java.sql.*;

public class PlaceService {

    public PlaceService() {}

    public Response InsertPlace(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Place place = mapper.readValue(request.getRequestBody(), Place.class);
        final PreparedStatement statement = connection.prepareStatement(PlaceQueries.INSERT_PLACE.getQuery());
        statement.setString(1, place.getName());
        statement.setString(2, place.getAddress());
        statement.setInt(3, place.getMaxCapacity());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from Places");
        resultSet.last();
        return  new Response(request.getRequestId(), mapper.writeValueAsString(place));

    }


    public Response SelectAllPlaces(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(PlaceQueries.SELECT_ALL_PLACES.getQuery());
        Places places = new Places();
        while (res.next()) {
            Place place = new Place();
            place.setName(res.getString(1));
            place.setAddress(res.getString(2));
            place.setMaxCapacity(res.getInt(3));
            places.add(place);
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(places));
    }

}
