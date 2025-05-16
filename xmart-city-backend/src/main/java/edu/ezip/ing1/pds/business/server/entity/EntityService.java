package edu.ezip.ing1.pds.business.server.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.entity.Entities;
import edu.ezip.ing1.pds.business.dto.entity.Entity;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityService {


    private final static String LoggingLabel = "P l a c e - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public Response selectAllEntity(Request request, Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet resultSet = stmt.executeQuery(EntityQueries.SELECT_ALL_ENTITY.getQuery());
        Entities entities = new Entities();
        while (resultSet.next()) {
            entities.add(resultSetToEntity(resultSet));
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(entities));
    }


    private Entity resultSetToEntity(ResultSet resultSet) throws SQLException {
        Entity entity = new Entity();
        entity.setId(resultSet.getInt(1));
        entity.setName(resultSet.getString(2));
        entity.setEmail(resultSet.getString(3));
        entity.setPhone(resultSet.getString(4));
        entity.setDescription(resultSet.getString(5));
        return  entity;
    }

}
