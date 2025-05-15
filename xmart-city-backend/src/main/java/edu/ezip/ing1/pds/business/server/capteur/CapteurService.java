package edu.ezip.ing1.pds.business.server.capteur;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.business.server.capteur.CapteurQueries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class CapteurService {

    private final static String LoggingLabel = "P l a c e - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public CapteurService() {}

    public Response InsertCapteur(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Capteur capteur = mapper.readValue(request.getRequestBody(), Capteur.class);
        final PreparedStatement statement = connection.prepareStatement(CapteurQueries.INSERT_CAPTEUR.getQuery());
        statement.setInt(1, capteur.getId());
        statement.setString(2, capteur.getName());
        statement.setBoolean(3, capteur.getState());
        statement.setInt(4, capteur.getId_lieu());
        statement.setString(5, capteur.getDescription());
        statement.setString(6, capteur.getManufacturer());
        statement.setString(7, capteur.getModel());
        statement.setString(8, capteur.getStatus());
        statement.setInt(9, capteur.getId_affluence());
        statement.setDate(10,  java.sql.Date.valueOf(capteur.getInstalled()));
        statement.setDate(11, java.sql.Date.valueOf(capteur.getLastMaintenance()));
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from sensor");
        resultSet.next();
        return  new Response(request.getRequestId(), mapper.writeValueAsString(capteur));

    }
    public Response DeleteCapteur(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Capteur capteur = objectMapper.readValue(request.getRequestBody(), Capteur.class);
        final PreparedStatement statement = connection.prepareStatement(CapteurQueries.DELETE_CAPTEUR.getQuery());
        statement.setInt(1, capteur.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select count(*) from Capteurs where id = "+capteur.getId());
        resultSet.next();
        int result = resultSet.getInt(1);
        logger.info("Total of capteur with the id {} is {}", capteur.getId(), result);
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(capteur));
    }


    public Response SelectAllCapteurs(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(CapteurQueries.SELECT_ALL_CAPTEURS.getQuery());
        logger.info(String.valueOf(res));
        Capteurs capteurs = new Capteurs();
        logger.info("Select all capteurs");
        while (res.next()) {
            Capteur capteur = new Capteur();
            capteur.setId(res.getInt(1));
            capteur.setName(res.getString(2));
            capteur.setState(res.getBoolean(3));
            capteur.setId_lieu(res.getInt(4));
            capteur.setDescription(res.getString(5));
            capteur.setManufacturer(res.getString(6));
            capteur.setModel(res.getString(7));
            capteur.setStatus(res.getString(8));
            capteur.setId_affluence(res.getInt(9));
            capteur.setInstalled(res.getString(10));
            capteur.setLastMaintenance(res.getString(11));
            capteurs.add(capteur);
            logger.info("Le capteur :" + capteur);
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(capteurs));
    }
    public Response EditCapteur(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Capteur capteur = objectMapper.readValue(request.getRequestBody(), Capteur.class);
        final PreparedStatement statement = connection.prepareStatement(CapteurQueries.EDIT_CAPTEUR.getQuery());
        statement.setString(1, capteur.getName());
        statement.setBoolean(2, capteur.getState());
        statement.setInt(3, capteur.getId_lieu());
        statement.setString(4, capteur.getDescription());
        statement.setString(5, capteur.getManufacturer());
        statement.setString(6, capteur.getModel());
        statement.setString(7, capteur.getStatus());
        statement.setInt(8, capteur.getId_affluence());
        statement.setDate(9,  java.sql.Date.valueOf(capteur.getInstalled()));
        statement.setDate(10, java.sql.Date.valueOf(capteur.getLastMaintenance()));
        statement.setInt(11, capteur.getId());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from sensor where id = "+capteur.getId());
        resultSet.next();
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(capteur));
    }
    private Capteur resultSetToCapteur(final ResultSet resultSet) throws SQLException {
        Capteur capteur = new Capteur();
        capteur.setId(resultSet.getInt(1));
        capteur.setName(resultSet.getString(2));
        capteur.setState(resultSet.getBoolean(3));
        capteur.setId_lieu(resultSet.getInt(4));
        return capteur;
    }

}
